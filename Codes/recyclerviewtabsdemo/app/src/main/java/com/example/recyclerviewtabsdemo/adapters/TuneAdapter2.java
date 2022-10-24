package com.example.recyclerviewtabsdemo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewtabsdemo.R;
import com.example.recyclerviewtabsdemo.model.Tune;

import java.util.List;

public class TuneAdapter2 extends
        RecyclerView.Adapter<TuneAdapter2.TuneViewHolder2> {
    List<Tune> tuneList;
    int currentPlayIndex = -1;

    public TuneAdapter2(List<Tune> tuneList) {
        this.tuneList = tuneList;
    }

    public List<Tune> getTuneList() {
        return tuneList;
    }

    public void setTuneList(List<Tune> tuneList) {
        this.tuneList = tuneList;
        currentPlayIndex = -1;
        notifyDataSetChanged();
    }

    public int getCurrentPlayIndex() {
        return currentPlayIndex;
    }

    public void setCurrentPlayIndex(int currentPlayIndex) {
        this.currentPlayIndex = currentPlayIndex;
    }

    @NonNull
    @Override
    public TuneViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.layout_tune2,parent,false);

        TuneViewHolder2 holder2 = new TuneViewHolder2(itemView);
        holder2.imgViewTune2 = itemView.findViewById(R.id.imgViewTune2);
        holder2.txtViewTune2 = itemView.findViewById(R.id.txtViewTune2);
        holder2.imgViewPlayPause = itemView.findViewById(R.id.imgViewPlayPause);

        return holder2;

    }

    @Override
    public void onBindViewHolder(@NonNull TuneViewHolder2 holder, int position) {
        holder.imgViewTune2.setImageResource(tuneList.get(position).getTunePic());
        holder.txtViewTune2.setText(tuneList.get(position).getTuneName());
        if(position==currentPlayIndex)
            holder.imgViewPlayPause.setImageResource(R.drawable.pause);
        else
            holder.imgViewPlayPause.setImageResource(R.drawable.play);

        holder.imgViewPlayPause.setOnClickListener((View view)-> {
            if(position==currentPlayIndex)
                currentPlayIndex=-1;
            else
                currentPlayIndex=holder.getAdapterPosition();
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return tuneList.size();
    }

    public class TuneViewHolder2 extends RecyclerView.ViewHolder{
        ImageView imgViewTune2;
        TextView txtViewTune2;
        ImageView imgViewPlayPause;

        public TuneViewHolder2(@NonNull View itemView) {
            super(itemView);
        }
    }

}
