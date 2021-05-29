package controller;

import java.util.Scanner;
import model.Transection;

/*
    create by Supawich
    เป็น interface ที่เก็บ Pool ของ obj เอาไว้ใช้
*/ 

public interface  ObjController{
    public Scanner sc = new Scanner(System.in);
    public Transection objTransection = new Transection();
    public add_transection_controller addC = new add_transection_controller();
    public search_controller searchC = new search_controller();
    public submit_controller submitC = new submit_controller();
 }
