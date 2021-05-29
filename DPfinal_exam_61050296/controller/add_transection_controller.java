package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.*;
import service.GenTransectionID;
import service.rateCoinGoble;



public class add_transection_controller{
    private String Transection_id;
    private Double rateCoin; 
    ConnDatabase connDB = new ConnDatabase();
    
    public void add_transection_controller(){}

    public String add_transection_controller(String Source_wallet_id,String Destination_wallet_id,double coin){
        
        GenTransectionID gTransectionID = new GenTransectionID();
        rateCoinGoble rateCoinGoble = new rateCoinGoble();
        
        this.Transection_id = gTransectionID.GenTransection_ID(); //สร้างหมายเลขTransection
        logicRate(rateCoinGoble.getrateCS_kmitl_Coin());//ดึงข้อมูลจากเรทราคาเหรียญโลก

        //System.out.println(Transection_id+" "+Source_wallet_id+" "+Destination_wallet_id+" "+coin+" "+rateCoin);
        int Rs_Source = connDB.findwalletID(Source_wallet_id);//ค้นหาเลขบีญชีต้นทาง
        int Rs_Destination = connDB.findwalletID(Destination_wallet_id);//ค้นหาเลขบัญชีปลายทาง

       // System.out.println(Rs_Source+" "+Rs_Destination);
            if(Rs_Source == 1&&Rs_Destination==1){ //ถ้าเจอ
               //System.out.println("if 1 - add Con");

                //บันทึกข้อมูล
                int addTransection_Result = connDB.insertTransection(new Transection(Transection_id,Source_wallet_id,Destination_wallet_id,coin,rateCoin));
                if(addTransection_Result == 1){
                    int addTranF_RS = connDB.insertTransection_flow(Transection_id);
                    if(addTranF_RS == 1){ //เพิ่มสำเร็จ
                        return "ทำการเพิ่มธุรกรรมเสร็จสิ้น หมายเลขธุรกรรมของคุณคือ => "+Transection_id;
                    }else{
                        //เพิ่มไม่สำเร็จ
                        connDB.delTransection(Transection_id);//ลบหมายเลขธุรกรรมที่ทำการ
                        return "ทำการเพิ่มธุรกรรมไม่สำเร็จ\nกรุณาทำรายการใหม่อีกครั้ง";
                    }
                }else{
                    //เพิ่มไม่สำเร็จ
                    return "ทำการเพิ่มธุรกรรมไม่สำเร็จ\nกรุณาทำรายการใหม่อีกครั้ง";
                }
            }else if(Rs_Source == -1){
                                                                    //System.out.println("elseif  S -1 - add Con");
                return "ไม่พบหมายเลขบัญชีต้นทาง\nกรุณาตรวจสอบหมายเลขบัญชีใหม่อีกครั้ง";

            }else if(Rs_Destination == -1){
                                                                    //System.out.println("elseif D -1 - add Con");
                return "ไม่พบหมายเลขบัญชีต้นปลายทาง\nกรุณาตรวจสอบหมายเลขบัญชีใหม่อีกครั้ง";

            }else if (Rs_Destination == 0||Rs_Source ==0){
                                                                    //System.out.println("elseif 00 - add Con");
                return "ทำการเพิ่มธุรกรรมไม่สำเร็จ\nกรุณาทำรายการใหม่อีกครั้ง";

            }else{
                                                                    //System.out.println("else - add Con");
                return "ไม่สามารถทำธุกรรมได้ในขณะนี้\nกรุณาลองใหม่อีกครั้งภายหลัง";
            }
        
    }
    public void logicRate(double rate){
        //System.out.println("---"+rate);
        if(rate < 100){
            this.rateCoin = 100.0; 
        }else if(rate > 150 ){
            this.rateCoin = 150.0;
        }else{
            this.rateCoin = rate;
        }
    }
    
    

}

