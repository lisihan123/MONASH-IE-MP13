package com.example.savethebird.ui.notifications;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.savethebird.R;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private Button mtbn_call;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        initView(root);
        
        return root;
    }

    private void initView(View view){
        mtbn_call = view.findViewById(R.id.btn_more_support);
        mtbn_call.setOnClickListener(new Listener());
    }

    private class Listener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            AlertDialog.Builder build = new AlertDialog.Builder(getContext());
            build.setTitle("Call").setMessage("Would you like to call Birdlife Australia?")
                    .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String number = "0405503888";
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            Uri data = Uri.parse("tel:" + number);
                            intent.setData(data);
                            startActivity(intent);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getContext(),"Cancel",Toast.LENGTH_SHORT).show();
                }
            }).show();


        }
    }


}