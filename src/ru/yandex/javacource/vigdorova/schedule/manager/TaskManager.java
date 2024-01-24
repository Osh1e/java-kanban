package ru.yandex.javacource.vigdorova.schedule.manager;

import ru.yandex.javacource.vigdorova.schedule.task.Epic;
import ru.yandex.javacource.vigdorova.schedule.task.SubTask;
import ru.yandex.javacource.vigdorova.schedule.task.Task;
import ru.yandex.javacource.vigdorova.schedule.task.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, SubTask> subtasks;
    private HashMap<Integer, Epic> epics;
    private int seq;

        public TaskManager() {
            this.tasks = new HashMap<>();
            this.subtasks = new HashMap<>();
            this.epics = new HashMap<>();
            this.seq = 0;
        }

        private int generateId() {
            return ++seq;
        }
        // ru.yandex.javacource.vigdorova.schedule.task.Task
        public List<Task> getAllTask() {
            return new ArrayList<>(tasks.values());
        }

        public void deleteAllTask() {
            tasks.clear();
        }

        public Task getTask(int id) {
            return tasks.get(id);
        }

        public Task createTask(Task task) {
            task.setId(generateId());
            tasks.put(task.getId(), task);
            return task;
        }

        public void updateTask(Task task) {
            tasks.put(task.getId(), task);
            int id = task.getId();
            Task savedTask = tasks.get(id);
            if(savedTask == null) {
                return;
            }
        }

        public void deleteTask(int id) {
            tasks.remove(id);
        }
        // ru.yandex.javacource.vigdorova.schedule.task.SubTask
        public List<SubTask> getAllSubTask() {
            return new ArrayList<>(subtasks.values());
        }

        public void deleteAllSubTask(int epicId) {
            subtasks.clear();
            updateEpicStatus(epicId);
        }

        public SubTask getSubTask(int id) {
            return subtasks.get(id);
        }

        public Integer createSubTask(SubTask subTask) {
            int epicId = subTask.getEpicId();
            Epic epic = epics.get(epicId);
            if (epic == null) {
                return null;
            }
            int id = generateId();
            subTask.setId(id);
            subtasks.put(id, subTask);
            epic.addSubTaskId(subTask.getId());
            updateEpicStatus(epic.getId());
            return id;
        }

        public void updateSubTask(SubTask subTask) {
            int id = subTask.getId();
            int epicId = subTask.getEpicId();
            SubTask savedSubTask = subtasks.get(id);
                if (savedSubTask == null) {
                    return;
                }
                Epic epic = epics.get(epicId);
                if (epic == null) {
                    return;
                }
                subtasks.put(id, subTask);
                updateEpicStatus(epic.getId());
        }

        public void deleteSubTask(int id) {
            SubTask subTask = subtasks.remove(id);
                if (subTask != null) {
                    Epic epic = getEpic(subTask.getEpicId());
                    if (epic != null) {
                        epic.removeSubTaskId(subTask.getId());
                        updateEpicStatus(epic.getId());
                    }
                }
        }
        // ru.yandex.javacource.vigdorova.schedule.task.Epic
        public List<Epic> getAllEpic() {
            return new ArrayList<>(epics.values());
        }

        public void deleteEpic() {
            epics.clear();
            subtasks.clear();
        }

        public Epic getEpic(int id) {
            return epics.get(id);
        }

        public Epic createEpic(Epic epic) {
            epic.setId(generateId());
            epics.put(epic.getId(), epic);
            return epic;
        }

        public void updateEpic(Epic epic) {
            epic.setName(epic.getName());
            epic.setDescription(epic.getDescription());
            updateEpicStatus(epic.getId());
        }

        public void deleteEpic(int id) {
            Epic epicRemove = epics.remove(id);
            if (epicRemove == null) {
                return;
            }
            for (SubTask subTask : epicRemove.getSubTasks()) {
                subtasks.remove(subTask.getId());
            }
        }

        public List<SubTask> getSubTasksForEpic(Epic epic) {
            List<SubTask> subTasksForEpic = new ArrayList<>();
            for (SubTask subTask : subtasks.values()) {
                if (subTask.getEpicId() == epic.getId()) {
                    subTasksForEpic.add(subTask);
                }
            }
            return subTasksForEpic;
        }

        private void updateEpicStatus(int id) {
            Epic epic = epics.get(id);
            if (epic != null) {
                List<Integer> subTaskIds = epic.getSubTaskIds();
                TaskStatus epicStatus = TaskStatus.NEW;

                for (Integer subTaskId : subTaskIds) {
                    SubTask subTask = subtasks.get(subTaskId);
                    if (subTask != null) {
                        if (subTask.getStatus() == TaskStatus.IN_PROGRESS) {
                            epicStatus = TaskStatus.IN_PROGRESS;
                            break;
                        } else if (subTask.getStatus() == TaskStatus.DONE) {
                            epicStatus = TaskStatus.DONE;
                        }
                    }
                }
                epic.setStatus(epicStatus);
            }
        }
}