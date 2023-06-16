package model;

import model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static utils.Constants.DATE_TIME_FORMATTER;

public class MessageTask extends Task {
    private String message;
    private String to;
    private String from;
    private LocalDateTime date;


    public MessageTask(String taskId, String description, String message, String from, String to, LocalDateTime date) {
        super(taskId, description);
        this.message = message;
        this.to = to;
        this.from = from;
        this.date = date;
    }




    @Override
    public void execute() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return super.toString() + " " + message + " " + from + " " + to + " " + date.format(DATE_TIME_FORMATTER);
    }
}
