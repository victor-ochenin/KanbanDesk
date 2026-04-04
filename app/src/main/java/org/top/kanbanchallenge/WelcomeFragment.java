package org.top.kanbanchallenge;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.UUID;

public class WelcomeFragment extends Fragment {
    public WelcomeFragment() { }

    public static WelcomeFragment newInstance() {
        WelcomeFragment fragment = new WelcomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button openTestDeskButton = view.findViewById(R.id.openTestDeskButton);
        Button exitButton = view.findViewById(R.id.exitButton);

        openTestDeskButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            String deskID = UUID.randomUUID().toString().substring(0, 6);
            bundle.putString(ContentWrapperFragment.OPEN_DESK_REQUEST, deskID);
            getParentFragmentManager().setFragmentResult(ContentWrapperFragment.OPEN_DESK_REQUEST, bundle);
        });

        exitButton.setOnClickListener(v -> {
            getActivity().finish();
        });
    }
}