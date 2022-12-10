package com.example.datedemo;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datedemo.databinding.LayoutDogitemBinding;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogViewHolder> {

    List<Dog> AdapterDogData;
    Context context;

    public DogAdapter(List<Dog> adapterDogData, Context context) {
        AdapterDogData = adapterDogData;
        this.context = context;
    }

    public DogAdapter(List<Dog> adapterDogData) {
        AdapterDogData = adapterDogData;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutDogitemBinding binding = LayoutDogitemBinding.inflate
                (inflater,parent,false);

        DogViewHolder dogViewHolder = new DogViewHolder(binding);
//        dogViewHolder.binding.txtViewDOB.setOnClickListener((View view)-> {
//
//        });
        dogViewHolder.binding.txtViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getApplicationContext(), 
                ""+AdapterDogData.get(dogViewHolder.getAdapterPosition()).getDogName(), 
                Toast.LENGTH_SHORT).show();
            }
        });
        return dogViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
    holder.binding.txtViewId.setText(String.valueOf(
            AdapterDogData.get(position).getId()    ));
    holder.binding.txtViewBreed.setText(AdapterDogData.get(position).getDogBreed());
    holder.binding.txtViewName.setText(AdapterDogData.get(position).getDogName());
    holder.binding.imgViewDogPic.setImageResource(
            AdapterDogData.get(position).getDogPicDrawable()    );

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    holder.binding.txtViewDOB.setText(
            formatter.format(AdapterDogData.get(position).getDob())    );

    }

    @Override
    public int getItemCount() {
        return AdapterDogData.size();
    }

    public class DogViewHolder extends RecyclerView.ViewHolder {

        LayoutDogitemBinding binding;

        public DogViewHolder(LayoutDogitemBinding bindingP) {
            super(bindingP.getRoot());
            binding = bindingP;
        }
    }
    


}
