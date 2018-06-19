package com.example.moksleivis.mobileapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
public class MainActivity extends AppCompatActivity {

    //calling variables
    DbAdapter db;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //calling DbAdapter
        db = new DbAdapter(this);
        db.open();
        ListView lv = (ListView) findViewById(R.id.listView1);
        int layoutstyle=R.layout.liststyle;
        int[] xml_id = new int[] {
                R.id.txtname,
                R.id.txtnumber
        };
        String[] column = new String[] {
                "vardas",
                "numeris"
        };
        Cursor row = db.fetchAllData();
        adapter = new SimpleCursorAdapter(this, layoutstyle,row,column, xml_id, 0);
        lv.setAdapter(adapter);
        //onClick function
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterview, View view, int position, long id) {
                Cursor row = (Cursor) adapterview.getItemAtPosition(position);
                String _id = row.getString(row.getColumnIndexOrThrow("_id"));
                String vardas = row.getString(row.getColumnIndexOrThrow("vardas"));
                String numeris = row.getString(row.getColumnIndexOrThrow("numeris"));
                String data = row.getString(row.getColumnIndexOrThrow("data"));
                String tipas = row.getString(row.getColumnIndexOrThrow("tipas"));
                //go to detailsContact page
                Intent todetais = new Intent(MainActivity.this, ClientDetails.class);
                todetais.putExtra("ID",_id);
                todetais.putExtra("VARDAS", vardas);
                todetais.putExtra("NUMERIS",numeris);
                todetais.putExtra("DATA",data);
                todetais.putExtra("TIPAS",tipas);
                startActivity(todetais);
            }
        });
        //dispay data by filter
        EditText et = (EditText) findViewById(R.id.myFilter);
        et.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }
        });
        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return db.fetchdatabyfilter(constraint.toString(),"vardas");
            }
        });
    }
    public void addContact(View v){
        Intent addNewContact = new Intent(MainActivity.this, addClient.class);
        startActivity(addNewContact);


    }


}