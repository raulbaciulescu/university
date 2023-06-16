package runner;


import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.xml.crypto.Data;
import java.time.LocalDateTime;

import static utils.Constants.DATE_TIME_FORMATTER;

public class PrinterTaskRunner extends AbstractTaskRunner {

    public PrinterTaskRunner(@NotNull TaskRunner taskRunner){
        super(taskRunner);
    }

    @Override
    public void executeOneTask() {
        this.taskRunner.executeOneTask();
        decorateExecuteOneTask();
    }

    private void decorateExecuteOneTask(){
        System.out.println("Executed task: " + LocalDateTime.now().format(DATE_TIME_FORMATTER));
    }
}
