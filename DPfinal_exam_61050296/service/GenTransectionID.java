package service;

import java.util.Random;
import java.util.UUID;

/*
    create by Supawich
*/

public class GenTransectionID {
    public UUID GenTransection_UID(){
        UUID transection_id = UUID.randomUUID();
        return transection_id;
    }   
    public String GenTransection_ID(){
        String Transection_id = "";
        String [] arr = {"A", "B", "C", "D","E","F","G","H","I","J","K","L","M","N","O"
                        ,"P","Q","R","S","T","U","V","X","Y","Z"};
        Random random = new Random();
        String s = String.valueOf(System.currentTimeMillis());
        int select1 = random.nextInt(arr.length);
        int select2 = random.nextInt(arr.length);
        Transection_id = arr[select1]+arr[select2]+s; 
        return Transection_id;
    }
}
