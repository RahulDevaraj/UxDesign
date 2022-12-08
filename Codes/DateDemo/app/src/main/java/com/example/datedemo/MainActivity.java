package com.example.datedemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.datedemo.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Dog> DogList = new ArrayList<>();
    //Note that buildFeatures viewBinding must be set to true for binding to work
    ActivityMainBinding binding;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ReadDogData();

        binding.recyclerViewDogItems
                .setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewDogItems
                .setAdapter(new DogAdapter(DogList,this));



    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void ReadDogData() {
        DogList = new ArrayList<>();

        //FileRead
        InputStream inputStream = getResources().openRawResource(R.raw.doginfo);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream));
        try {

        String line;
        while((line = reader.readLine())!=null){
        String[] eachWord = line.split(",");
        int id = Integer.parseInt(eachWord[0]);
        String picName = eachWord[1];
        String dogBreed = eachWord[2];
        String dogName = eachWord[3];
        String dogDOBStr = eachWord[4];

        int dogDrawable = getResources().getIdentifier(picName,
                "drawable",getPackageName());

            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("d-MMM-yyyy");
            LocalDate dob = LocalDate.parse(dogDOBStr,formatter);

            Dog eachDog = new Dog(id,dogBreed,dogName,dogDrawable,dob);
            DogList.add(eachDog);
        }
        }
        catch (Exception e)
        {
            Log.d("DATEDEMO",e.getMessage());
        }
    }
}