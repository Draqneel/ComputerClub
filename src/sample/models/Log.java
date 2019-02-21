package sample.models;

import java.util.Date;

/**
 * Created by mezkresh on 16.02.2019.
 */
public class Log {
    private int logId;
    private int user;
    private int computer;
    private long date_from;
    private long date_to;

    public Log(int user, int computer) {
        this.user = user;
        this.computer = computer;
        this.logId = String.valueOf(new Date().getTime()).hashCode();
    }

    public Log(int logId, int user, int computer, long date_from, long date_to) {
        this.logId = logId;
        this.user = user;
        this.computer = computer;
        this.date_from = date_from;
        this.date_to = date_to;
    }

    @Override
    public String toString() {
        return logId + " " + user + " " + computer + " " + date_from + " " + date_to;
    }

    public void startSession(){
        this.date_from = new Date().getTime();
    }

    public void closeSession(){
        this.date_to = new Date().getTime();
    }
    public void setDate_from(long date_from) {
        this.date_from = date_from;
    }

    public void setDate_to(long date_to) {
        this.date_to = date_to;
    }

    public int getLogId() {
        return logId;
    }

    public int getUser() {
        return user;
    }

    public int getComputer() {
        return computer;
    }

    public long getDate_from() {
        return date_from;
    }

    public long getDate_to() {
        return date_to;
    }
}
