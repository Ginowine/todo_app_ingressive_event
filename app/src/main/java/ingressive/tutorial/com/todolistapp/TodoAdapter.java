package ingressive.tutorial.com.todolistapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ingressive.tutorial.com.todolistapp.models.Task;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    List<Task> listOfTasks;
    Context context;

    public TodoAdapter(Context context, List<Task> taskList){
        listOfTasks = taskList;
        this.context = context;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list_item, parent, false);
        TodoViewHolder viewHolder = new TodoViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
            Task tasks = listOfTasks.get(position);
            holder.listItemTodoView.setText(tasks.getNewTask());
    }

    @Override
    public int getItemCount() {
        return listOfTasks.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder{

        TextView listItemTodoView;

        public TodoViewHolder(View itemView){
            super(itemView);
            listItemTodoView = (TextView) itemView.findViewById(R.id.tv_list);

        }

    }
}
