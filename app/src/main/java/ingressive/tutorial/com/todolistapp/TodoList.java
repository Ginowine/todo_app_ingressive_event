package ingressive.tutorial.com.todolistapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ingressive.tutorial.com.todolistapp.models.Task;

public class TodoList extends AppCompatActivity {

    private static final int NUM_LIST_ITEMS = 100;

    private TodoAdapter mAdapter;
    private RecyclerView mTodoList;

    private DatabaseReference mDatabase;

    List<Task> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mTodoList = (RecyclerView) findViewById(R.id.rv_todo);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mTodoList.setLayoutManager(layoutManager);
        mTodoList.setHasFixedSize(true);

        readTask();
    }


    public void readTask(){

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Task allTasks = snapshot.getValue(Task.class);
                    list.add(allTasks);
                }
                mAdapter = new TodoAdapter(TodoList.this, list);
                mTodoList.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//
//        mDatabase.child("tasks").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//
//                    Task allTask = snapshot.getValue(Task.class);
//                    list.add(allTask);
//                }
//
//                mAdapter = new TodoAdapter(TodoList.this, list);
//                mTodoList.setAdapter(mAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }
}
