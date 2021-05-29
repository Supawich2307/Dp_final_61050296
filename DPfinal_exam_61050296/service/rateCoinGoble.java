package service;

/*
    create by Supawich
*/

public class rateCoinGoble {
    public double rateCS_kmitl_Coin(){
        return Math.random()*200;
    }
    public double getrateCS_kmitl_Coin(){
        double rateCScoin = rateCS_kmitl_Coin();
        return  Double.parseDouble(String.format("%.6f",rateCScoin));
    }
}
