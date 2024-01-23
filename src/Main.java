import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создаем объект TaskManager
        TaskManager taskManager = new TaskManager();

        // Создаем задачу
        Task task1 = new Task("Выйти на пробежку", TaskStatus.NEW, "Пробежать 10 км");

        // Добавляем задачу в TaskManager
        taskManager.createTask(task1);

        // Получаем и выводим задачу по идентификатору
        Task retrievedTask = taskManager.getTask(task1.getId());
        System.out.println("Полученная задача: " + retrievedTask.getName());

        // Изменяем задачу
        Task updatedTask = new Task("Выйти на пробежку и одеться теплее", TaskStatus.IN_PROGRESS, "Пробежать 10 км и одеться теплее");
        updatedTask.setId(task1.getId());
        taskManager.updateTask(updatedTask);

        // Получаем и выводим измененную задачу по идентификатору
        Task updatedTaskResult = taskManager.getTask(task1.getId());
        System.out.println("Измененная задача: " + updatedTaskResult.getName());

        // Получаем и выводим список всех задач
        List<Task> allTasks = taskManager.getAllTask();
        System.out.println("\nСписок всех задач:");
        for (Task task : allTasks) {
            System.out.println(task.getName());
        }

        // Удаляем задачу
        taskManager.deleteTask(task1.getId());

        // Получаем и выводим список всех задач после удаления
        List<Task> allTasksAfterDeletion = taskManager.getAllTask();
        System.out.println("\nСписок всех задач после удаления:");
        for (Task task : allTasksAfterDeletion) {
            System.out.println(task.getName());
        }
    }
}
