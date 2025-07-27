package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {
  // Method to create connection with database.
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

  // Method to fetch the records of given user
  public static Customer getCustomer(String userName) {
    Customer cus = null;
    // This is preparedStatement
    String query = "select * from customers where username=?";
    try {
      Connection conn = connect();
      PreparedStatement pState = conn.prepareStatement(query);
      pState.setString(1, userName);
      ResultSet resultSet = pState.executeQuery();
      boolean flag = false;
      while (resultSet.next()) {
        flag = true;
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        int accountId = resultSet.getInt("account_id");
        cus = new Customer(id, name, username, password, accountId);
      }
      if (!flag) {
        System.out.println("Records Not Found.");
      }
      try {
        conn.close();
        System.out.println("Connection Closed Successfully.");
      } catch (SQLException e) {
        e.printStackTrace();
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return cus;
  }

  public static void main(String[] args) {
    // Connection cc = connect();
    // try {
    // cc.close();
    // System.out.println("Database closed.");
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    Customer cus = getCustomer("ttoulchi5@ehow.com");
    if (cus != null) {
      System.out.println(cus.getId() + "\n" + cus.getName() + "\n" + cus.getPassword());
    }

  }
}
