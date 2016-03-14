package hu.ait.android.todolist.touch;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import hu.ait.android.todolist.adapter.TodoRecyclerAdapter;

/**
 * Created by joe on 10/19/15.
 */
public class TodoListTouchHelper extends ItemTouchHelper.Callback {

    private TodoRecyclerAdapter adapter;

    public TodoListTouchHelper(TodoRecyclerAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView,
                          RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        adapter.swapTodos(viewHolder.getAdapterPosition(), target.getAdapterPosition());

        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.removeTodo(viewHolder.getAdapterPosition());
    }
}
