package controller;

import service.*;
abstract class abstrct_add implements ItfRateGoble,ItfGenTran_ID{
    @Override
    public String GenTransectionID(){
        return genID.GenTransection_ID();
    }
    @Override
    public double logicRate() {
        double rate = rateCoin_Goble.rateCS_kmitl_Coin();
        if(rate < 100){
            return 100.0; 
        }else if(rate > 150 ){
            return 150.0;
        }else{
            return rate;
        }
    }
    
}
