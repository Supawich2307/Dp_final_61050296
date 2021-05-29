package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/*
    create by Supawich
    เชื่อมต่อกับ Database และการquery
*/


public class ConnDatabase implements ItfDataControl{
    private String db_name = "dp_transection";
    private String name = "root";
    private String pass = "";
    private String hostname = "127.0.0.1";
    private String db_driver = "com.mysql.cj.jdbc.Driver";
    private Connection conn ;
    private String url = "jdbc:mysql://"+hostname+"/"+db_name;
    public ConnDatabase(){
        try {
            Class.forName(db_driver);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public int insertTransection(Transection transection) {
        String sql = "INSERT INTO `dp_transection`.`transection`(`Transection_ID`, `Source_wallet_ID`, `Destination_wallet_ID`, `Coin`, `RateCoin`) \n"+ 
                     "VALUES ('"+transection.Transection_id+"', '"+transection.Source_wallet_id+"', '"
                     +transection.Destination_wallet_id+"', '"+transection.coin+"', '"+transection.rateCoin+"');";
        
        try{
            
            System.out.println("insertTrans");
            conn = DriverManager.getConnection(url,name,pass);
            Statement myStmt = conn.createStatement();
            myStmt.executeUpdate(sql);
            myStmt.close();
            return 1;
        }catch(Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
            return 0;
        }
        
    }
    public int insertTransection_flow(String Transection_id){
        String sql = "INSERT INTO `dp_transection`.`transection_flow`(`FTransection_ID`,`State`) \n"+ 
                     "VALUES ('"+Transection_id+"', '"+State.Awaiting+"');";
       
        try{
            System.out.println("insertTrans_flow");
            conn = DriverManager.getConnection(url,name,pass);
            Statement myStmt = conn.createStatement();
            myStmt.executeUpdate(sql);
            myStmt.close();
            return 1;
        }catch(Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
            return 0;
        }
    }
    public int findwalletID(String wallet_id) {
        String sql = "SELECT User_ID FROM user_info WHERE User_ID = '"+wallet_id+"';";
        try{
            conn = DriverManager.getConnection(url,name,pass);
            Statement myStmt = conn.createStatement();
            ResultSet myRs = myStmt.executeQuery(sql);
            String Wallet_ID_Rs = "";
            while(myRs.next()){
                Wallet_ID_Rs = myRs.getString(1);
                System.out.println(Wallet_ID_Rs);
            }
            if(Wallet_ID_Rs.equals(wallet_id)) {
                System.out.println("True");
                myStmt.close();
                myRs.close();
                return 1; //ค้านหาเจอ
            }else return -1; //ไม่เจอ
   
        }catch(Exception e){
            System.out.println("error : "+e);
            return 0; //พัง
        }
    }
    public String findTosubmit(String wallet_ID, String Transection_ID){
        String sql = "SELECT tran.State FROM transection_flow as tran ,user_info as user\n"+
                     "WHERE user.User_ID = '"+wallet_ID+"' AND (tran.FTransection_ID = '"+Transection_ID+"') ;";
        System.out.println("DB "+wallet_ID+Transection_ID);
        try{
            conn = DriverManager.getConnection(url,name,pass);
            Statement myStmt = conn.createStatement();
            ResultSet myRs = myStmt.executeQuery(sql);
            String a = "";
            while(myRs.next()){
                a = myRs.getString(1);
            }
            myStmt.close();
            myRs.close();
            return a;
             
        }catch(Exception e){
            System.out.println("error : "+e);
            return null;
        }
                    
    }
    public int updateAwait(String transection_ID) {
        String sql = "UPDATE transection_flow\n"+
                     "SET State ='Complete'\n"+
                     "WHERE FTransection_ID ='"+transection_ID+"' AND State = '"+State.Awaiting+"';";
       
        try{
            conn = DriverManager.getConnection(url,name,pass);
            Statement myStmt = conn.createStatement();
            myStmt.executeUpdate(sql);
            myStmt.close();
            return 1;
        }catch(Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
            return 0;
        }
    }
    public int updateAllAwaitToIn() {
        String sql = "UPDATE transection_flow\n"+
                     "SET State ='Incomplete'\n"+
                     "WHERE State = 'Awaiting';";
       
        try{
            conn = DriverManager.getConnection(url,name,pass);
            Statement myStmt = conn.createStatement();
            myStmt.executeUpdate(sql);
            myStmt.close();
            return 1;
        }catch(Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
            return 0;
        }
       
    }
    
    public boolean findTransection(String transection_ID) {
        String sql = "SELECT FTransection_ID FROM transection_flow WHERE FTransection_ID = '"+transection_ID+"' ;";
        try{
            conn = DriverManager.getConnection(url,name,pass);
            Statement myStmt = conn.createStatement();
            ResultSet myRs = myStmt.executeQuery(sql);
            while(myRs.next()){
                if(myRs.getString(1).equalsIgnoreCase(transection_ID)) return true;
            }
            myStmt.close();
            myRs.close();
            return false;
             
        }catch(Exception e){
            System.out.println("error : "+e);
            return false;
        }
    }
    
    public ArrayList<Transection_flow<String,State>> searchTransection(String transection_ID) {
        String sql = "SELECT b.Ftransection_ID,b.State FROM transection_flow  as b\n"+
                     "WHERE (b.State = 'Complete') AND \n"+
                     "(b.Transection_flowID >= (SELECT a.Transection_flowID \n"+
                     "FROM transection_flow as a\n"+  
                     "WHERE a.FTransection_ID = '"+transection_ID+"' ));";
        //ArrayList<Transection_flow<String,State>> Qflow = new ArrayList<>();
        try{
            conn = DriverManager.getConnection(url,name,pass);
            Statement myStmt = conn.createStatement();
            ResultSet myRs = myStmt.executeQuery(sql);
            
            while(myRs.next()){
                String FTransection_ID = myRs.getString(1);
                State state = Enum.valueOf(State.class,myRs.getString(2));
                Qflow.add(new Transection_flow(FTransection_ID,state));
            }
            myStmt.close();
            myRs.close();
            return Qflow;
             
        }catch(Exception e){
            System.out.println("error : "+e);
            return Qflow;
        }
    }
    public int delTransection(String transection_id) {
        String sql = "DELETE FROM transection WHERE Transection ='"+transection_id+"'";
        try{
            conn = DriverManager.getConnection(url,name,pass);
            Statement myStmt = conn.createStatement();
            ResultSet myRs = myStmt.executeQuery(sql);
            
            myStmt.close();
            myRs.close();
            return 1;
             
        }catch(Exception e){
            System.out.println("error : "+e);
            return 0;
        }
    }
    public int updateComToInCom(String transection_id){
        String sql = "UPDATE transection_flow\n"+
                     "SET State ='InComplete'\n"+
                     "WHERE State = 'Complete' AND FTransection_ID = '"+transection_id+"'';";
       
        try{
            conn = DriverManager.getConnection(url,name,pass);
            Statement myStmt = conn.createStatement();
            myStmt.executeUpdate(sql);
            myStmt.close();
            return 1;
        }catch(Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
            return 0;
        }
    }
       
}