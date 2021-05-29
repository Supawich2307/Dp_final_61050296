package controller;

import model.*;

/*
    create by Supawich
*/

public class submit_controller extends controlException{
    ConnDatabase connDB = new ConnDatabase();
    public submit_controller(){}

    public String submit(String wallet_ID, String Transection_ID){

        System.out.println("Controller");
        String find_tran = connDB.findTosubmit(wallet_ID, Transection_ID);//ค้านหาว่ามีหมายเลขบัญชีและธุรกรรมนี้หรือไม่
        System.out.println(find_tran);
        if(find_tran.equals("Awaiting")){ //ถ้าสถานะ เป็นรอ
            System.out.println("if-Controller");
            int updateR = connDB.updateAwait(Transection_ID);//ทำการอัพเดทสถานะตามหมายเลขธุรกรรม
            if(updateR == 0){ //ถ้าเพิ่มไม่สำเร็จ
                return "fail";
            } //ถ้าเพิ่มสำเร็จ
            int updateAllR = connDB.updateAllAwaitToIn();//อัพเดททุกสถานะที่เป็น รอ เป็น ไม่สำเร็จ
            if(updateAllR == 0){
                connDB.updateComToInCom(Transection_ID);//อัพเดทสถานะ ที่เป็น เสร็จ เป็น Incomplete
                return "fail";
            } //ถ้าอัพเดทสถานะทั้งหมดสำเร็จ
            return "ทำการยืนยันธุรกรรมหมายเลข "+Transection_ID+" เรียบร้อยแล้ว";
        }else if(find_tran.equals("Complete")||find_tran.equals("Incomplete")){ //ถ้าเป็นอื่นๆ
            return "หมายเลขธุรกรรมนี้ไม่สามารถทำการ ยืนยัน การทำธุรกรรมได้";
        }else{
            return "หมายเลขบัญชี หรือ หมายเลขธุรกรรมไม่ถูกต้อง กรุณาทำรายการใหม่อีกครั้ง"; //ถ้าไม่มีสถานะ หรือก็คือไม่มีหมายเลขบัญชีหรือธุรกรรมในระบบ
        }
    }
    
}
