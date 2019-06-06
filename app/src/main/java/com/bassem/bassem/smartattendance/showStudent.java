package com.bassem.bassem.smartattendance;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bassem.bassem.smartattendance.Model.AddInterface;
import com.bassem.bassem.smartattendance.Model.GetDataInterface;
import com.bassem.bassem.smartattendance.Model.Student;
import com.bassem.bassem.smartattendance.Model.Subject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class showStudent extends AppCompatActivity implements AddInterface {
TextView bassem;
Button show;
Calendar calendar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student);
        bassem=findViewById(R.id.showDate);
        show=findViewById(R.id.Show);
        calendar1=Calendar.getInstance();
        SimpleDateFormat ss=new SimpleDateFormat("dd-MM-yyyy");
        Date date=new Date();
        final String currentDate=ss.format(date);

        SharedPreferences currentSharedPreferences = getSharedPreferences("Mark", MODE_PRIVATE);
        String email = currentSharedPreferences.getString("email", "");
        String name= currentSharedPreferences.getString("name","");
        HelperMethods.currentSubject.setEmail(email);
        final String id = HelperMethods.currentSubject.getEmail().replace(".","");;

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HelperMethods.pushInFireBase( "Subject","0","date","5-5-2019","emails",HelperMethods.currentSubject,showStudent.this,"plz wait","loading","0","date",currentDate,"emails",id);


            }
        });

    }


    @Override
    public void updateUI(DatabaseError databaseError) {

    }
}
