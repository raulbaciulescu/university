import container.Strategy;
import models.MessageTask;
import org.jetbrains.annotations.NotNull;
import runner.PrinterTaskRunner;
import runner.StrategyTaskRunner;

import java.time.LocalDateTime;

public class Main {


    public static MessageTask[] createMessageTaskArray(){
        MessageTask t1 = new MessageTask("1", "MTask1", "Buna", "Andrei", "George", LocalDateTime.now());
        MessageTask t2 = new MessageTask("2", "MTask2", "Buna", "Andrei", "George", LocalDateTime.now());
        MessageTask t3 = new MessageTask("3", "MTask3", "Buna", "Andrei", "George", LocalDateTime.now());
        return new MessageTask[]{t1, t2, t3};
    }


    private static void runPrinterTaskRunner(){
        @NotNull final MessageTask[] messageTasks = createMessageTaskArray();
        @NotNull final StrategyTaskRunner strategyTaskRunner = new StrategyTaskRunner(Strategy.LIFO);

        for(@NotNull final MessageTask task : messageTasks){
            strategyTaskRunner.addTask(task);
        }
        PrinterTaskRunner printerTaskRunner = new PrinterTaskRunner(strategyTaskRunner);

        printerTaskRunner.executeOneTask();
    }
    public static void main(String[] args){

        MessageTask[] messageTasks = createMessageTaskArray();
        StrategyTaskRunner strategyTaskRunner = new StrategyTaskRunner(Strategy.LIFO);
        /*
        strategyTaskRunner.addTask(messageTasks[0]);
        strategyTaskRunner.addTask(messageTasks[1]);
        strategyTaskRunner.executeAll();

         */
        runPrinterTaskRunner();
    }


}
