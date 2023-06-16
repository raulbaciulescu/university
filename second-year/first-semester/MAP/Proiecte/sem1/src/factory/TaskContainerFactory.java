package factory;

import container.StackContainer;
import container.Strategy;

import java.awt.*;

public class TaskContainerFactory implements Factory{

    private static TaskContainerFactory instance = null;

    private TaskContainerFactory(){}

    public static TaskContainerFactory getInstance(){
        if (instance == null)
            instance = new TaskContainerFactory();
        return instance;
    }


    @Override
    public Container createContainer() {
        if (strategy == Strategy.LIFO)
            return new StackContainer();
        else
            //return new QueueContainer()
            return null;
    }
}
