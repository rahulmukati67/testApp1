package com.example.testapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Tab2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab2, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView Date1 = view.findViewById(R.id.edittxtDate1);
        TextView Date2 = view.findViewById(R.id.edittxtDate2);

        Date date = new Date();
        LocalDateTime localDateTime = null;
        String formattedDate = null;
        DateTimeFormatter formatter = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
            formattedDate = localDateTime.format(formatter);
        } else{
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
             formattedDate = sdf.format(date);
        }
        Date1.setText(formattedDate);
        Date2.setText(formattedDate);
    }
}