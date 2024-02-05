package ru.yandex.javacource.sevagin.schedule.manager;

import org.junit.jupiter.api.Test;
import ru.yandex.javacource.sevagin.schedule.manager.InMemoryTaskManager;
import ru.yandex.javacource.sevagin.schedule.task.Epic;
import ru.yandex.javacource.sevagin.schedule.task.SubTask;
import ru.yandex.javacource.sevagin.schedule.task.Task;
import ru.yandex.javacource.sevagin.schedule.task.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    @Test
    void taskManagerAddsFindsDifferentTypes() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        Task task = new Task("Задача", TaskStatus.NEW, "Описание");
        Epic epic = new Epic("Эпик", TaskStatus.NEW, "Описание");
        SubTask subTask = new SubTask("Описание", TaskStatus.NEW, "Описание", epic.getId());

        taskManager.createTask(task);
        taskManager.createSubTask(subTask);
        taskManager.createEpic(epic);

        assertEquals(task, taskManager.getTask(task.getId()));
        assertEquals(subTask, taskManager.getSubTask(subTask.getId()));
        assertEquals(epic, taskManager.getEpic(epic.getId()));
    }
}