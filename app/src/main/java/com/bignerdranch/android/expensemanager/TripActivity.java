package com.bignerdranch.android.expensemanager;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class TripActivity extends ListActivity {
ArrayList screens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        Intent i=getIntent();
        String u=i.getStringExtra("key");
        int p=0;

        for(int i1=0;i1<u.length();i1++)
        {
            if(u.charAt(i1)==' ')
            {
                p=i1;break;
            }


        }
        String u1=u.substring(0,p);
        Toast.makeText(this,u1,Toast.LENGTH_SHORT).show();

        SQLiteDatabase database=openOrCreateDatabase("ProDB", MODE_APPEND, null);
        String q="select * from expenseDetails where tripID='"+u1+"'";


        Cursor c=database.rawQuery(q,null);


        screens=new ArrayList();


        String name,fr,to;

        while(c.moveToNext())
        {
            name=c.getString(0);
            fr=c.getString(1);
            to=c.getString(3);



            screens.add(fr+"    "+to);




        }database.close();
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,screens);
        setListAdapter(adapter);


    }
}
