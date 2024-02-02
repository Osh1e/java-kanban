import java.util.List;

public class Main {
        public static void main(String[] args) {
            TaskManager taskManager = new TaskManager();

            Task task1 = new Task("Выйти на пробежку" , TaskStatus.NEW, "Пробежать 10 км");

            taskManager.createTask(task1);

            Task retrievedTask = taskManager.getTask(task1.getId());
            System.out.println("Получена задача: " + retrievedTask.getName());

            Task updateTask = new Task("Выйти на пробежку и одеться теплее", TaskStatus.IN_PROGRESS, "Пробежать 10 км");
            updateTask.setId(task1.getId());
            taskManager.updateTask(updateTask);

            Task updateTaskResult = taskManager.getTask(task1.getId());
            System.out.println("Измененная задача: " + updateTaskResult.getName());

            List<Task> allTask = taskManager.getAllTask();
            System.out.println("\nСписок всех задач:");
            for (Task task : allTask) {
                System.out.println(task.getName());
            }

            taskManager.deleteTask(task1.getId());

            List<Task> allTaskAfterDelete = taskManager.getAllTask();
            System.out.println("\nСписок всех задач после удаления:");
            for (Task task : allTaskAfterDelete) {
                System.out.println(task.getName());
            }

            Epic epic = new Epic("Проект", TaskStatus.NEW, "Описание проекта");

            taskManager.createEpic(epic);

            SubTask subTask1 = new SubTask("А", TaskStatus.NEW, "Описание А", epic.getId());
            SubTask subTask2 = new SubTask("Б", TaskStatus.NEW, "Описание Б", epic.getId());

            taskManager.createSubTask(subTask1);
            taskManager.createSubTask(subTask2);

            subTask1.setStatus(TaskStatus.DONE);
            taskManager.updateSubTask(subTask1);

            Epic uddateEpicResult = taskManager.getEpic(epic.getId());
            System.out.println("\nСтатус эпика после обновления подзадачи: " + updateTaskResult.getStatus());
        }
}