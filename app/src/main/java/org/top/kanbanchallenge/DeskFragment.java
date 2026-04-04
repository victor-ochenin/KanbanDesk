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

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView1.addItemDecoration(dividerItemDecoration);
        recyclerView2.addItemDecoration(dividerItemDecoration);
        recyclerView3.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView1);
    }

    public void onCreateTaskClick(View view) {
        column1Tasks.add(new TaskItem("Задача1", "Доделать приложение"));
        adapter1.notifyItemInserted(column1Tasks.size() - 1);
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, 0) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getBindingAdapterPosition();
            int toPosition = target.getBindingAdapterPosition();
            adapter1.moveItem(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };

    private List<TaskItem> getListForRecyclerView(RecyclerView rv) {
        if (rv == recyclerView1) return column1Tasks;
        if (rv == recyclerView2) return column2Tasks;
        if (rv == recyclerView3) return column3Tasks;
        return null;
    }

    private CustomAdapter getAdapterForRecyclerView(RecyclerView rv) {
        if (rv == recyclerView1) return adapter1;
        if (rv == recyclerView2) return adapter2;
        if (rv == recyclerView3) return adapter3;
        return null;
    }
}
