package com.omerfpekgoz.notlaruygulamasisqllite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class DetayActivity extends AppCompatActivity {

    private Toolbar toolbarDetay;
    private EditText txtDersDetay;
    private EditText txtVizeNotDetay;
    private EditText txtFinalNotDetay;

    private Notlar not;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        toolbarDetay=findViewById(R.id.toolbarDetay);
        txtDersDetay=findViewById(R.id.txtDersDetay);
        txtVizeNotDetay=findViewById(R.id.txtVizeNotDetay);
        txtFinalNotDetay=findViewById(R.id.txtFinalNotDetay);

        not= (Notlar) getIntent().getSerializableExtra("not");
        toolbarDetay.setTitle("Not Detay");
        setSupportActionBar(toolbarDetay);

        dbHelper=new DbHelper(this);

        txtDersDetay.setText(not.getDersAdi());
        txtVizeNotDetay.setText(String.valueOf(not.getNotVize()));
        txtFinalNotDetay.setText(String.valueOf(not.getNotFinal()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {   //Buarada menu yu gösterdik

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {  //Buarada menu deki item ları almak için

       switch (item.getItemId()){

           case R.id.menuSil:
               Snackbar.make(toolbarDetay,"Silinsin mi",Snackbar.LENGTH_SHORT).setAction("Evet", new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       new NotlarDao().notSil(dbHelper,not.getNotId());
                       startActivity(new Intent(DetayActivity.this,MainActivity.class));
                       finish();

                   }
               }).show();
               return true;

           case R.id.menuDuzenle:
               String dersAdi=txtDersDetay.getText().toString().trim();
               String notVize=txtVizeNotDetay.getText().toString().trim();
               String notFinal=txtFinalNotDetay.getText().toString().trim();

               if (TextUtils.isEmpty(dersAdi)){   //Ders adı boşluğunu doldurup doldurmadığını kontrol ediyoruz

                   Snackbar.make(toolbarDetay,"Ders Adı Giriniz",Snackbar.LENGTH_SHORT).show();
                   return false;

               }
               if (TextUtils.isEmpty(notVize)){   //Vize boşluğunu doldurup doldurmadığını kontrol ediyoruz

                   Snackbar.make(toolbarDetay,"Vize Notunu Giriniz",Snackbar.LENGTH_SHORT).show();
                   return false;

               }
               if (TextUtils.isEmpty(notFinal)){   //Final  adı boşluğunu doldurup doldurmadığını kontrol ediyoruz

                   Snackbar.make(toolbarDetay,"Final Notunu Giriniz",Snackbar.LENGTH_SHORT).show();
                   return false;

               }

               new NotlarDao().notDuzenle(dbHelper,not.getNotId(),dersAdi,Integer.parseInt(notVize),Integer.parseInt(notFinal));

               startActivity(new Intent(DetayActivity.this,MainActivity.class));
               finish();

               return true;
           default:
               return false;

       }
    }
}
