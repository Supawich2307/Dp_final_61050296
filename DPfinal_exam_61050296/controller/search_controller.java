package controller;

import java.util.ArrayList;
import model.Transection_flow;

/*
    create by Supawich
*/

public class search_controller  implements model.ItfDataControl,model.ItfConnDB{
    ArrayList<Transection_flow<String,State>> ans = new  ArrayList<>();
    
    public  ArrayList<Transection_flow<String,State>> search(String transection_ID,model.ItfDataControl.State state) {
        
        boolean Result_Check_Transection = connDB.findTransection(transection_ID);
        if(Result_Check_Transection){
            ans = connDB.searchTransection(transection_ID,state);
        }else{
            return ans;
        }
        return ans;
    }

    public ArrayList<Transection_flow<String,State>> searchForward(String transection_ID) {
        
        boolean findTransectionRs = connDB.findTransection(transection_ID);
        if(findTransectionRs){
            ans = connDB.searchForward(transection_ID);
            return ans;
        }

        return ans;
    }

}
