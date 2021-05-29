package controller;

import java.util.ArrayList;
import model.Transection_flow;

/*
    create by Supawich
*/

public class search_controller  implements model.ItfDataControl,model.ItfConnDB{
    
    public search_controller(){}
    public  ArrayList<Transection_flow<String,State>> search(String transection_ID) {
        ArrayList<Transection_flow<String,State>> ans = new  ArrayList<>();
        
        boolean Result_Check_Transection = connDB.findTransection(transection_ID);
        if(Result_Check_Transection){
            ans = connDB.searchTransection(transection_ID);
        }else{
            return ans;
        }
        return ans;
    }

}
