package com.javarush.task.task30.task3008;

import com.javarush.task.task30.task3008.client.Client;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();
    
    public static void sendBroadcastMessage(Message message) {
        for (Connection connection : connectionMap.values()) {
            try {
                connection.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Ошибка при отправке сообщения");
            }
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            Message answer;
            do {
                connection.send(new Message(MessageType.NAME_REQUEST));
                answer = connection.receive();
            } while (answer.getType() != MessageType.USER_NAME || answer.getData().equals("") || connectionMap.containsKey(answer.getData()));

            connectionMap.put(answer.getData(), connection);
            connection.send(new Message(MessageType.NAME_ACCEPTED));

            return answer.getData();
        }

        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> pair: connectionMap.entrySet()) {
                if (!pair.getKey().equals(userName)) {
                    Message message = new Message(MessageType.USER_ADDED, pair.getKey());
                    connection.send(message);
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message clientMessage = connection.receive();
                if (clientMessage.getType() == MessageType.TEXT) {
                    Message textMessage = new Message(MessageType.TEXT, userName + ": " + clientMessage.getData());
                    sendBroadcastMessage(textMessage);
                } else ConsoleHelper.writeMessage("Ошибка");
            }
        }

        public void run() {
            String userName = "";
            ConsoleHelper.writeMessage(socket.getRemoteSocketAddress() + "");
            try(Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);

            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом");
            }
            finally {
                if (userName != null){
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED,userName));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt());
        ConsoleHelper.writeMessage("Сервер запущен!");

        while (true) {
            try {
                Handler handler = new Handler(serverSocket.accept());
                handler.start();
                continue;
            } catch (IOException e) {
                serverSocket.close();
                ConsoleHelper.writeMessage(e.getMessage());
                break;
            }
        }
    }


}
