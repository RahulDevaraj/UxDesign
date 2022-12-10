package com.example.rahuld_3375final;

import java.time.LocalDate;

public class Auction {
    String auctionName;
    int auctionPic;
    LocalDate auctionDate;
    double auctionPrice;
    int count;
    boolean raffleStatus;

    public Auction(String auctionName, int auctionPic, LocalDate auctionDate, double auctionPrice, boolean raffleStatus) {
        this.auctionName = auctionName;
        this.auctionPic = auctionPic;
        this.auctionDate = auctionDate;
        this.auctionPrice = auctionPrice;
        this.raffleStatus = raffleStatus;
        this.count = 0;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    public int getAuctionPic() {
        return auctionPic;
    }

    public void setAuctionPic(int auctionPic) {
        this.auctionPic = auctionPic;
    }

    public LocalDate getAuctionDate() {
        return auctionDate;
    }

    public void setAuctionDate(LocalDate auctionDate) {
        this.auctionDate = auctionDate;
    }

    public double getAuctionPrice() {
        return auctionPrice;
    }

    public void setAuctionPrice(double auctionPrice) {
        this.auctionPrice = auctionPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isRaffleStatus() {
        return raffleStatus;
    }

    public void setRaffleStatus(boolean raffleStatus) {
        this.raffleStatus = raffleStatus;
    }
}
