package model;

/*
    create by Supawich
*/

public class Transection_flow<String,State> implements ObjectControl,ItfDataControl{
    
    public String transection_id;
    public State state;
    public Transection_flow(Transection_flow transection_flow){
        this.transection_id = (String) transection_flow.transection_id;
        this.state = transection_flow.state;
    }

    public Transection_flow(String transection_id,State state){
        this.transection_id = transection_id;
        this.state = state;
    }

    @Override
    public java.lang.String ToString(){
        return  transection_id+" "+state;
    }

}
