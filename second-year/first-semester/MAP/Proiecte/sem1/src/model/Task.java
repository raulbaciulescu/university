package model;

import java.util.Objects;

public abstract class Task {


    protected String taskId;
    protected String description;

    public Task(String taskId, String description) {
        this.taskId = taskId;
        this.description = description;
    }
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public abstract void execute();

    @Override
    public String toString() {
        return taskId + " " + description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getTaskId(), this.getDescription());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Task))
            return false;
        Task task = (Task) o;

        return Objects.equals(this.getTaskId(), task.getTaskId()) &&  Objects.equals(this.getDescription(), task.getDescription());
    }

}
