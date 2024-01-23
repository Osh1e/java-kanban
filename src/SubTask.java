public class SubTask extends Task {
    private Epic epic;

    public SubTask(String name, TaskStatus status, String description, Epic epic) {
        super(name, status, description);
        this.epic = epic;
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }
}