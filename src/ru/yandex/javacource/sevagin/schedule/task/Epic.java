package ru.yandex.javacource.sevagin.schedule;

import java.util.ArrayList;
    public class Epic extends Task {
        protected ArrayList<Integer> subTaskIds;

        public Epic(String name, TaskStatus status, String description) {
            super(name, status, description);
            this.subTaskIds = new ArrayList<>();
        }

        public ArrayList<Integer> getSubTaskIds() {
            return subTaskIds;
        }

        public void addSubTaskId(int subTaskId) {
            subTaskIds.add(subTaskId);
        }

        public void removeSubTaskId(int subTaskId) {
            subTaskIds.remove(subTaskId);
        }
}