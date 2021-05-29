package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.*;


public class add_transection_controller extends abstrct_add{
    private String Transection_id;
    private Double rateCoin; 
    ConnDatabase connDB = new ConnDatabase();
    
    public void add_transection_controller(){}

    public String add_transection_controller(String Source_wallet_id,String Destination_wallet_id,double coin){
        
        
        this.Transection_id = GenTransectionID(); //สร้างหมายเลขTransection
        this.rateCoin = logicRate();//ดึงข้อมูลจากเรทราคาเหรียญโลก

        
        int Rs_Source = connDB.findwalletID(Source_wallet_id);//ค้นหาเลขบีญชีต้นทาง
        int Rs_Destination = connDB.findwalletID(Destination_wallet_id);//ค้นหาเลขบัญชีปลายทาง

            if(Rs_Source == 1&&Rs_Destination==1){ //ถ้าเจอ
               

                //บันทึกข้อมูล
                int addTransection_Result = connDB.insertTransection(new Transection(Transection_id,Source_wallet_id,Destination_wallet_id,coin,rateCoin));
                if(addTransection_Result == 1){
                    int addTranF_RS = connDB.insertTransection_flow(Transection_id);
                    if(addTranF_RS == 1){ //เพิ่มสำเร็จ
                        return "ทำการเพิ่มธุรกรรมเสร็จสิ้น หมายเลขธุรกรรมของคุณคือ => "+Transection_id+"\n";
                    }else{
                        //เพิ่มไม่สำเร็จ
                        connDB.delTransection(Transection_id);//ลบหมายเลขธุรกรรมที่ทำการ
                        return "ทำการเพิ่มธุรกรรมไม่สำเร็จ\nกรุณาทำรายการใหม่อีกครั้ง\n";
                    }
                }else{
                    //เพิ่มไม่สำเร็จ
                    return "ทำการเพิ่มธุรกรรมไม่สำเร็จ\nกรุณาทำรายการใหม่อีกครั้ง\n";
                }
            }else if(Rs_Source == -1){
                                                                    
                return "ไม่พบหมายเลขบัญชีต้นทาง\nกรุณาตรวจสอบหมายเลขบัญชีใหม่อีกครั้ง\n";

            }else if(Rs_Destination == -1){
                                                                    
                return "ไม่พบหมายเลขบัญชีต้นปลายทาง\nกรุณาตรวจสอบหมายเลขบัญชีใหม่อีกครั้ง\n";

            }else if (Rs_Destination == 0||Rs_Source ==0){
                                                                   
                return "ทำการเพิ่มธุรกรรมไม่สำเร็จ\nกรุณาทำรายการใหม่อีกครั้ง\n";

            }else{
                                                                    
                return "ไม่สามารถทำธุกรรมได้ในขณะนี้\nกรุณาลองใหม่อีกครั้งภายหลัง\n";
            }
        
    }
    
    

}

