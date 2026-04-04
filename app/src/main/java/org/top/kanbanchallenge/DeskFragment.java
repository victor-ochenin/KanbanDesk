package org.top.kanbanchallenge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.top.kanbanchallenge.thingsForRecyclerView.CustomAdapter;
import org.top.kanbanchallenge.thingsForRecyclerView.TaskItem;

import java.util.ArrayList;
import java.util.List;

public class DeskFragment extends Fragment {
    protected List<TaskItem> taskList = new ArrayList<>();
    protected CustomAdapter customAdapter = new CustomAdapter(taskList);

    public DeskFragment() { }

    private final static String ARG_DESK_ID = "desk_id";

    public static DeskFragment newInstance(String deskID) {
        DeskFragment fragment = new DeskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DESK_ID, deskID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_desk, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button createTask = view.findViewById(R.id.actionButton);
        configureRecyclerView(view);

        createTask.setOnClickListener(this::onCreateTaskClick);

        String deskID = "NONE";
        if (savedInstanceState != null && savedInstanceState.getString(ARG_DESK_ID) != null) {
            deskID = savedInstanceState.getString(ARG_DESK_ID);
        }
        if (getArguments() != null && getArguments().getString(ARG_DESK_ID) != null) {
            deskID = getArguments().getString(ARG_DESK_ID);
        }

        TextView deskTitleTextView = view.findViewById(R.id.deskTitleTextView);
        deskTitleTextView.setText(String.format("DESK#%s", deskID));
    }

    public void configureRecyclerView(@NonNull View view){
        RecyclerView recyclerView1 = view.findViewById(R.id.recyclerView1);
        RecyclerView recyclerView2 = view.findViewById(R.id.recyclerView2);
        RecyclerView recyclerView3 = view.findViewById(R.id.recyclerView3);

        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView1.setAdapter(customAdapter);
    }

    public void onCreateTaskClick(View view) {
        taskList.add(new TaskItem("Задача1", "Доделать приложение"));
        customAdapter.updateData(new ArrayList<>(taskList));
    }
}
