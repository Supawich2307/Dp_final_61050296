package view;

import java.util.Scanner;

import model.Transection;
import model.Transection_flow;

/*
    create by Supawich

    เมนูของการทำธุรกรรม
*/

public class Menu{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean loops = true;
        int num;
        functions func = new functions(); 
        while(loops){
            
            System.out.println("1 : โอนเงิน ");
            System.out.println("2 : ยืนยันการทำธุรกรรม ");
            System.out.println("3 : ค้นหาหมายเลขธุรกรรมที่ยืนยันแล้วต่อเนื่อหลังจากนี้");
            System.out.println("4 : ค้นหาหมายเลขธุรกรรมที่ไม่สมบูรณ์ที่ต่อเนื่องหลังจากนี้");
            System.out.println("5 : ค้นหาหมายเลขธุรกรรมที่ก่อนหน้า ");
            System.out.println("6 : ออก ");
            System.out.print("กรุณาเลือกหมายเลข => ");  num = sc.nextInt();
            System.out.println();
            if(1 <= num ||num <=6){
                switch(num){
                    case 1 : System.out.println(func.addTransection());
                             break;
                    case 2 : System.out.println(func.submitTransection());
                             break;
                    case 3 : System.out.println(func.searchTransection(Transection_flow.State.Complete));
                             break;
                    case 4 : System.out.println(func.searchTransection(Transection_flow.State.Incomplete));
                             break;
                    case 5 : System.out.println(func.searchForwardTransection());
                             break;
                    case 6 : loops = false;
                             break;   
                    default: System.out.println("ไม่มีหมายเลขที่ท่านเลือกกรุณาลองใหม่อีกครั้ง\n");  
                               
                }
                
            } 
        }
    }
}