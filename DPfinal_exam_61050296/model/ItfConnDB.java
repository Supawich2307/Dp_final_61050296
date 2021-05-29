package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public interface ItfConnDB{
    ConnDatabase connDB = new ConnDatabase();
}