package ingressive.tutorial.com.todolistapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ingressive.tutorial.com.todolistapp.models.Task;

public class MainActivity extends AppCompatActivity {


    private static final String REQUIRED = "Required";

    //Declare button and EditText Objects
    private Button addTaskButton;
    private EditText taskDescription;

    //Declare a variable to hold the Firebase database URL
    //public static final String FirebaseDatabaseURL = "https://todo-list-app-3938a.firebaseio.com";

    // Firebase database Reference
    private DatabaseReference mDatabase;


    // Declare variable to store input from EditText
    public String task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the Firebase database reference
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //get a reference to the Button and EditText Views using their ID's
        addTaskButton = (Button) findViewById(R.id.addTask_id);
        taskDescription = (EditText) findViewById(R.id.taskDescription_id);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNewTask();
            }
        });
    }

    public void writeNewTask(){
        final String taskToAdd = taskDescription.getText().toString();
        if (TextUtils.isEmpty(taskToAdd)){
            taskDescription.setError(REQUIRED);
            return;
        }
        // Disable button so there are no multi-posts
        setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

        mDatabase.child("tasks").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null){
                    Task newTask = new Task(taskToAdd);
                    mDatabase.push().setValue(newTask);

                    setEditingEnabled(true);
                    finish();
                    Toast.makeText(MainActivity.this, "task added successfully!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, TodoList.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void setEditingEnabled(boolean enabled) {
        taskDescription.setEnabled(enabled);
        if (enabled) {
            addTaskButton.setVisibility(View.VISIBLE);
        } else {
            addTaskButton.setVisibility(View.GONE);
        }
    }
}
