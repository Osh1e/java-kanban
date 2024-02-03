import org.junit.jupiter.api.Test;
import ru.yandex.javacource.sevagin.schedule.manager.Managers;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {
    @Test
    void returnsManagers() {
        assertNotNull(Managers.getDefault(), "Обычная инициализация менеджера задач");
        assertNotNull(Managers.getDefaultHistory(), "Обычная инициализация менеджера истории");
    }
}