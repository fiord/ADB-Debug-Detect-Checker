package com.fiord.debugchecker;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.provider.Settings;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.fiord.debugchecker.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        if (container == null) {
            return null;
        }
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public int DebugResultIcon(int res) {
        switch (res) {
            case 0:
                return android.R.drawable.presence_busy;
            case 1:
                return android.R.drawable.presence_online;
            default:
                return android.R.drawable.presence_offline;
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                int adb = Settings.Secure.getInt(context.getContentResolver(),  Settings.Global.ADB_ENABLED, 0);
                final TextView adb_enabled = (TextView) binding.getRoot().getViewById(R.id.adb_enabled);
                adb_enabled.setCompoundDrawablesWithIntrinsicBounds(0, 0, DebugResultIcon(adb), 0);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}