package com.omerfpekgoz.notlaruygulamasisqllite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    private ArrayList<Notlar> notlarArrayList;
    private NotlarAdapter notlarAdapter;

    private DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolbar);
        recyclerView=findViewById(R.id.recyclerView);
        fab=findViewById(R.id.fab);

        dbHelper=new DbHelper(this);
        setSupportActionBar(toolbar);



        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        notlarArrayList=new NotlarDao().tumNotlar(dbHelper);

        float toplamNot=0.0f;
        for (Notlar not:notlarArrayList) {
            toplamNot=toplamNot+(not.getNotVize()+not.getNotFinal())/2;
        }

        toolbar.setSubtitle("Genel Ders Ortalaması: "+toplamNot/notlarArrayList.size());



        notlarAdapter=new NotlarAdapter(this,notlarArrayList);
        recyclerView.setAdapter(notlarAdapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,NotKayitActivity.class));
            }
        });


    }

    @Override
    public void onBackPressed() {   //BU metodu Main Activity deyken geri tuşuna basılınca uygulamayı kapatması için yazdık
       Intent intent=new Intent(Intent.ACTION_MAIN);
       intent.addCategory(Intent.CATEGORY_HOME);
       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       startActivity(intent);
    }
}
