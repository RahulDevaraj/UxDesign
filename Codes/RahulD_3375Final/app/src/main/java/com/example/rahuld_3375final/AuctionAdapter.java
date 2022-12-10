package com.example.rahuld_3375final;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahuld_3375final.databinding.LayoutAuctionitemBinding;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class AuctionAdapter extends RecyclerView.Adapter<AuctionAdapter.AuctionViewHolder> {

    List<Auction> auctionList;

    public AuctionAdapter(List<Auction> auctionList) {
        this.auctionList = auctionList;
    }

    @NonNull
    @Override
    public AuctionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutAuctionitemBinding binding = LayoutAuctionitemBinding.inflate(
                inflater,parent,false);

        AuctionViewHolder auctionViewHolder = new AuctionViewHolder(binding);
        return auctionViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull AuctionViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.txtViewAuctionName.setText(
                auctionList.get(position).getAuctionName());
        holder.binding.txtViewAuctionNumTix.setText(
                auctionList.get(position).getCount()+"");

        holder.binding.imgViewAuctionPic.setImageResource(
                auctionList.get(position).getAuctionPic());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy");
        holder.binding.txtViewAuctionDateTixPrice.setText(
                formatter.format(auctionList.get(position).auctionDate)+"\n"+"$"+
                        auctionList.get(position).auctionPrice
        );
     //   holder.binding.imgViewRaffle.setImageResource(0);
        if(auctionList.get(position).isRaffleStatus())
        holder.binding.imgViewRaffle.setImageResource(R.drawable.raffle);
        else
            holder.binding.imgViewRaffle.setImageResource(0);

        holder.binding.imgViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auctionList.get(position).setCount(
                        auctionList.get(position).getCount()+1
                );
                notifyItemChanged(position);
            }
        });

        holder.binding.imgViewRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(auctionList.get(position).getCount() !=0)
                auctionList.get(position).setCount(
                        auctionList.get(position).getCount()-1
                );
               notifyItemChanged(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return auctionList.size();
    }

    public class AuctionViewHolder extends RecyclerView.ViewHolder {
        LayoutAuctionitemBinding binding;
        public AuctionViewHolder(@NonNull LayoutAuctionitemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
