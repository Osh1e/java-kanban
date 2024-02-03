import org.junit.jupiter.api.Test;
import ru.yandex.javacource.sevagin.schedule.task.SubTask;
import ru.yandex.javacource.sevagin.schedule.task.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class SubTaskTest {
    @Test
    void subtaskWithSameID() {
        SubTask subTask1 = new SubTask("Подзадача 1", TaskStatus.NEW, "Описание", 10);
        SubTask subTask2 = new SubTask("Подзадача 2", TaskStatus.DONE, "Описание", 10);

        assertEquals(subTask1, subTask1, "Подзадачи с одинаковым ID считаются идентичны");
    }
}