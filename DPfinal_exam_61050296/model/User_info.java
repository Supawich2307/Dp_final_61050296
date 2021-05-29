package model;

/*
    create by Supawich
*/

public class User_info {
    private String wallet_id;
    private String Name;
    private double sumCoin;

    public User_info(){}
    public User_info(String wallet_id,String Name,double sumCoin){
        this.wallet_id = wallet_id;
        this.Name = wallet_id;
        this.sumCoin = sumCoin;
    }
    public String getWallet_id(){
        return wallet_id;
    }
    public String getName(){
        return Name;
    }
    public double getSumCoin(){
        return sumCoin;
    }
    public void setwallet_id(String wallet_id){
        this.wallet_id = wallet_id;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    public void setsemCoin(double sumCoin){
        this.sumCoin = sumCoin;
    }
    @Override
    public String toString(){
        return wallet_id+""+Name+""+sumCoin;
    }
}
