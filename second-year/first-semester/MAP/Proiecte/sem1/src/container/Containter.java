package container;

import model.Task;

public interface Containter {
    Task remove();
    void add(Task task);
    int size();
    boolean isEmpty();
}
