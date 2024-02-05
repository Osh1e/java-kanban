package ru.yandex.javacource.sevagin.schedule.task;

import org.junit.jupiter.api.Test;
import ru.yandex.javacource.sevagin.schedule.task.Epic;
import ru.yandex.javacource.sevagin.schedule.task.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    @Test
    void epicStatus() {
        Epic epic = new Epic("Эпик", TaskStatus.NEW, "Описание");
        int subtaskId1 = 1;
        int subtaskId2 = 2;

        epic.addSubTaskId(subtaskId1);
        epic.addSubTaskId(subtaskId2);

        assertTrue(epic.getSubTaskIds().contains(subtaskId1), "Эпик хранит SubTask ID");
        assertTrue(epic.getSubTaskIds().contains(subtaskId2), "Эпик хранит SubTask ID");

        epic.removeSubTaskId(subtaskId1);

        assertFalse(epic.getSubTaskIds().contains(subtaskId1), "Эпик не хранит первый SubTask ID после удаления");
        assertTrue(epic.getSubTaskIds().contains(subtaskId2), "Эпик все еще хранит второй SubTask ID");
    }
}