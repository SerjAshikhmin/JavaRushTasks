package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {

    private final Path logDir;
    private final DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);

    public LogParser(Path logDir) {
        this.logDir = logDir;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .map(userNote -> userNote.getIp())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getName().equals(user))
                .map(UserNote::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getEvent().equals(event))
                .map(UserNote::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getStatus().equals(status))
                .map(UserNote::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAllUsers() {
        return getAllNotesFromLogs().stream()
                .map(logNote -> logNote[1])
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .map(UserNote::getName)
                .collect(Collectors.toSet()).size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getName().equals(user))
                .map(UserNote::getEvent)
                .collect(Collectors.toSet()).size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getIp().equals(ip))
                .map(UserNote::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getEvent().equals(Event.LOGIN))
                .map(UserNote::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getEvent().equals(Event.DOWNLOAD_PLUGIN))
                .map(UserNote::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getEvent().equals(Event.WRITE_MESSAGE))
                .map(UserNote::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getEvent().equals(Event.SOLVE_TASK))
                .map(UserNote::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getEvent().equals(Event.SOLVE_TASK) && userNote.getTaskNumber() == task)
                .map(UserNote::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getEvent().equals(Event.DONE_TASK))
                .map(UserNote::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getEvent().equals(Event.DONE_TASK) && userNote.getTaskNumber() == task)
                .map(UserNote::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getName().equals(user) && userNote.getEvent().equals(event))
                .map(UserNote::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getStatus().equals(Status.FAILED))
                .map(UserNote::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getStatus().equals(Status.ERROR))
                .map(UserNote::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Date date = getAllUserNotesBetweenDates(after, before).stream()
                .sorted(Comparator.comparing(UserNote::getDate))
                .filter(userNote -> userNote.getName().equals(user) && userNote.getEvent().equals(Event.LOGIN) && userNote.getStatus().equals(Status.OK))
                .map(UserNote::getDate)
                .findFirst()
                .orElse(null);
        if (date != null) {
            return date;
        } else {
            return null;
        }
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Date date = getAllUserNotesBetweenDates(after, before).stream()
                .sorted(Comparator.comparing(UserNote::getDate))
                .filter(userNote -> userNote.getName().equals(user) && userNote.getEvent().equals(Event.SOLVE_TASK) && userNote.getTaskNumber() == task)
                .map(UserNote::getDate)
                .findFirst()
                .orElse(null);
        if (date != null) {
            return date;
        } else {
            return null;
        }
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Date date = getAllUserNotesBetweenDates(after, before).stream()
                .sorted(Comparator.comparing(UserNote::getDate))
                .filter(userNote -> userNote.getName().equals(user) && userNote.getEvent().equals(Event.DONE_TASK) && userNote.getTaskNumber() == task)
                .map(UserNote::getDate)
                .findFirst()
                .orElse(null);
        if (date != null) {
            return date;
        } else {
            return null;
        }
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getName().equals(user) && userNote.getEvent().equals(Event.WRITE_MESSAGE))
                .map(UserNote::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getName().equals(user) && userNote.getEvent().equals(Event.DOWNLOAD_PLUGIN))
                .map(userNote -> userNote.getDate())
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .map(UserNote::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getIp().equals(ip))
                .map(UserNote::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getName().equals(user))
                .map(UserNote::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getStatus().equals(Status.FAILED))
                .map(UserNote::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getStatus().equals(Status.ERROR))
                .map(userNote -> userNote.getEvent())
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return (int) getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getTaskNumber() == task && userNote.getEvent().equals(Event.SOLVE_TASK))
                .count();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return (int) getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getTaskNumber() == task && userNote.getEvent().equals(Event.DONE_TASK))
                .count();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getEvent().equals(Event.SOLVE_TASK))
                .collect(Collectors.toMap(UserNote::getTaskNumber, userNote -> 1, Integer::sum));
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getEvent().equals(Event.DONE_TASK))
                .collect(Collectors.toMap(UserNote::getTaskNumber, userNote -> 1, Integer::sum));
    }

    @Override
    public Set<Object> execute(String query) {
        Set<Object> result = new HashSet<>();
        String type;
        String fieldType = null;
        Object fieldValue = "";
        Date after = null;
        Date before = null;

        if (query.contains("=")) {
            type = query.substring(4, query.indexOf("for") - 1);
            fieldType = query.substring(query.indexOf("for") + 4, query.indexOf("=") - 1);
            if (query.contains("and date between")) {
                fieldValue = query.substring(query.indexOf("=") + 3, query.indexOf("and date") - 2);
            } else {
                fieldValue = query.substring(query.indexOf("=") + 3, query.length() - 1);
            }

            switch (fieldType) {
                case "date": {
                    try {
                        fieldValue = formatter.parse((String) fieldValue);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "event": {
                    fieldValue = Event.valueOf((String) fieldValue);
                    break;
                }
                case "status": {
                    fieldValue = Status.valueOf((String) fieldValue);
                    break;
                }
            }
        } else {
            type = query.substring(4);
        }

        if (query.contains("and date between")) {
            String dateInterval = query.substring(query.indexOf("between ") + 9);
            try {
                after = formatter.parse(dateInterval.substring(0, dateInterval.indexOf("and") - 2));
                before = formatter.parse(dateInterval.substring(dateInterval.indexOf("and") + 5, dateInterval.length() - 1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        switch (type) {
            case "ip": {
                result = getAllIpsForQuery(fieldType, fieldValue, after, before);
                break;
            }
            case "user": {
                result = getAllUsersForQuery(fieldType, fieldValue, after, before);
                break;
            }
            case "date": {
                result = getAllDatesForQuery(fieldType, fieldValue, after, before);
                break;
            }
            case "event": {
                result = getAllEventsForQuery(fieldType, fieldValue, after, before);
                break;
            }
            case "status": {
                result = getAllStatusesForQuery(fieldType, fieldValue, after, before);
                break;
            }
        }
        return result;
    }

    private Set<Object> getAllIpsForQuery(String fieldType, Object fieldValue, Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getByFieldType(fieldType).equals(fieldValue))
                .map(UserNote::getIp)
                .collect(Collectors.toSet());
    }

    private Set<Object> getAllUsersForQuery(String fieldType, Object fieldValue, Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getByFieldType(fieldType).equals(fieldValue))
                .map(UserNote::getName)
                .collect(Collectors.toSet());
    }

    private Set<Object> getAllDatesForQuery(String fieldType, Object fieldValue, Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getByFieldType(fieldType).equals(fieldValue))
                .map(UserNote::getDate)
                .collect(Collectors.toSet());
    }

    private Set<Object> getAllEventsForQuery(String fieldType, Object fieldValue, Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getByFieldType(fieldType).equals(fieldValue))
                .map(UserNote::getEvent)
                .collect(Collectors.toSet());
    }

    private Set<Object> getAllStatusesForQuery(String fieldType, Object fieldValue, Date after, Date before) {
        return getAllUserNotesBetweenDates(after, before).stream()
                .filter(userNote -> userNote.getByFieldType(fieldType).equals(fieldValue))
                .map(UserNote::getStatus)
                .collect(Collectors.toSet());
    }

    private List<UserNote> getAllUserNotes() {
        List<UserNote> result = new ArrayList<>();
        List<String[]> allNotes = getAllNotesFromLogs();
        for (String[] logNote : allNotes) {
            try {
                String userName = logNote[1];
                String ip = logNote[0];
                Date date = formatter.parse(logNote[2]);
                Event event;
                Integer taskNumber;
                if (logNote[3].contains(" ")) {
                    event = Event.valueOf(logNote[3].substring(0, logNote[3].indexOf(" ")));
                    taskNumber = Integer.parseInt(logNote[3].substring(logNote[3].indexOf(" ") + 1));
                } else {
                    event = Event.valueOf(logNote[3]);
                    taskNumber = 0;
                }
                Status status = Status.valueOf(logNote[4]);
                result.add(new UserNote(userName, ip, date, event, taskNumber, status));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    private List<UserNote> getAllUserNotesBetweenDates(Date after, Date before) {
        List<UserNote> result = new ArrayList<>();
        for (UserNote userNote : getAllUserNotes()) {
            if (checkAfterAndBeforeDates(after, before, userNote.getDate())) {
                result.add(userNote);
            }
        }
        return result;
    }

    private List<String[]> getAllNotesFromLogs() {
        File[] logFiles = logDir.toFile().listFiles();
        List<String[]> allNotes = new ArrayList<>();
        for (File logFile : logFiles) {
            if (logFile.getName().endsWith(".log")) {
                try (BufferedReader bf = new BufferedReader(new FileReader(logFile))) {
                    while (bf.ready()) {
                        String line = bf.readLine();
                        String[] parsedLine = line.split("\t");
                        allNotes.add(parsedLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return allNotes;
    }

    private boolean checkAfterAndBeforeDates(Date after, Date before, Date dateOfNote) {
        if (after == null && before == null || after == null && before != null && dateOfNote.before(before)
                || after != null && before == null && dateOfNote.after(after)
                || after != null && before != null && dateOfNote.before(before) && dateOfNote.after(after)) {
            return true;
        }
        return false;
    }

}
