package controller;

import model.ConnDatabase;

public abstract class controlException {
    ConnDatabase conn = new ConnDatabase();
    public void updateAllToIncom(){ 
        conn.updateAllAwaitToIn();
    }
}
