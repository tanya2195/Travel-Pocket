package com.bignerdranch.android.expensemanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTripActivity extends AppCompatActivity {

    private EditText mEditText1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton1;
    private EditText mEditText2;
    private EditText mEditText3;
    private EditText mEditText4;
    private EditText mEditText5;
    private EditText mEditText6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        mEditText1=(EditText)findViewById(R.id.editText1);
        mEditText2=(EditText)findViewById(R.id.editText2);
        mEditText3=(EditText)findViewById(R.id.editText3);
        mEditText4=(EditText)findViewById(R.id.editText4);
        mEditText5=(EditText)findViewById(R.id.editText5);
        mEditText6=(EditText)findViewById(R.id.editText6);
        mButton1=(Button)findViewById(R.id.button1);
        mButton2=(Button)findViewById(R.id.button2);
        mButton3=(Button)findViewById(R.id.button3);

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase database=openOrCreateDatabase("ProDB", MODE_APPEND, null);

                database.execSQL("create table if not exists tripDetails(tripID integer primary key, source varchar, destination varchar,  dateOfStart date, dateOfEnd date, approvedBudget varchar) ");

                String tripID=mEditText1.getText().toString();
                String source=mEditText2.getText().toString();
                String destination=mEditText3.getText().toString();
                String dateOfStart=mEditText4.getText().toString();
                String dateOfEnd=mEditText5.getText().toString();
                String approvedBudget=mEditText6.getText().toString();

                database.execSQL("insert into tripDetails(tripID, source, destination, dateOfStart, dateOfEnd, approvedBudget) " +
                        "values('"+tripID+"','"+source+"','"+destination+"', '"+dateOfStart+"', '"+dateOfEnd+"', '"+approvedBudget+"')");

                String q="select * from tripDetails";
                Cursor c=database.rawQuery(q,null);
                String s1,s2,s3,s4,s5,s6;

                while(c.moveToNext())
                {
                    s1=c.getString(0);
                    s2=c.getString(1);
                    s3=c.getString(2);
                    s4=c.getString(3);
                    s5=c.getString(4);
                    s6=c.getString(5);

                    Toast.makeText(AddTripActivity.this, " " + s1 + "\n" + s2 + "\n" + s3 + "\n" + s4 + "\n" + s5 + "\n" + s6, Toast.LENGTH_SHORT).show();

                }


            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mEditText1.getText().clear();
                mEditText2.getText().clear();
                mEditText3.getText().clear();
                mEditText4.getText().clear();
                mEditText5.getText().clear();
                mEditText6.getText().clear();
            }
        });

        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* SQLiteDatabase database=openOrCreateDatabase("ProDB", MODE_APPEND, null);
                String query="select * from tripDetails";
                Cursor c=database.rawQuery(query, null);
                String tripID;
                c.moveToNext();
                tripID=c.getString(0);*/
                Intent box=new Intent(AddTripActivity.this, AddExpenseActivity.class);
                //box.putExtra("tripID", tripID);
                startActivity(box);
            }
        });

    }
}
