package hu.ait.android.todolist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.ait.android.todolist.R;
import hu.ait.android.todolist.data.Todo;

/**
 * Created by joe on 10/15/15.
 */
public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Todo> todos;

    public TodoRecyclerAdapter(Context context) {
        this.context = context;
        todos = Todo.listAll(Todo.class);
        /*for (int i = 0; i < 20; i++) {
            Todo todo = new Todo("Todo " + i, false);
            todos.add(todo);
            todo.save();
        }*/
    }

    public void addTodo(String task) {
        Todo todo = new Todo(task, false);
        todos.add(todo);
        todo.save();
        notifyDataSetChanged();
    }

    public void removeTodo(int index) {
        //remove Todo from the DataBase
        todos.get(index).delete();

        //remove Todo from the list
        todos.remove(index);
        //notifyDataSetChanged();
        notifyItemRemoved(index);
    }

    public void removeAll() {
        todos.clear();
        Todo.deleteAll(Todo.class);
        notifyDataSetChanged();
    }

    public void swapTodos(int oldPosition, int newPosition) {
        if (oldPosition < newPosition) {
            for (int i = oldPosition; i < newPosition; i++) {
                Collections.swap(todos, i, i + 1);
            }
        } else {
            for (int i = oldPosition; i > newPosition; i--) {
                Collections.swap(todos, i, i - 1);
            }
        }
        notifyItemMoved(oldPosition, newPosition);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(context).inflate(
                R.layout.todo_row, parent, false
        );
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Todo todo = todos.get(position);
        holder.tvTodo.setText(todo.getTodo());
        holder.cdDone.setChecked(todo.isDone());
        holder.cdDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todo.setDone(((CheckBox)v).isChecked());
                todo.save();

                Toast.makeText(context,
                        todo.getTodo() + " finished",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTodo;
        private final CheckBox cdDone;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTodo = (TextView) itemView.findViewById(R.id.tvTodo);
            cdDone = (CheckBox) itemView.findViewById(R.id.cbDone);
        }
    }

}
