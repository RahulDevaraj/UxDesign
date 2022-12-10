package com.example.rahuld_3375final;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.rahuld_3375final.databinding.FragmentFirstBinding;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    List<Auction> fragAuction = new ArrayList<>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AuctionViewModel auctionViewModel = new ViewModelProvider(
                requireActivity()).get(AuctionViewModel.class);

        auctionViewModel.getAuction().observe(requireActivity(), new Observer<List<Auction>>() {
            @Override
            public void onChanged(List<Auction> auctionList) {
                fragAuction = auctionList;
                AuctionAdapter auctionAdapter = new AuctionAdapter(fragAuction);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                binding.recyclerViewAuctionCatalogue.setLayoutManager(gridLayoutManager);
                binding.recyclerViewAuctionCatalogue.setAdapter(auctionAdapter);

            }
        });

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("title", (String) binding.txtViewTitle.getText());
                String str = "ORDER SUMMARY\n";
                Double total = 0.0;
                for(int i=0;i<fragAuction.size();i++){
                    if(fragAuction.get(i).getCount()>0){
                        str = str+ fragAuction.get(i).getAuctionName()+"(";
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                        str = str + formatter.format(fragAuction.get(i).getAuctionDate());
                        if(fragAuction.get(i).isRaffleStatus())
                            str = str + ",raffle): ";
                        else
                            str = str + "): ";
                        str = str + fragAuction.get(i).getCount()+"\n";
                        if(!fragAuction.get(i).isRaffleStatus())
                        total += fragAuction.get(i).getAuctionPrice()*
                                fragAuction.get(i).getCount();
                        else
                            total += (fragAuction.get(i).getAuctionPrice()+9.99)*
                                    fragAuction.get(i).getCount();
                    }
                }
               // if(total)
                //String totalStr = total.toString().substring(0,6);
                str = str + "Total Cost: $"+total;

                bundle.putString("total",str);
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment,bundle);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}