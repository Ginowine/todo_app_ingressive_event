package ingressive.tutorial.com.todolistapp.models;

public class Task {

    private String newTask;

    public Task(){

    }

    public Task(String todo){
        setNewTask(todo);
    }


    public String getNewTask() {
        return newTask;
    }

    public void setNewTask(String newTask) {
        this.newTask = newTask;
    }
}
