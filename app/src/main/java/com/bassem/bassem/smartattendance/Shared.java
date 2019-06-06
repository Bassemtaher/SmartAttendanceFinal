package com.bassem.bassem.smartattendance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by bassem on 17/04/2019.
 */

public class Shared {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int mode =0;
    String fileName="sdfile";
    String Data="b";

    public Shared(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences(fileName,mode);
        editor=sharedPreferences.edit();

    }

    public void secondTime()
    {
        editor.putBoolean(Data,true);
        editor.commit();

    }

    public void firstTime()
    {
        if (!this.login())
        {
            Intent intent=new Intent(context,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }

    }

    private boolean login()
    {
        return sharedPreferences.getBoolean(Data,false);
    }
}
