package ru.yandex.javacource.sevagin.schedule.manager;

import ru.yandex.javacource.sevagin.schedule.task.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, SubTask> subtasks;
    private HashMap<Integer, Epic> epics;
    private int seq;
    private HistoryManager historyManager;

        public InMemoryTaskManager() {
            this.tasks = new HashMap<>();
            this.subtasks = new HashMap<>();
            this.epics = new HashMap<>();
            this.seq = 0;
            this.historyManager = Managers.getDefaultHistory();
        }

        private int generateId() {
            return ++seq;
        }

        @Override
        public List<Task> getAllTask() {
            return new ArrayList<>(tasks.values());
        }

        @Override
        public void deleteAllTask() {
            tasks.clear();
        }

        @Override
        public Task getTask(int id) {
            return tasks.get(id);
        }

        @Override
        public Task createTask(Task task) {
            task.setId(generateId());
            tasks.put(task.getId(), task);
            historyManager.add(task);
            return task;
        }

        @Override
        public void updateTask(Task task) {
            tasks.put(task.getId(), task);
            historyManager.add(task);
        }

        @Override
        public void deleteTask(int id) {
            tasks.remove(id);
        }

        @Override
        public List<SubTask> getAllSubTask() {
            return new ArrayList<>(subtasks.values());
        }

        @Override
        public void deleteAllSubTask(int epicId) {
            subtasks.clear();
            updateEpicStatus(epicId);
        }

        @Override
        public SubTask getSubTask(int id) {
            return subtasks.get(id);
        }

        @Override
        public Integer createSubTask(SubTask subTask) {
            subTask.setId(generateId());
            subtasks.put(subTask.getId(), subTask);
            historyManager.add(subTask);
            return subTask.getId();
        }

        @Override
        public void updateSubTask(SubTask subTask) {
            subtasks.put(subTask.getId(), subTask);
            historyManager.add(subTask);
        }

        @Override
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

        @Override
        public List<Epic> getAllEpic() {
            return new ArrayList<>(epics.values());
        }

        @Override
        public void deleteEpic() {
            epics.clear();
            subtasks.clear();
        }

        @Override
        public Epic getEpic(int id) {
            historyManager.add(epics.get(id));
            return epics.get(id);
        }

        @Override
        public Epic createEpic(Epic epic) {
            epic.setId(generateId());
            epics.put(epic.getId(), epic);
            historyManager.add(epic);
            return epic;
        }

        @Override
        public void updateEpic(Epic epic) {
            epic.setName(epic.getName());
            epic.setDescription(epic.getDescription());
            updateEpicStatus(epic.getId());
            historyManager.add(epic);
        }

        @Override
        public void deleteEpic(int id) {
            Epic epicRemove = epics.remove(id);
            if (epicRemove == null) {
                return;
            }
            for (SubTask subTask : epicRemove.getSubTasks()) {
                subtasks.remove(subTask.getId());
            }
        }

        @Override
        public List<SubTask> getSubTasksForEpic(Epic epic) {
            List<SubTask> subTasksForEpic = new ArrayList<>();
            for (SubTask subTask : subtasks.values()) {
                if (subTask.getEpicId() == epic.getId()) {
                    subTasksForEpic.add(subTask);
                }
            }
            return subTasksForEpic;
        }

        @Override
        public List<Task> getHistory() {
            return historyManager.getHistory();
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