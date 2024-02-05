package ru.yandex.javacource.sevagin.schedule.task;

import org.junit.jupiter.api.Test;
import ru.yandex.javacource.sevagin.schedule.task.Task;
import ru.yandex.javacource.sevagin.schedule.task.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    void tasksWithSameId() {
        Task task1 = new Task("Задача 1", TaskStatus.NEW, "Описание");
        Task task2 = new Task("Задача 2", TaskStatus.DONE, "Описание");

        assertEquals(task1, task2, "Задачи с одинаковым ID считаются идентичны");
    }
}