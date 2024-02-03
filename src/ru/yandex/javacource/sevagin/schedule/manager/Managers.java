package ru.yandex.javacource.sevagin.schedule.manager;

import ru.yandex.javacource.sevagin.schedule.task.HistoryManager;
import ru.yandex.javacource.sevagin.schedule.task.TaskManager;

public class Managers {
    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
