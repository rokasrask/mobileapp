package com.example.moksleivis.mobileapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ClientDetails extends AppCompatActivity {
    DbAdapter db;
    String id,vardas,numeris,data,tipas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_contact);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        vardas = intent.getStringExtra("VARDAS");
        numeris = intent.getStringExtra("NUMERIS");
        data = intent.getStringExtra("DATA");
        tipas = intent.getStringExtra("TIPAS");
        ((TextView) findViewById(R.id.vardas)).setText(vardas);
        ((TextView) findViewById(R.id.numeris)).setText(numeris);
        ((TextView) findViewById(R.id.data)).setText(data);
        ((TextView) findViewById(R.id.tipas)).setText(tipas);
        //calling DbAdapter
        db = new DbAdapter(this);
        db.open();
    }
    public void Edit(View v){
        //go to EditContact page
        Intent edit = new Intent(ClientDetails.this, EditContact.class);
        edit.putExtra("ID", id);
        edit.putExtra("VARDAS", vardas);
        edit.putExtra("NUMERIS", numeris);
        edit.putExtra("DATA", data);
        edit.putExtra("TIPAS",tipas);
        startActivity(edit);
    }
    public void Delete(View v){
        db.delete(Integer.parseInt(id));
        Toast.makeText(getApplicationContext(),"deleted", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
