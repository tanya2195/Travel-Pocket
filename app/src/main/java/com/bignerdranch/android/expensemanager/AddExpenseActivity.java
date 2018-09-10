package com.bignerdranch.android.expensemanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddExpenseActivity extends AppCompatActivity {

    String key;
    String eCategory;
    private EditText mEditText1;
    private EditText mEditText;
    private EditText mEditText2;
    private EditText mEditText3;
    private Spinner mSpinner1;
    private Button mButton1;
    private Button mButton2;
    String category[]={"Travelling", "Meal", "Lodging", "Miscellaneous"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        mButton1=(Button)findViewById(R.id.button1);
        mButton2=(Button)findViewById(R.id.button2);
        mEditText=(EditText)findViewById(R.id.editText);
        mEditText3=(EditText)findViewById(R.id.editText3);
        mEditText1=(EditText)findViewById(R.id.editText1);
        mEditText2=(EditText)findViewById(R.id.editText2);
        mSpinner1=(Spinner)findViewById(R.id.spinner1);

       /* Intent intent=getIntent();
        key=intent.getStringExtra("tripID");*/
        ArrayAdapter mArrayAdapter=new ArrayAdapter(AddExpenseActivity.this, android.R.layout.simple_spinner_item, category);
        mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner1.setAdapter(mArrayAdapter);
        mSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                eCategory=category[i];
                Toast.makeText(AddExpenseActivity.this, " " + category[i], Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(AddExpenseActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
            }
        });

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("TESTING", "**1");
                SQLiteDatabase database=openOrCreateDatabase("ProDB", MODE_APPEND, null);
                Log.d("TESTING", "**2");
                database.execSQL("create table if not exists expenseDetails(expenseID integer primary key autoincrement, category varchar, particulars varchar, amount real, date varchar, tripID integer)");
                //String query="delete from expenseDetails";
                database.execSQL("delete from expenseDetails");

                String amtSpend=mEditText1.getText().toString();
                String date=mEditText2.getText().toString();
                String particulars=mEditText.getText().toString();
                String tripID=mEditText3.getText().toString();
                Log.d("TESTING", "***1");
                System.out.print("\n"+ eCategory);
                Log.d("TESTING", "**3");
                database.execSQL("insert into expenseDetails(category, particulars,amount, date, tripID) " +
                        "values('"+eCategory+"', '"+particulars+"', '"+amtSpend+"', '"+date+"',  '"+tripID+"')" );
                Log.d("TESTING", "**4");
                String q="select * from expenseDetails";
                Log.d("TESTING", "**5");
                Cursor c=database.rawQuery(q,null);
                //Integer i1;
                Log.d("TESTING", "**6");
                String s1,s2,s3,s4,s5,s6,s7;
                while(c.moveToNext())
                {
                    Log.d("TESTING", "**7");
                    s7=c.getString(0);
                    Log.d("TESTING", "**8");
                    s1=c.getString(1);
                    s2=c.getString(2);
                    s3=c.getString(3);
                    s4=c.getString(4);
                    s5=c.getString(5);
                    //s6=c.getString(6);

                    Toast.makeText(AddExpenseActivity.this, " " + s7 + "\n"+s1+"\n"+s2+"\n"+s3+"\n"+s4+"\n"+s5+"\n", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}
