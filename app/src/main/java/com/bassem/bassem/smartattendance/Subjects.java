package com.bassem.bassem.smartattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bassem.bassem.smartattendance.Model.GetDataInterface;
import com.bassem.bassem.smartattendance.Model.Student;
import com.bassem.bassem.smartattendance.Model.Subject;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class Subjects extends AppCompatActivity implements GetDataInterface {
GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
        gridView=findViewById(R.id.gridview);

        HelperMethods.getData(Subjects.this,"Subject","0","date","27-05-2019","emails","please wait","Loading");



    }

    @Override
    public void updateUI(DataSnapshot data) {


        Log.i("data", "update ui : " + data.toString());
HelperMethods.subjectArrayList.clear();
        ArrayList<String> subjectEmails=new ArrayList<>();
        for (DataSnapshot currentChild : data.getChildren()) {
            Subject currentSubject = currentChild.getValue(Subject.class);
            subjectEmails.add(currentSubject.getEmail());

            HelperMethods.subjectArrayList.add(currentSubject);



        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, subjectEmails);
        gridView.setAdapter(adapter);


    }
}