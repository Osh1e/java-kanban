package ru.yandex.javacource.sevagin.schedule.task;

import java.util.List;

public interface TaskManager {
    List<Task> getAllTask();

    void deleteAllTask();

    Task getTask(int id);

    Task createTask(Task task);

    void updateTask(Task task);

    void deleteTask(int id);

    List<SubTask> getAllSubTask();

    void deleteAllSubTask(int epicId);

    SubTask getSubTask(int id);

    Integer createSubTask(SubTask subTask);

    void updateSubTask(SubTask subTask);

    void deleteSubTask(int id);

    List<Epic> getAllEpic();

    void deleteEpic();

    Epic getEpic(int id);

    Epic createEpic(Epic epic);

    void updateEpic(Epic epic);

    void deleteEpic(int id);

    List<SubTask> getSubTasksForEpic(Epic epic);

    List<Task> getHistory();
}
