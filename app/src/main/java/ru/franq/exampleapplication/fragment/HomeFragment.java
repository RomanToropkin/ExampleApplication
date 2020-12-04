package ru.franq.exampleapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.franq.exampleapplication.R;
import ru.franq.exampleapplication.adapter.TaskAdapter;
import ru.franq.exampleapplication.model.Task;
import ru.franq.exampleapplication.util.FileService;


public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rvList);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new TaskAdapter(FileService.importTaskFromFile(getContext()), getContext()));

        return view;
    }

    private List<Task> generateList() {

        ArrayList<Task> list = new ArrayList<>();

        list.add(new Task("Помыть посуду", new Date(), "Грязной посуды накопилось очень много!"));
        list.add(new Task("Погулять с собакой", new Date(), "В парке Горького"));
        list.add(new Task("Сделать лабораторные", new Date(), "Ссылка на лабораторные в беседе"));
        list.add(new Task("Пойти на свидание", new Date(), "Не забудь взять с собой контрацепцию :)"));

        FileService.saveFile(getContext(), list);
        return list;
    }
}