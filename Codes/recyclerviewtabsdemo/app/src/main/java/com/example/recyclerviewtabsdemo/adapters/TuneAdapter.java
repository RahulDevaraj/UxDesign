package com.example.recyclerviewtabsdemo.adapters;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewtabsdemo.R;
import com.example.recyclerviewtabsdemo.model.Tune;

import java.util.List;

public class TuneAdapter extends RecyclerView.Adapter
        <TuneAdapter.TuneViewHolder> {
    List<Tune> tuneList;
    public TuneAdapter(List<Tune> tuneList) {
        this.tuneList = tuneList;
    }
    public List<Tune> getTuneList() {
        return tuneList;
    }
    public void setTuneList(List<Tune> tuneList) {
        this.tuneList = tuneList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TuneViewHolder onCreateViewHolder
            (@NonNull ViewGroup parent, int viewType) {//inflate
        View itemView= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.layout_tune,parent,false);
        //create viewHolder Obj
        TuneViewHolder viewHolder = new TuneViewHolder(itemView);

        viewHolder.imageViewTune = itemView.findViewById(R.id.imgViewTune);
        viewHolder.textViewTune = itemView.findViewById(R.id.txtViewTune);
        viewHolder.tuneItemView = itemView;

        viewHolder.tuneItemView.setOnClickListener((View view)-> {
              if(viewHolder.tuneItemView.getBackground() instanceof ColorDrawable
              && ((ColorDrawable) viewHolder.tuneItemView.getBackground()).getColor()
                      != Color.LTGRAY)
                  viewHolder.tuneItemView.setBackgroundColor(Color.LTGRAY);
              else
                  viewHolder.tuneItemView.setBackgroundColor(
                          Color.parseColor("#FAFAFA"));

        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TuneViewHolder holder, int position) {
        holder.tuneItemView.setBackgroundColor(Color.parseColor("#FAFAFA"));
        holder.imageViewTune.setImageResource(tuneList.get(position).getTunePic());
        holder.textViewTune.setText(tuneList.get(position).getTuneName());

    }

    @Override
    public int getItemCount() {
        return tuneList.size();
    }

    public class TuneViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewTune;
        TextView textViewTune;
        View tuneItemView;

        public TuneViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
