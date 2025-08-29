package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

public class Menu {
  private Scanner scanner;

  public static void main(String[] args) {
    System.out.println("Welcome to the Bank Management System");
    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);
    Customer cus = menu.authenticateUser();
    if (cus != null) {
      Account acc = DataSource.getAccount(cus.getAccountId());
      menu.showMenu(acc, cus);
    } else {
      System.out.println("User Doest Exist.");
    }

    menu.scanner.close();

  }

  private Customer authenticateUser() {
    System.out.println("Enter your userName: ");
    String username = scanner.nextLine();
    System.out.println("Enter your password: ");
    String password = scanner.nextLine();
    Customer cus = null;
    try {
      cus = Authenticator.login(username, password);
    } catch (LoginException e) {
      System.out.println("There is an error. " + e.getMessage());
    }
    return cus;
  }

  public void showMenu(Account acc, Customer cus) {
    int selection = 0;
    while (selection != 4 && cus.isIsAuthenticated()) {
      System.out.println("================ Hello " + cus.getName() + " To Our Bank ====================");
      System.out.println("Please select one of the following options: ");
      System.out.println("1:Deposit");
      System.out.println("2:Withdraw");
      System.out.println("3:Check Balance");
      System.out.println("4:Exit");
      System.out.println("================ It's Great " + cus.getName() + "  ====================");
      selection = scanner.nextInt();
      double amount = 0;
      switch (selection) {
        case 1:
          System.out.println("How much would you like to deposit?");
          amount = scanner.nextDouble();
          acc.deposit(amount);
          break;
        case 2:
          System.out.println("How much would you like to withdraw?");
          amount = scanner.nextDouble();
          acc.withdraw(amount);
          break;

        case 3:
          System.out.println("Current balance: " + acc.getBalance());
          break;

        case 4:
          Authenticator.logout(cus);
          System.out.println("Thanks for banking with us.");
          break;

        default:
          System.out.println("Invalid option. Please try again.");
          break;
      }
    }
  }

}
