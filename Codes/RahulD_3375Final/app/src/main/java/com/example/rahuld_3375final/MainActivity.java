package com.example.rahuld_3375final;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.rahuld_3375final.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    AuctionViewModel auctionViewModel;
    List<Integer> tempAuction = new ArrayList<>();
    List<Auction> auctionList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        readData();
        auctionViewModel = new ViewModelProvider(this).get(AuctionViewModel.class);
        auctionViewModel.loadAuction(auctionList);
        //Toast.makeText(this, ""+auctionList.size(), Toast.LENGTH_SHORT).show();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tempAuction = new ArrayList<>();
                for(int i=0;i<auctionList.size();i++){
                   tempAuction.add(auctionList.get(i).getCount());

                }

                //
                for(int i=0;i<auctionList.size();i++){
                    auctionList.get(i).setCount(0);
                    auctionViewModel.loadAuction(auctionList);
                }

                Snackbar.make(view, "Empty Cart?", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", (View v)->{
                            for(int i=0;i<tempAuction.size();i++){
                                auctionList.get(i).setCount(tempAuction.get(i));
                            }
                            auctionViewModel.loadAuction(auctionList);
                           // Toast.makeText(MainActivity.this, ""+tempAuction.get(0).getCount(), Toast.LENGTH_SHORT).show();
                        }).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void readData() {
        InputStream inputStream = getResources().openRawResource(R.raw.auctionfundraiser);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream));
        try{
            reader.readLine(); //first line dump

            String line;
            while((line = reader.readLine())!=null){
                String each[] = line.split(",");
                String name = each[0];
                String picString = each[1];
                String dateStr = each[2];
                double price = Double.parseDouble(each[3]);
                String raffleString = each[4];

                int auctionDrawable = getResources().getIdentifier(
                        picString,"drawable",getPackageName());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                LocalDate date = LocalDate.parse(dateStr,formatter);
                boolean raffleStatus;
                if(raffleString.equals("yes"))
                    raffleStatus = true;
                else
                    raffleStatus = false;

                Auction eachAuction = new Auction(name,auctionDrawable,date,price,raffleStatus);
                auctionList.add(eachAuction);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}