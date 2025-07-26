package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
  public static Connection connect() {
    String dbFile = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(dbFile);
      System.out.println("Connected to Db.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  public static Customer getCustomer(String userName){
     Customer cus=null;
     return cus;
  }

  public static void main(String[] args) {
    Connection cc = connect();
    try {
      cc.close();
      System.out.println("Database closed.");
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
}
