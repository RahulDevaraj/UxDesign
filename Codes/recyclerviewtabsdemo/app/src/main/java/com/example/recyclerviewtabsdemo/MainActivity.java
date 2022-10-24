package com.example.recyclerviewtabsdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.recyclerviewtabsdemo.adapters.TuneAdapter;
import com.example.recyclerviewtabsdemo.adapters.TuneAdapter2;
import com.example.recyclerviewtabsdemo.model.Tune;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> tuneNames =
            new ArrayList<>(Arrays.asList("Beauty And The Beast",
                    "Lion King", "Mary Poppins",
                    "Game of Thrones","Ozark"));
    List<Integer> tunePics = new ArrayList<>(Arrays.asList(R.drawable.beauty,
            R.drawable.lionking,R.drawable.marypoppins,
            R.drawable.gameofthrones,R.drawable.ozark));

    List<Tune> allTunes = new ArrayList<>();
    List<Tune> movieTunes = new ArrayList<>();
    List<Tune> tVTunes = new ArrayList<>();
    final String TAG = "RECYCLERVIEWDEMO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            loadData();
        RecyclerView recyclerViewTunes = findViewById(R.id.recyclerView);
        TabLayout tuneTabs = findViewById(R.id.tabLayoutTunes);

//        TuneAdapter tuneAdapter = new TuneAdapter(allTunes);//deflt alltunes
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
//        recyclerViewTunes.setLayoutManager(gridLayoutManager);
//        recyclerViewTunes.setAdapter(tuneAdapter);

        TuneAdapter2 tuneAdapter=new TuneAdapter2(allTunes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewTunes.setLayoutManager(layoutManager);
        recyclerViewTunes.setAdapter(tuneAdapter);

        tuneTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tuneTabs.getSelectedTabPosition()){
                    case 0:{
                        tuneAdapter.setTuneList(allTunes);
                    }
                    break;
                    case 1:{
                        tuneAdapter.setTuneList(movieTunes);
                    }
                    break;
                    case 2:{
                        tuneAdapter.setTuneList(tVTunes);
                    }
                    break;
                }
                  }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Toast.makeText(MainActivity.this, "ReSel "+tuneTabs.getSelectedTabPosition(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void loadData(){
        for(int i=0;i<tuneNames.size();i++)
        {
            Tune tune = new Tune(tuneNames.get(i), tunePics.get(i));
            allTunes.add(tune);
        }
        movieTunes = allTunes.subList(0,3);//3 is excluded
        tVTunes = allTunes.subList(3,5);
    }

}