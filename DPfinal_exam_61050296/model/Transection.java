package model;

/*
    create by Supawich
*/

public class Transection{

    public String Transection_id;
    public String Source_wallet_id;
    public String Destination_wallet_id;
    public Double coin;
    public Double rateCoin; 
    public Transection(){}
    public Transection(Transection transection){
        this.Transection_id = transection.Transection_id;
        this.Source_wallet_id = transection.Source_wallet_id;
        this.Destination_wallet_id = transection.Destination_wallet_id;
        this.coin = transection.coin;
        this.rateCoin = transection.rateCoin;
    }
    public Transection(String Transection_id,String Source_wallet_id
    ,String Destination_wallet_id,double coin,double rateCoin){
        this.Transection_id = Transection_id;
        this.Source_wallet_id = Source_wallet_id;
        this.Destination_wallet_id = Destination_wallet_id;
        this.coin = coin;
        this.rateCoin = rateCoin;
    }
    public String ToString(){
        return this.Transection_id+" "+this.Source_wallet_id+" "+this.Destination_wallet_id
                +" "+this.coin+" "+this.rateCoin;
    }
}



