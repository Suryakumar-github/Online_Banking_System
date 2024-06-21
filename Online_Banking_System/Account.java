import java.sql.*;

public class Account {
    private Bank bank;
    private Customer customer;
    private String userName;
    private String password;
    private long accountNo;
    private double balance; 
    private int ifscCode;
    static Connection connection;
    public static Account account = null;
    
    private Account(){
    	
    }
    
    public static Account getAccountInstance(){
    	if(account == null){
    		account = new Account();
    	}
    	return account;
    }
    
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Online_Banking", "root", "");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Account(String userName, String password, long accountNo, Customer customer, Bank bank, double balance) {
        this.userName = userName;
        this.password = password;
        this.accountNo = accountNo;
        this.customer = customer;
        this.bank = bank;
        this.balance = balance;
    }

    public Bank getBank() {
        return bank;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public int getIfscCode() {
        return ifscCode;
    }

    public boolean hasSufficientBalance(double amount) {
        return this.balance >= amount;
    }
    
    public void updateBalance(double amount) throws SQLException {
    	//double balance = account.getBalance();
        balance += amount;
        String updateBalanceQuery = "UPDATE Account SET balance = ? WHERE account_No = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(updateBalanceQuery)) {
            statement.setDouble(1, balance);
            statement.setLong(2, accountNo);
            statement.executeUpdate();
        }
     }
}

