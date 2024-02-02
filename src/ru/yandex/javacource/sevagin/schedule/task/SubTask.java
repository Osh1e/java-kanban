package ru.yandex.javacource.sevagin.schedule;

public class SubTask extends Task {
    private int epicId;

    public SubTask(String name, TaskStatus status, String description, int epicId) {
        super(name, status, description);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
}