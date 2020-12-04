package ru.franq.exampleapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import ru.franq.exampleapplication.R;
import ru.franq.exampleapplication.fragment.HomeFragmentDirections;
import ru.franq.exampleapplication.model.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private final List<Task> taskList;
    private Context context;

    public TaskAdapter(List<Task> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final TextView tvDate;
        private final TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvListItemTitle);
            tvDate = itemView.findViewById(R.id.tvListItemDate);
            tvTime = itemView.findViewById(R.id.tvListItemTime);
        }

        public void bind(int pos){
            Task task = taskList.get(pos);

            tvTitle.setText(task.getTitle());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

            tvDate.setText(dateFormat.format(task.getDate()));
            tvTime.setText(timeFormat.format(task.getDate()));

            itemView.setOnClickListener(v -> {
                HomeFragmentDirections.ActionToNoteFragment action =
                        HomeFragmentDirections.actionToNoteFragment(task);
                Navigation.findNavController(v).navigate(action);
            });
        }


    }

}
