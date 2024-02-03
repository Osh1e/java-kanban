import org.junit.jupiter.api.Test;
import ru.yandex.javacource.sevagin.schedule.manager.InMemoryHistoryManager;
import ru.yandex.javacource.sevagin.schedule.task.Task;
import ru.yandex.javacource.sevagin.schedule.task.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    @Test
    void tasksRetainedInHistory() {
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        Task task = new Task("Задача", TaskStatus.NEW, "Описание");
        historyManager.add(task);

        assertFalse(historyManager.getHistory().isEmpty(), "История не должна быть пустая после добавления задачи");
        assertTrue(historyManager.getHistory().contains(task), "Добавленные задачи будут представлены в истории");
    }
}