package com.example.philippe.molebuster;

/**
 * Created by Philippe on 2017-12-06.
 */


import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.Collections;
import java.util.List;


/**
 * Created by Philippe on 2017-12-02.
 */

/**
 * Classe trouvée sur internet qui gère le RecycleurView
 * https://stackoverflow.com/questions/39238674/recyclerview-viewholder-oncreateviewholder-view-binding-and-onbindviewholder
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Joueur> mData = Collections.emptyList();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    //CLICKING
    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    //VIEW
    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, List<Joueur> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rowhighscore, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // binds the data to the textview in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Si le joueur est actif, le mettre en gras
        if (mData.get(position).estActif()){
            holder.myTextView1.setTypeface(Typeface.DEFAULT_BOLD);
            holder.myTextView2.setTypeface(Typeface.DEFAULT_BOLD);
        }
        holder.myTextView1.setText(mData.get(position).getNom());
        double temp = mData.get(position).getScore();
        DecimalFormat df = new DecimalFormat("#.##");
        holder.myTextView2.setText(df.format(temp));

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView myTextView1;
        public TextView myTextView2;

        public ViewHolder(View itemView) {
            super(itemView);
            myTextView1 = (TextView) itemView.findViewById(R.id.textViewName);
            myTextView2 = (TextView) itemView.findViewById(R.id.textViewScore);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            System.out.println("1");
            if (mClickListener != null) {
                System.out.println("2");
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    // convenience method for getting data at click position

    //Donne le score du joueur actif
    public double getScore(int id) {
        //return mData.get(id).getNbBananas();
        return mData.get(id).getScore();
    }

}