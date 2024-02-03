package ru.yandex.javacource.sevagin.schedule.task;

import ru.yandex.javacource.sevagin.schedule.task.Task;

import java.util.List;
public interface HistoryManager {
    void add(Task task);
    List<Task> getHistory();
}
