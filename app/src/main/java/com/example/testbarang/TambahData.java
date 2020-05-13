package com.example.testbarang;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahData extends AppCompatActivity {
    private DatabaseReference database;
    private Button btnSubmit;
    private EditText etKode, etNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        etKode = findViewById(R.id.editNo);
        etNama = findViewById(R.id.editNama);
        btnSubmit = findViewById(R.id.btnOk);

        //mengambil referensi ke firebase database
        database = FirebaseDatabase.getInstance().getReference();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(etKode.getText().toString().isEmpty()) &&
                        !(etNama.getText().toString().isEmpty())){
                    submitBrg(new Barang(etKode.getText().toString(),
                            etNama.getText().toString()));
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(), "Data tidak boleh kosong", Toast.LENGTH_LONG).show();

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etKode.getWindowToken(), 0);
            }
        });
    }

    public void submitBrg(Barang brg){
        /*Berikut ini adalah kode yang digunakan untuk mengirimkan data
         * ke firebase RealTime Database dan juga kita set onSuccessListener
         * yang berisi kode yang akan dijalankan ketika data berhasil ditambahkan*/
        database.child("Barang").push().setValue(brg).addOnSuccessListener(this, new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void aVoid){
                etKode.setText("");
                etNama.setText("");
                Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan", Toast.LENGTH_LONG).show();
            }
        });
    }
//
    public static Intent getActIntent(Activity activity){
        //kode untuk pengambilan intent
        return new Intent(activity, TambahData.class);
    }
}