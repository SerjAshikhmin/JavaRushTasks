package com.javarush.task.task39.task3913;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class UserNote {

    private String name;
    private String ip;
    private Date date;
    private Event event;
    private int taskNumber;
    private Status status;

    public UserNote() {
    }

    public UserNote(String name, String ip, Date date, Event event, int taskNumber, Status status) {
        this.name = name;
        this.ip = ip;
        this.date = date;
        this.event = event;
        this.taskNumber = taskNumber;
        this.status = status;
    }

    public Object getByFieldType(String fieldType) {
        if (fieldType == null) {
            return "";
        }
        switch (fieldType) {
            case "ip": {
                return ip;
            }
            case "user": {
                return name;
            }
            case "date": {
                return date;
            }
            case "event": {
                return event;
            }
            case "status": {
                return status;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNote userNote = (UserNote) o;
        return taskNumber == userNote.taskNumber &&
                Objects.equals(name, userNote.name) &&
                Objects.equals(ip, userNote.ip) &&
                Objects.equals(date, userNote.date) &&
                event == userNote.event &&
                status == userNote.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ip, date, event, taskNumber, status);
    }
}
