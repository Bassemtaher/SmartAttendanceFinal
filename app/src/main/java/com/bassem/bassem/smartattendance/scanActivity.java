package com.bassem.bassem.smartattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.DateFormat;
import java.util.Calendar;

public class scanActivity extends AppCompatActivity {


    Button scan,show;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        scan = findViewById(R.id.scB);
        show = findViewById(R.id.shB);


        imageView = findViewById(R.id.imsc);



        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrator = new IntentIntegrator(scanActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setCameraId(0);
                integrator.setOrientationLocked(false);
                integrator.setPrompt("scanning");
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();


            }
        });
show.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        showstudent();
    }
});
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {


                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();


            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        Shared shared=new Shared(getApplicationContext());
        shared.firstTime();
    }
    public void showstudent()
    {
        Intent intent=new Intent(scanActivity.this,showStudent.class);
        startActivity(intent);
        finish();


    }
}
