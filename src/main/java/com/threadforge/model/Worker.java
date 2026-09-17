package com.threadforge.model;

public class Worker {

    private final String workerId;
    private String workerName;
    private boolean busy;

    public Worker(String workerId, String workerName) {
        this.workerId = workerId;
        this.workerName = workerName;
        this.busy = false;
    }

    public String getWorkerId() {
        return workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "workerId='" + workerId + '\'' +
                ", workerName='" + workerName + '\'' +
                ", busy=" + busy +
                '}';
    }
}