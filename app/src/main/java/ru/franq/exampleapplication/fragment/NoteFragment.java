package ru.franq.exampleapplication.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.franq.exampleapplication.R;
import ru.franq.exampleapplication.model.Task;


public class NoteFragment extends Fragment {

    private TextView tvTitle;
    private TextView tvDate;
    private TextView tvDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_note, container, false);

        tvTitle = view.findViewById(R.id.tvTitle);
        tvDate = view.findViewById(R.id.tvDate);
        tvDescription = view.findViewById(R.id.tvDescription);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Task task = NoteFragmentArgs.fromBundle(getArguments()).getTaskItem();

        tvTitle.setText(task.getTitle());
        tvDate.setText("-");
        tvDescription.setText(task.getDescription());

    }
}