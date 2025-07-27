package bank;

import javax.security.auth.login.LoginException;

public class Authenticator {
  public static Customer login(String username, String password) throws LoginException {
    Customer cus = DataSource.getCustomer(username);
    if (cus == null) {
      throw new LoginException("User not found");
    }
    if (password.equals(cus.getPassword())) {
      cus.setIsAuthenticated(true);
      return cus;
    } else
      throw new LoginException("Incorrect passsword");
  }

  public static void logout(Customer customer) {
    customer.setIsAuthenticated(false);
  }
}
