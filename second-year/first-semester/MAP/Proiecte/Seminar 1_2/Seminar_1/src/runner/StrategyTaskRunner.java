package runner;

import container.Container;
import container.Strategy;
import factory.TaskContainerFactory;
import models.Task;

public class StrategyTaskRunner implements TaskRunner{
    private Container container;

    public StrategyTaskRunner(Strategy strategy){
        this.container = TaskContainerFactory.getInstance().createContainer(strategy);
    }

    @Override
    public void executeOneTask(){

        if (hasTask()){
            container.remove().execute();
        }
    }

    @Override
    public void executeAll(){
        while (hasTask())
            executeOneTask();
    }

    @Override
    public void addTask(Task t){
        container.add(t);
    }

    @Override
    public boolean hasTask(){
        return !container.isEmpty();
    }
}
