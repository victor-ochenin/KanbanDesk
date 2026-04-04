package org.top.kanbanchallenge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ContentWrapperFragment extends Fragment {

    public static final String OPEN_DESK_REQUEST = "open_desk";

    public ContentWrapperFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content_wrapper, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button serviceButton = view.findViewById(R.id.serviceButton);
        serviceButton.setOnClickListener(v -> {
            if (Math.random() > 0.5) {
                showContentFragment(R.layout.fragment_desk, "FROM-SERVICE-BTN-TEST");
            } else {
                showContentFragment(R.layout.fragment_welcome, null);
            }
        });

        getParentFragmentManager().setFragmentResultListener(OPEN_DESK_REQUEST, this, (key, bundle) -> {
            String openDeskArg = bundle.getString(key);
            showContentFragment(R.layout.fragment_desk, openDeskArg);
        });

        // TODO: будет открываться каждый раз Welcome что не ОК, надо учитывать состояния bundle
        showContentFragment(R.layout.fragment_welcome, null);
    }

    // showContentFragment - вывод указанного фрагмента
    // реализовано простым способом через ветвление
    // можно подумать над обощенной реализацией (без проверки типа)
    private void showContentFragment(int fragmentID, Object arg) {
        Fragment fragment;
        if (fragmentID == R.layout.fragment_desk) {
            String deskID = (String)arg;
            fragment = DeskFragment.newInstance(deskID);              // создаем доску
        } else if (fragmentID == R.layout.fragment_welcome) {
            fragment = WelcomeFragment.newInstance();           // создаем welcome
        } else {
            // TODO: можно добавить и другие корневые фрагменты
            throw new IllegalStateException("unsupported fragment id");
        }

        // отобразить фрагмент программно
        FragmentTransaction ftx = getParentFragmentManager().beginTransaction();
        ftx.replace(R.id.contentFragmentContainerView, fragment);
        ftx.commit();
    }
}
