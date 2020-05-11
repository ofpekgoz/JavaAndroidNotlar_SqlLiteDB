package com.omerfpekgoz.notlaruygulamasisqllite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class NotKayitActivity extends AppCompatActivity {

    private Toolbar toolbar2;
    private EditText txtDersKayit;
    private EditText txtVizeNotKayit;
    private EditText txtFinalNotKayit;
    private Button btnKaydet;

    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_kayit);

        toolbar2=findViewById(R.id.toolbarDetay);
        txtDersKayit=findViewById(R.id.txtDersDetay);
        txtVizeNotKayit=findViewById(R.id.txtVizeNotDetay);
        txtFinalNotKayit=findViewById(R.id.txtFinalNotDetay);
        btnKaydet=findViewById(R.id.btnKaydet);

        toolbar2.setTitle("Not Kayıt");
        setSupportActionBar(toolbar2);

        dbHelper=new DbHelper(this);
        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dersAdi=txtDersKayit.getText().toString().trim();
                String notVize=txtVizeNotKayit.getText().toString().trim();
                String notFinal=txtFinalNotKayit.getText().toString().trim();

                if (TextUtils.isEmpty(dersAdi)){   //Ders adı boşluğunu doldurup doldurmadığını kontrol ediyoruz

                    Snackbar.make(view,"Ders Adı Giriniz",Snackbar.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(notVize)){   //Vize boşluğunu doldurup doldurmadığını kontrol ediyoruz

                    Snackbar.make(view,"Vize Notunu Giriniz",Snackbar.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(notFinal)){   //Final  adı boşluğunu doldurup doldurmadığını kontrol ediyoruz

                    Snackbar.make(view,"Final Notunu Giriniz",Snackbar.LENGTH_SHORT).show();
                    return;

                }

                 new NotlarDao().notEkle(dbHelper,dersAdi,Integer.parseInt(notVize),Integer.parseInt(notFinal));

                startActivity(new Intent(NotKayitActivity.this,MainActivity.class));
                finish();

            }
        });
    }
}
