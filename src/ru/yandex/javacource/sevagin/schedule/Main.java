package ru.yandex.javacource.sevagin.schedule;

import ru.yandex.javacource.sevagin.schedule.manager.InMemoryTaskManager;
import ru.yandex.javacource.sevagin.schedule.manager.TaskManager;
import ru.yandex.javacource.sevagin.schedule.task.*;

import java.util.List;

public class Main {
        public static void main(String[] args) {
            TaskManager inMemoryTaskManager = new InMemoryTaskManager();

            Task task1 = new Task("Выйти на пробежку" , TaskStatus.NEW, "Пробежать 10 км");

            inMemoryTaskManager.createTask(task1);

            Task retrievedTask = inMemoryTaskManager.getTask(task1.getId());
            System.out.println("Получена задача: " + retrievedTask.getName());

            Task updateTask = new Task("Выйти на пробежку и одеться теплее", TaskStatus.IN_PROGRESS, "Пробежать 10 км");
            updateTask.setId(task1.getId());
            inMemoryTaskManager.updateTask(updateTask);

            Task updateTaskResult = inMemoryTaskManager.getTask(task1.getId());
            System.out.println("Измененная задача: " + updateTaskResult.getName());

            List<Task> allTask = inMemoryTaskManager.getAllTask();
            System.out.println("\nСписок всех задач:");
            for (Task task : allTask) {
                System.out.println(task.getName());
            }

            inMemoryTaskManager.deleteTask(task1.getId());

            List<Task> allTaskAfterDelete = inMemoryTaskManager.getAllTask();
            System.out.println("\nСписок всех задач после удаления:");
            for (Task task : allTaskAfterDelete) {
                System.out.println(task.getName());
            }

            Epic epic = new Epic("Проект", TaskStatus.NEW, "Описание проекта");

            inMemoryTaskManager.createEpic(epic);

            SubTask subTask1 = new SubTask("А", TaskStatus.NEW, "Описание А", epic.getId());
            SubTask subTask2 = new SubTask("Б", TaskStatus.NEW, "Описание Б", epic.getId());

            inMemoryTaskManager.createSubTask(subTask1);
            inMemoryTaskManager.createSubTask(subTask2);

            subTask1.setStatus(TaskStatus.DONE);
            inMemoryTaskManager.updateSubTask(subTask1);

            Epic updateEpicResult = inMemoryTaskManager.getEpic(epic.getId());
            System.out.println("\nСтатус эпика после обновления подзадачи: " + updateTaskResult.getStatus());

            System.out.println("\nИстория просмотров задач:");
            List<Task> history = inMemoryTaskManager.getHistory();
            for (Task task : history) {
                System.out.println(task.getName() + " (ID: " + task.getId() + ")");
            }
        }
}