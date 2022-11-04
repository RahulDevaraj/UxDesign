package com.example.rahuld_3375mt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> bookNames = new ArrayList<>(
            Arrays.asList("The Alchemist",
                    "Bewilderment",
                    "Speak",
                    "Night",
                    "Iron Widow",
                    "The Wish")
    );
    List <Integer> bookPics = new ArrayList<>(Arrays.asList(
            R.drawable.thealchemist,
            R.drawable.bewilderment,
            R.drawable.speak,
            R.drawable.night,
            R.drawable.ironwidow,
            R.drawable.thewish
    ));
    List<Book> allBooks = new ArrayList<>();
    RecyclerView recyclerView;
    TabLayout tabLayout;
    Button button;

    public void loadData(){
        for(int i=0;i<bookNames.size();i++){
            Book book = new Book(bookNames.get(i),bookPics.get(i));
            allBooks.add(book);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        recyclerView = findViewById(R.id.recyclerVIew);
        tabLayout = findViewById(R.id.tabLayout);
        button = findViewById(R.id.buttonRead);

        BookAdapter adapter = new BookAdapter(allBooks);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tabLayout.getSelectedTabPosition()){
                    case 0:
                        adapter.setBooks(allBooks);
                        break;
                    case 1:
                    {
                        List<Book> favBooks = new ArrayList<>();
                        for(int i=0;i<allBooks.size();i++){
                            if(allBooks.get(i).isFavoriteStatus())
                                favBooks.add(allBooks.get(i));
                        }
                        adapter.setBooks(favBooks);
                        break;
                    }


                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tabLayout.getSelectedTabPosition()){
                    case 0:
                        adapter.setBooks(allBooks);
                        break;
                    case 1:
                    {
                        List<Book> favBooks = new ArrayList<>();
                        for(int i=0;i<allBooks.size();i++){
                            if(allBooks.get(i).isFavoriteStatus())
                                favBooks.add(allBooks.get(i));
                        }
                        adapter.setBooks(favBooks);
                        break;
                    }


                }
            }
        });

        button.setOnTouchListener(new CustomTouchListener(MainActivity.this){
            @Override
            public void onSingleClick() {
                super.onSingleClick();
                List<Book> unReadBooksAll = new ArrayList<>();
                if(tabLayout.getSelectedTabPosition()==0){
                    for(int i=0;i<allBooks.size();i++){
                        if(!allBooks.get(i).isReadStatus()){
                            unReadBooksAll.add(allBooks.get(i));
                        }
                    }
                    adapter.setBooks(unReadBooksAll);
                }else
                {
                    List<Book> favBooks = new ArrayList<>();
                    for(int i=0;i<allBooks.size();i++){
                        if(allBooks.get(i).isFavoriteStatus()==true
                        && allBooks.get(i).isReadStatus()==false)

                            favBooks.add(allBooks.get(i));
                    }

                    adapter.setBooks(favBooks);
                }

            }

            @Override
            public void onDoubleClick() {
                super.onDoubleClick();

                List<Book> unReadBooksAll = new ArrayList<>();
                if(tabLayout.getSelectedTabPosition()==0){
                    adapter.setBooks(allBooks);
                }else
                {
                    List<Book> favBooks = new ArrayList<>();
                    for(int i=0;i<allBooks.size();i++){
                        if(allBooks.get(i).isFavoriteStatus()==true)
                            favBooks.add(allBooks.get(i));
                    }

                    adapter.setBooks(favBooks);
                }
            }
        });





    }
}