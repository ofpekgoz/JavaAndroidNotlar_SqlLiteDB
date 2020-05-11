package com.omerfpekgoz.notlaruygulamasisqllite;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotlarAdapter extends RecyclerView.Adapter<NotlarAdapter.cardTasarimTutucu> {

    private Context mContext;
    List<Notlar> notlarListesi;

    public NotlarAdapter() {
    }

    public NotlarAdapter(Context mContext, List<Notlar> notlarListesi) {
        this.mContext = mContext;
        this.notlarListesi = notlarListesi;
    }

    @NonNull
    @Override
    public cardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim,parent,false);


        return new cardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cardTasarimTutucu holder, int position) { //card tasarımı üzerine buarad verileri akttaracapğız

        final Notlar not=notlarListesi.get(position);
        holder.txtDersAdi.setText(not.getDersAdi());
        holder.txtNotVize.setText(String.valueOf(not.getNotVize()));
        holder.txtNotFinal.setText(String.valueOf(not.getNotFinal()));

        holder.cardNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext,DetayActivity.class);
                intent.putExtra("not",not);

                mContext.startActivity(intent);

            }
        });



    }


    @Override
    public int getItemCount() {
        return notlarListesi.size();
    }


    public class cardTasarimTutucu extends RecyclerView.ViewHolder{

        private CardView  cardNot;
        private TextView txtDersAdi;
        private TextView txtNotVize;
        private TextView txtNotFinal;



        public cardTasarimTutucu(@NonNull View itemView) {

            super(itemView);
            cardNot=itemView.findViewById(R.id.cardNot);
            txtDersAdi=itemView.findViewById(R.id.txtDersAdi);
            txtNotVize=itemView.findViewById(R.id.txtNotVize);
            txtNotFinal=itemView.findViewById(R.id.txtNotFinal);


        }
    }
}
