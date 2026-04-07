package org.top.kanbanchallenge;

import static org.top.kanbanchallenge.thingsForRecyclerView.SetupDropTargetKt.setupDropTarget;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.top.kanbanchallenge.thingsForRecyclerView.CustomAdapter;
import org.top.kanbanchallenge.thingsForRecyclerView.TaskItem;

import java.util.ArrayList;
import java.util.List;

public class DeskFragment extends Fragment {
    protected List<TaskItem> column1Tasks = new ArrayList<>();
    protected List<TaskItem> column2Tasks = new ArrayList<>();
    protected List<TaskItem> column3Tasks = new ArrayList<>();

    protected CustomAdapter adapter1;
    protected CustomAdapter adapter2;
    protected CustomAdapter adapter3;

    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;

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
        recyclerView1 = view.findViewById(R.id.recyclerView1);
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerView3 = view.findViewById(R.id.recyclerView3);

        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter1 = new CustomAdapter(column1Tasks);
        adapter2 = new CustomAdapter(column2Tasks);
        adapter3 = new CustomAdapter(column3Tasks);

        recyclerView1.setAdapter(adapter1);
        recyclerView2.setAdapter(adapter2);
        recyclerView3.setAdapter(adapter3);

        setupDropTarget(recyclerView1, adapter1);
        setupDropTarget(recyclerView2, adapter2);
        setupDropTarget(recyclerView3, adapter3);

    }

    public void onCreateTaskClick(View view) {
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.create_task, null);
        EditText headerEdit = dialogView.findViewById(R.id.taskHeaderEditText);
        EditText descriptionEdit = dialogView.findViewById(R.id.taskDescriptionEditText);

        new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.createTask))
                .setView(dialogView)
                .setPositiveButton(getString(R.string.save), (dialog, which) -> {
                    String header = headerEdit.getText().toString().trim();
                    String description = descriptionEdit.getText().toString().trim();
                    if (!header.isEmpty() || !description.isEmpty()) {
                        TaskItem newItem = new TaskItem(
                                header,
                                description,
                                adapter1
                        );
                        column1Tasks.add(newItem);
                        adapter1.notifyItemInserted(column1Tasks.size() - 1);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

}