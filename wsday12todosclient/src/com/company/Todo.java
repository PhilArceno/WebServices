package com.company;

enum Status {
    pending, done
}

public class Todo {
    String task;
    String dueDate;
    Status status;

    public Todo(String task, String dueDate, Status status) {
        this.task = task;
        this.dueDate = dueDate;
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "task='" + task + '\'' +
                ", dueDate=" + dueDate +
                ", status=" + status +
                '}';
    }
}
