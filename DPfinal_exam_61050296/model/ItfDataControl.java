package model;

import java.util.ArrayList;

/*
    create by Supawich
*/

public interface ItfDataControl{
    public enum State{Awaiting,Incomplete,Complete};
    ArrayList<Transection> transectionArrayList = new ArrayList<>();
    ArrayList<Transection_flow<String,State>> Qflow = new ArrayList<>();
}

interface ObjectControl{
    String ToString();
}

