package view;

import java.util.Scanner;

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
            System.out.println("2 : ค้นหาหมายเลขธุรกรรมที่สำเร็จแล้วก่อนหน้า ");
            System.out.println("3 : ยืนยันการทำธุรกรรม ");
            System.out.println("4 : ออก ");
            System.out.print("กรุณาเลือกหมายเลข => ");  num = sc.nextInt();
            System.out.println();
            if(1 <= num ||num <=4){
                switch(num){
                    case 1 : System.out.println(func.addTransection());
                             break;
                    case 2 : System.out.println(func.searchTransection());
                             break;
                    case 3 : System.out.println(func.submitTransection());
                             break;
                    case 4 : loops = false;
                             break;   
                    default: System.out.println("ไม่มีหมายเลขที่ท่านเลือกกรุณาลองใหม่อีกครั้ง\n");  
                               
                }
                
            } 
        }
    }
}