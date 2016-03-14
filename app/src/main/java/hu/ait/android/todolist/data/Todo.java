package hu.ait.android.todolist.data;

import com.orm.SugarRecord;

/**
 * Created by joe on 10/15/15.
 */
public class Todo extends SugarRecord<Todo> {

    private String todo;
    private boolean done;

    public Todo() {
    }

    public Todo(String todo, boolean done) {
        this.todo = todo;
        this.done = done;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
