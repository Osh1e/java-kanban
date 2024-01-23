import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TaskManager {
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, SubTask> subtasks;
    private HashMap<Integer, Epic> epics;
    int seq = 0;

    public TaskManager() {
        this.tasks = new HashMap<>();
        this.subtasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.seq = seq;
    }

    private int generateId() {
        return ++seq;
    }
    // Task
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
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }
    // SubTask
    public List<SubTask> getAllSubTask() {
        return new ArrayList<>(subtasks.values());
    }

    public void deleteAllSubTask() {
        subtasks.clear();
    }

    public SubTask getSubTask(int id) {
        return subtasks.get(id);
    }

    public SubTask createSubTask(SubTask subTask, Epic epic) {
        subTask.setId(generateId());
        subTask.setEpic(epic);
        subtasks.put(subTask.getId(), subTask);
        epic.addSubTask(subTask);
        manageEpicStatus(epic);
        return subTask;
    }

    public void updateSubTask(SubTask subTask) {
        subtasks.put(subTask.getId(), subTask);
        manageEpicStatus(subTask.getEpic());
    }

    public void deleteSubTask(int id) {
        SubTask subTask = subtasks.remove(id);
        if (subTask != null) {
            Epic epic = subTask.getEpic();
            if (epic != null) {
                epic.removeSubTask(subTask);
                manageEpicStatus(epic);
            }
        }
    }

    // Epic
    public List<Epic> getAllEpic() {
        return new ArrayList<>(epics.values());
    }

    public void deleteAllEpic() {
        epics.clear();
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public Epic createEpic(Epic epic) {
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    public void updateEpic(Epic epic, String name, String description) {
        epic.setName(name);
        epic.setDescription(description);
        manageEpicStatus(epic);
    }

    public void deleteEpic(Epic epic) {
        epics.remove(epic.getId());
        List<SubTask> subTasksToRemove = new ArrayList<>();
        for (SubTask subTask : subtasks.values()) {
            if (subTask.getEpic() != null && subTask.getEpic().equals(epic)) {
                subTasksToRemove.add(subTask);
            }
        }
        for (SubTask subTask : subTasksToRemove) {
            subtasks.remove(subTask.getId());
        }
    }

    public List<SubTask> getSubTasksForEpic(Epic epic) {
        List<SubTask> subTasksForEpic = new ArrayList<>();

        for (SubTask subTask : subtasks.values()) {
            if (subTask.getEpic() != null && subTask.getEpic().equals(epic)) {
                subTasksForEpic.add(subTask);
            }
        }
        return subTasksForEpic;
    }


    private void manageEpicStatus(Epic epic) {
        List<SubTask> subTasks = epic.getSubTasks();
        if (subTasks.isEmpty()) {
            epic.setStatus(TaskStatus.NEW);
        } else {
            for (SubTask subTask : subTasks) {
                if (subTask.getStatus() != TaskStatus.DONE) {
                    epic.setStatus(TaskStatus.IN_PROGRESS);
                    return;
                }
            }
            epic.setStatus(TaskStatus.DONE);
        }
    }
}
