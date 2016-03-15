package hu.ait.android.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hu.ait.android.todolist.adapter.TodoRecyclerAdapter;
import hu.ait.android.todolist.touch.TodoListTouchHelper;

public class MainActivity extends AppCompatActivity {

    private Button btnSave;
    private EditText etTask;
    private Button btnDeleteAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final TodoRecyclerAdapter adapter = new TodoRecyclerAdapter(this);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDeleteAll = (Button) findViewById(R.id.btnDeleteAll);
        etTask = (EditText) findViewById(R.id.etAdditions);

        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager =
                new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        TodoListTouchHelper touchHelperCallback = new TodoListTouchHelper(
                adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(
                touchHelperCallback);
        touchHelper.attachToRecyclerView(recyclerView);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addTodo(etTask.getText().toString());
                etTask.setText("");
            }
        });

        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.removeAll();
            }
        });
    }
}