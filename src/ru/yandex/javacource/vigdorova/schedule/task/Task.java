package ru.yandex.javacource.vigdorova.schedule.task;

import java.util.ArrayList;
import java.util.Objects;

    public class Task {
        private int id;
        private String name;
        private TaskStatus status;
        private String description;

        public Task(String name, TaskStatus status, String description) {
            this.id = getId();
            this.name = name;
            this.status = status;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public TaskStatus getStatus() {
            return status;
        }

        public void setStatus(TaskStatus status) {
            this.status = status;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public ArrayList<SubTask> getSubTasks() {
            return new ArrayList<>();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Task task = (Task) o;
            return id == task.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "ru.yandex.javacource.vigdorova.schedule.task.Task{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", status=" + status +
                    ", description='" + description + '\'' +
                    '}';
        }
    }