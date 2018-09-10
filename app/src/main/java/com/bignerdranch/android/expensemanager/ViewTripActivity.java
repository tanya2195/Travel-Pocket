package com.bignerdranch.android.expensemanager;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewTripActivity extends ListActivity {



    ArrayList screens;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip);


        SQLiteDatabase database=openOrCreateDatabase("ProDB", MODE_APPEND, null);
        String query="select * from tripDetails";
        Cursor c=database.rawQuery(query, null);
        screens=new ArrayList();


String name,fr,to;

        while(c.moveToNext())
        {
            name=c.getString(0);
            fr=c.getString(1);
            to=c.getString(2);
            screens.add(name+"     "+"    "+fr+"    "+"to"+"    "+"     "+to);




        }database.close();
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,screens);
        setListAdapter(adapter);


    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(this,"tripID selected"+screens.get(position),Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(ViewTripActivity.this, TripActivity.class);
        String i=screens.get(position).toString();
        int ck=0;

        intent.putExtra("key",i);
        startActivity(intent);



        SQLiteDatabase db=openOrCreateDatabase("ProDB",MODE_APPEND,null);

        String q="select * from tripDetails where tripID=' " + screens.get(position)+" ' "
                ;
        String ph=null;
        Cursor c=db.rawQuery(q,null);
        if(c.moveToNext())

        {
            ph=c.getString(1);

        }
        Toast.makeText(this,"Phone"+ph,Toast.LENGTH_SHORT).show();

    }
}
