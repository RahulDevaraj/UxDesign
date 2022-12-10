package com.example.rahuld_3375final;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class AuctionViewModel extends ViewModel {
    MutableLiveData<List<Auction>> AuctionList = new MutableLiveData<>();

    public LiveData<List<Auction>> getAuction(){
        if(AuctionList==null){
            AuctionList=new MutableLiveData<>();
        }
        return AuctionList;
    }

    public void loadAuction(List<Auction> auctionList){
        AuctionList.setValue(auctionList);
    }

}
