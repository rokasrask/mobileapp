package com.example.moksleivis.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditContact extends AppCompatActivity {
    DbAdapter db;
    String id,vardas,numeris,data,tipas;
    EditText etvardas,etnumeris,etdata,ettipas;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_contact);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        vardas = intent.getStringExtra("VARDAS");
        numeris = intent.getStringExtra("NUMBER");
        data = intent.getStringExtra("DATA");
        tipas = intent.getStringExtra("TIPAS");
        ((EditText) findViewById(R.id.vardas)).setText(vardas);
        ((EditText) findViewById(R.id.numeris)).setText(numeris);
        ((EditText) findViewById(R.id.data)).setText(data);
        ((EditText) findViewById(R.id.tipas)).setText(tipas);
        //calling DbAdapter
        db = new DbAdapter(this);
        db.open();
        //get data from text feld
        etvardas =(EditText)findViewById(R.id.vardas);
        etnumeris =(EditText)findViewById(R.id.numeris);
        etdata =(EditText)findViewById(R.id.data);
        ettipas = (EditText)findViewById(R.id.tipas);
        btnSave = (Button)findViewById(R.id.save);
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if(!Validation.isValidCredentials(etvardas.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Netinkamas Vardas tiktai raidės", Toast.LENGTH_LONG).show();
                }else if(!Validation.isValidNumeris(etnumeris.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Netinkamas numeris tiktai skaiciai", Toast.LENGTH_LONG).show();
                }else if(!Validation.isValidCredentials(ettipas.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Netinkamas tipas tiktai raidės", Toast.LENGTH_LONG).show();
                }else if(!Validation.isValidData(etdata.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Netinkama data nuo 1900 iki 2018", Toast.LENGTH_LONG).show();
                }else{// validated


                    vardas=etvardas.getText().toString();
                    numeris=etnumeris.getText().toString();
                    data=etdata.getText().toString();
                    tipas=ettipas.getText().toString();
                    db.insert(vardas,numeris,data,tipas);
                    Toast.makeText(getApplicationContext(),"Contact added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /**
    public void Save(View v){
        vardas=etvardas.getText().toString();
        numeris=etnumeris.getText().toString();
        data=etdata.getText().toString();
        tipas=ettipas.getText().toString();
        db.update(Integer.parseInt(id),vardas, numeris, data, tipas);
        Toast.makeText(getApplicationContext(),"Update success", Toast.LENGTH_SHORT).show();
    }**/
    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
