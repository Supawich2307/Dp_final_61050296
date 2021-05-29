package view;

import java.util.ArrayList;
import model.*;

/*
    create by Supawich

    เป็น ฟังก์ชั่นทั้งหมดโดยมีหน้าที่รับค่าต่าง ๆเพื่อส่งต่อไปยัง Controller โดยมีการ Implement กับ interface functionOfApp
    เพื่อเป็นการกำหนดฟังก์ชั่นโดยใช้หลักการของ Abstract และเก็บ pool ของ Objที่ต้องการใช้  ใช้หลักการของ Flyweight
*/

public class functions implements functionOfApp,controller.ObjController{
    @Override
    public String addTransection() {
        
        System.out.print("กรอกหมายเลขบัญชีต้นทาง => "); String Swallet_ID = sc.next();
        System.out.print("กรอกหมายเลขบัญชีปลายทาง => "); String Dwallet_ID = sc.next();
        System.out.print("กรอกจำนวนเหรีญ(CS-KMITL COINS) => "); double Coin = sc.nextDouble();
        String ans = addC.add_transection_controller(Swallet_ID,Dwallet_ID,Coin);
        return ans;
        
    }
    
    @Override
    public String searchTransection() {
        
        String Transection_ID;
        System.out.print("กรุณากรอกหมายเลขธุรกรรม => "); Transection_ID = sc.next();
        ArrayList<Transection_flow<String,model.ItfDataControl.State>> ans = searchC.search(Transection_ID);
        
        if(ans.isEmpty()){//ถ้าหาไม่เจอ
            return "ไม่พบหมายเลขธุรกรรมนี้";
        }else {//ถ้าเจอทำ Iterator และ builderคำ
            StringBuilder sb = new StringBuilder();
            for (Transection_flow<String,model.ItfDataControl.State> Transection : ans) {
                System.out.println(Transection.ToString());
                sb.append(Transection.ToString());
                sb.append("\n\tV\t\n");
            }
            return sb.toString();
        }
    }

    @Override
    public String submitTransection() {
        String wallet_id;
        String Transection_id ;
        System.out.print("กรอกหมายเลขบัญชี => "); wallet_id = sc.next();
        System.out.print("กรุณากรอกหมายเลขธุรกรรม => "); Transection_id = sc.next();
        String ans = submitC.submit(wallet_id,Transection_id);
        if(ans.equals("fail")){
            return "ไม่สามารถทำการยืนยันได้";
        }else return ans;
    }
    
}
