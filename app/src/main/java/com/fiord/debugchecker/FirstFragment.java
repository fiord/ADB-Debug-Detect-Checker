package com.fiord.debugchecker;

import android.app.Activity;
import android.app.ActivityManager;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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

                // the most simple one
                int adb = Settings.Secure.getInt(context.getContentResolver(),  Settings.Global.ADB_ENABLED, 0);
                TextView adb_enabled = (TextView) binding.getRoot().getViewById(R.id.adb_enabled);
                adb_enabled.setCompoundDrawablesWithIntrinsicBounds(0, 0, DebugResultIcon(adb),0);

                // with wifi detection. I think it won't work...
                int wifi = Settings.Secure.getInt(context.getContentResolver(), "adb_wifi_enabled", 0);
                adb_enabled = (TextView) binding.getRoot().getViewById(R.id.adb_wifi_enabled);
                adb_enabled.setCompoundDrawablesWithIntrinsicBounds(0, 0, DebugResultIcon(wifi),0);

                // too serious one
                int developer_mode = Settings.Secure.getInt(context.getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);
                adb_enabled = (TextView) binding.getRoot().getViewById(R.id.developer_mode_enabled);
                adb_enabled.setCompoundDrawablesWithIntrinsicBounds(0, 0, DebugResultIcon(developer_mode), 0);

                // get all processes. but it is impossible to detect adbd, because of application userspace.
//                try {
//                    ProcessBuilder processBuilder = new ProcessBuilder("ps", "-A");
//                    Process process = processBuilder.start();
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        Log.d("Running Processes", "()()" + line);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//                List<ActivityManager.RunningServiceInfo> runningServices = manager.getRunningServices(Integer.MAX_VALUE);
//                Log.d("Activity Processes", "num:" + runningServices.size());
//                for (ActivityManager.RunningServiceInfo service : runningServices) {
//                    Log.d("Activity Processes", "()" + service.service.getClassName());
//                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}