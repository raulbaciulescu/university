package container;

import model.Task;

import static utils.Constants.INITIAL_STACK_SIZE;

public class QueueContainer implements Container{
    private Task[] tasks;
    private int size;

    public QueueContainer(){
        tasks = new Task[INITIAL_STACK_SIZE];
        size = 0;
    }

    @Override
    public Task remove() {
        int i;
        for (i = 0; i < size - 1; i++)
            tasks[i] = tasks[i + 1];
        size--;
        return tasks[size];
    }

    @Override
    public void add(Task task) {
        int l = tasks.length;
        if(l == size){
            Task[] t = new Task[l*2];
            System.arraycopy(tasks,0,t,0,l);
            tasks = t;
        }
        tasks[size] = task;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
