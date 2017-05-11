package com.leaning.interviews;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Avinash V on 10-05-2017.
 */
public class ScrapBook {
    public static void main(String[] args) {
        ArrayList<GoldPrice> priceList = new ArrayList<GoldPrice>();
        priceList.add(new GoldPrice(10, new Timestamp(System.currentTimeMillis())));
        priceList.add(new GoldPrice(5, new Timestamp(System.currentTimeMillis()+10000)));
        priceList.add(new GoldPrice(90, new Timestamp(System.currentTimeMillis()+20000)));
        priceList.add(new GoldPrice(1, new Timestamp(System.currentTimeMillis()+30000)));
        priceList.add(new GoldPrice(86, new Timestamp(System.currentTimeMillis()+40000)));
        priceList.add(new GoldPrice(65, new Timestamp(System.currentTimeMillis()+50000)));
        priceList.add(new GoldPrice(80, new Timestamp(System.currentTimeMillis()+60000)));

        ScrapBook.getMinMaxPriceForMaxMargin(priceList);
    }

    public static void getMinMaxPriceForMaxMargin(ArrayList<GoldPrice> priceList) {
        int margin = 0, minPrice = priceList.get(0).getPrice(),
                minPriceToPur = 0, maxPrice = 0;
        Timestamp timeToPurchase = new Timestamp(System.currentTimeMillis()),
                timeToSell = new Timestamp(System.currentTimeMillis()), minPurchaseTime = new Timestamp(System.currentTimeMillis());
        for (GoldPrice goldPrice : priceList) {
            if (goldPrice.getPrice() - minPrice > 0 && goldPrice.getPrice()
                    - minPrice > margin) {
                margin = goldPrice.getPrice() - minPrice;
                maxPrice = goldPrice.getPrice();
                minPriceToPur = minPrice;
                timeToSell = goldPrice.getUpdatedTime();
                timeToPurchase = minPurchaseTime;
            }
            if (minPrice > goldPrice.getPrice()) {
                minPrice = goldPrice.getPrice();
                minPurchaseTime = goldPrice.getUpdatedTime();
            }
        }
        if (margin > 0) {
            System.out.println("Min Price For Max margin : " + minPriceToPur
                    + " , Max Prixe for Max Margin: "
                    + maxPrice + " , Margin : " + margin);
            System.out.println("Gold should be purchased at "+
                    timeToPurchase+" and should be sold at: "+timeToSell);
        } else {
            System.out.println("Item not purchased");
        }

    }
}


class GoldPrice {
    private int price;
    private Timestamp updatedTime;
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public Timestamp getUpdatedTime() {
        return updatedTime;
    }
    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }
    public GoldPrice(int price, Timestamp updatedTime) {
        super();
        this.price = price;
        this.updatedTime = updatedTime;
    }
}
