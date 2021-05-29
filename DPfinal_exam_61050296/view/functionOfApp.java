package view;
/*
    create by Supawich
     เป็น interface ของตัว App เพื่อกำหนดว่า app ต้องมีความสามารถอะไรบ้าง
*/ 

public interface functionOfApp{
    String addTransection();
    String submitTransection();
    String searchTransection(model.ItfDataControl.State state);
    String searchForwardTransection();
}
