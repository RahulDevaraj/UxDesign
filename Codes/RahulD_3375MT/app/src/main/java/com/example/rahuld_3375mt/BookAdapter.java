package com.example.rahuld_3375mt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }


    public BookAdapter(List<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout,parent,false);

        BookViewHolder viewHolder = new BookViewHolder(itemView);

        viewHolder.imgViewFavStatus = itemView.findViewById(R.id.imgViewFavStatus);
        viewHolder.imgViewReadStatus = itemView.findViewById(R.id.imgViewReadStatus);
        viewHolder.txtViewBookName = itemView.findViewById(R.id.txtViewBookName);
        viewHolder.imgViewBookPic = itemView.findViewById(R.id.imgViewBookPic);
        viewHolder.bookItemView = itemView;

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

        holder.txtViewBookName.setText(books.get(position).getBookName());
        holder.imgViewBookPic.setImageResource(books.get(position).getBookPic());
        if(books.get(position).isReadStatus())
            holder.imgViewReadStatus.setImageResource(R.drawable.readbook);
        else
            holder.imgViewReadStatus.setImageResource(R.drawable.unreadbook);

        if(books.get(position).isFavoriteStatus())
            holder.imgViewFavStatus.setAlpha(1f);
        else
            holder.imgViewFavStatus.setAlpha(.2f);


        //
//        holder.imgViewReadStatus.setImageResource(R.drawable.unreadbook);
//        holder.imgViewFavStatus.setImageResource(R.drawable.favourite);

        holder.imgViewFavStatus.setOnClickListener((View view)-> {
           if(books.get(position).isFavoriteStatus() ==false)
           {
               holder.imgViewFavStatus.setAlpha(1f);
               books.get(position).setFavoriteStatus(true);
           }
           else
           {
               holder.imgViewFavStatus.setAlpha(0.2f);
               books.get(position).setFavoriteStatus(false);
           }

        });

        holder.imgViewReadStatus.setOnClickListener((View view)-> {
            if(books.get(position).isReadStatus()==false)
            {
                holder.imgViewReadStatus.setImageResource(R.drawable.readbook);
                books.get(position).setReadStatus(true);
            }
            else
            {
                holder.imgViewReadStatus.setImageResource(R.drawable.unreadbook);
                books.get(position).setReadStatus(false);
            }

        });

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{
        ImageView imgViewReadStatus;
        ImageView imgViewFavStatus;
        TextView txtViewBookName;
        ImageView imgViewBookPic;
        View bookItemView;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
