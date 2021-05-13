package com.javarush.task.task32.task3208;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/* 
RMI-2
*/
public class Solution {
    public static Registry registry;

    // Pretend we're starting an RMI client as the CLIENT_THREAD thread
    public static Thread CLIENT_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for (String bindingName : registry.list()) {
                    Animal service = (Animal) registry.lookup(bindingName);
                    service.printName();
                    service.speak();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        }
    });

    // Pretend we're starting an RMI server as the SERVER_THREAD thread
    public static Thread SERVER_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            //напишите тут ваш код
            try {
                final String UNIC_BINDING_CAT_NAME = "server.cat";
                final String UNIC_BINDING_DOG_NAME = "server.dog";
                registry = LocateRegistry.createRegistry(2099);
                Cat cat = new Cat("Tom");
                Dog dog = new Dog("Spike");
                Remote shareCat = UnicastRemoteObject.exportObject(cat, 0);
                Remote shareDog = UnicastRemoteObject.exportObject(dog, 0);
                registry.bind(UNIC_BINDING_CAT_NAME, shareCat);
                registry.bind(UNIC_BINDING_DOG_NAME, shareDog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    public static void main(String[] args) throws InterruptedException {
        // Starting an RMI server thread
        SERVER_THREAD.start();
        Thread.sleep(1000);
        // Start the client
        CLIENT_THREAD.start();
    }
}