package org.top.kanbanchallenge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DeskFragment extends Fragment {

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
}
