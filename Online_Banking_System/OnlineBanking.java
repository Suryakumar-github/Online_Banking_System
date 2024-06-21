import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class OnlineBanking {
Connection connection = DatabaseConnection.getConnection();
    static Scanner scanner;
    static Account account = Account .getAccountInstance();
    static Transaction transaction =Transaction.getTransactionInstance();
    public static OnlineBanking onlineBanking = null;
    
    Admin admin = new Admin("root", "MainAdmin", "MainAdmin123");

    private OnlineBanking() {
    }
    
    public static OnlineBanking getOnlineBankingInstance(){
          if(onlineBanking==null){
                onlineBanking = new OnlineBanking();
         }
         return  onlineBanking;
    }  

    public void createBankTable() {
        try {
        
            PreparedStatement statement = StatementProvider.getPreparedStatement(SQLQueries.CREATE_BANK_TABLE);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createCustomerTable() {
        try {
            PreparedStatement statement = StatementProvider.getPreparedStatement(SQLQueries.CREATE_CUSTOMER_TABLE);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createAccountTable() {
        try {
            PreparedStatement statement = StatementProvider.getPreparedStatement(SQLQueries.CREATE_ACCOUNT_TABLE);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createAdminTable() {
        try {
            PreparedStatement statement = StatementProvider.getPreparedStatement(SQLQueries.CREATE_ADMIN_TABLE);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTransactionTable() {
        try {
            PreparedStatement statement = StatementProvider.getPreparedStatement(SQLQueries.CREATE_TRANSACTION_TABLE);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean addBank(Bank bank) {
        try {
            PreparedStatement statement = StatementProvider.getPreparedStatement(SQLQueries.ADD_BANK);
            statement.setInt(1,bank.getBankId());
            statement.setString(2, bank.getName());
            statement.setString(3, bank.getBranch_Name());
            statement.setString(4, bank.getIfsc_Code());
            statement.setLong(5, bank.getMicr_Code());
            statement.setString(6, bank.getEmail_Id());
            statement.setLong(7, bank.getPhone_No());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean addCustomer(Customer customer) {
        try {
            PreparedStatement statement = StatementProvider.getPreparedStatement(SQLQueries.ADD_CUSTOMER);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setLong(3, customer.getMobile_No());
            statement.setLong(4, customer.getAadhar_No());
            statement.setDouble(5, customer.getBalance());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean addAccount(Account account) {
        try (PreparedStatement statement = StatementProvider.getPreparedStatement(SQLQueries.ADD_ACCOUNT)) {
            statement.setString(1, account.getUserName());
            statement.setString(2, account.getPassword());
            statement.setLong(3, account.getAccountNo());
            int customerId = getCustomer_Id(account.getCustomer());
            int bankId = getBank_Id(account.getBank());
            if (customerId == -1 || bankId == -1) {
                return false;
            }
            statement.setInt(4, customerId);
            statement.setInt(5, bankId);
            statement.setDouble(6, account.getBalance());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return false;
    }

    public int getCustomer_Id(Customer customer) {
        try (PreparedStatement stmt = StatementProvider.getPreparedStatement(SQLQueries.GET_CUSTOMER_ID_BY_AADHAR)) {
            stmt.setLong(1, customer.getAadhar_No());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    System.out.println("No customer found with the given Aadhar number.");
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return -1;
    }

    public int getBank_Id(Bank bank) {
        try (PreparedStatement stmt = StatementProvider.getPreparedStatement(SQLQueries.GET_BANK_ID_BY_IFSC)) {
            stmt.setString(1, bank.getIfsc_Code());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    System.out.println("No bank found with the given IFSC code.");
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return -1;
    }

    public boolean adminCheck(String userName ,String password){
    
    	
            if(userName.equals(admin.getRootUserName() )&& password.equals(admin.getRootPassword())){
                return true;
            }
            else{
            return false;
          }  
  }    
 
 
public boolean addAdmin(String name, String userName, String password) {
    try {
     
        PreparedStatement existenceStatement = StatementProvider.getPreparedStatement(SQLQueries.CHECK_ADMIN_EXISTENCE);
        existenceStatement.setString(1, userName);
        ResultSet rs = existenceStatement.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        if (count > 0) {
            System.out.println("Username already exists. Please choose a different username.");
            return false;
        }

       
        PreparedStatement statement = StatementProvider.getPreparedStatement(SQLQueries.ADD_ADMIN);
        statement.setString(1, name);
        statement.setString(2, userName);
        statement.setString(3, password);
        statement.executeUpdate();

        System.out.println("Admin added successfully");
        return true;
    } catch (SQLException e) {
        System.out.println(e);
    }
    return false;
}


    public Account getAccountByNumber(long accountNo) {
        try (PreparedStatement stmt = StatementProvider.getPreparedStatement(SQLQueries.GET_ACCOUNT_BY_NUMBER)) {
            stmt.setLong(1, accountNo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                 Bank bank =getBankById(rs.getInt("Bank_Id"));
                 Customer customer =getCustomerById(rs.getInt("Customer_Id"));
                    return new Account(rs.getString("user_Name"), rs.getString("password"), rs.getLong("account_No"), customer, bank, rs.getDouble("Balance"));
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return null;
    }
    
    public Bank getBankById(int bankId) {
        try {
    
            PreparedStatement statement = StatementProvider.getPreparedStatement(SQLQueries.GET_BANK_BY_ID);
            statement.setInt(1, bankId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Bank(rs.getString("BankName"), rs.getString("BranchName"), rs.getString("IFSC_CODE"), rs.getLong("MICR_CODE"), rs.getString("email_Id"), rs.getLong("mobile_No"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Customer getCustomerById(int customerId) {
        try {
            PreparedStatement statement = StatementProvider.getPreparedStatement(SQLQueries.GET_CUSTOMER_BY_ID);
            statement.setInt(1, customerId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getString("Customer_Name"), rs.getString("email_id"), rs.getLong("mobile_No"), rs.getLong("aadhar_No"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean updateTransaction(long senderAccountNo, long receiverAccountNo, double amount) {
        try (PreparedStatement stmt = StatementProvider.getPreparedStatement(SQLQueries.INSERT_TRANSACTION)) {
            stmt.setLong(1, senderAccountNo);
            stmt.setLong(2, receiverAccountNo);
            stmt.setDouble(3, amount);
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return false;
    }

    public List<Transaction> showTransaction() {
        List<Transaction> transactions = new ArrayList<>();
        try (PreparedStatement stmt = StatementProvider.getPreparedStatement(SQLQueries.VIEW_ALL_TRANSACTIONS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                transactions.add(new Transaction(rs.getInt("Transaction_Id"), rs.getLong("Sender_Account_No"), rs.getLong("Receiver_Account_No"), rs.getDouble("Amount"), rs.getTimestamp("DateAndTiming").toLocalDateTime()));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return transactions;
    }

     public boolean showMyTransaction(long accountNumber) {
       
      try (PreparedStatement statement = StatementProvider.getPreparedStatement(SQLQueries.VIEW_MY_TRANSACTIONS)) {
        statement.setLong(1, accountNumber);

        try (ResultSet resultSet = statement.executeQuery()) {
        	boolean hasResults = false;
            while (resultSet.next()) {
            	hasResults = true;
                long senderAccountNo = resultSet.getLong("Sender_Account_No");
                long receiverAccountNo = resultSet.getLong("Receiver_Account_No");
                double amount = resultSet.getDouble("Amount");

                Timestamp timestamp = resultSet.getTimestamp("DateAndTiming");
                LocalDateTime dateAndTiming = timestamp != null ? timestamp.toLocalDateTime() : null;
                String userName = resultSet.getString("user_Name");

                // Retrieve receiver's username
                String receiverQuery = "SELECT user_Name FROM Account WHERE account_No = ?";
                try (PreparedStatement receiverStatement = connection.prepareStatement(receiverQuery)) {
                    receiverStatement.setLong(1, receiverAccountNo);
                    try (ResultSet rs = receiverStatement.executeQuery()) {
                        String receiverName = "";
                        if (rs.next()) {
                            receiverName = rs.getString("user_Name");
                        }

                        System.out.println("Sender_Account_No =:" + senderAccountNo +
                                ", Receiver_Account_No = :" + receiverAccountNo +
                                ", Amount = :" + amount +
                                ", DateAndTiming = : " + dateAndTiming +
                                ", user_Name = :" + userName +
                                ", Receiver Name = :" + receiverName);
                                
                                
                    }
                }
            }
            return hasResults;
            
        }
    
    } 
   
    catch (SQLException e) {
        System.out.println("SQL Exception: " + e.getMessage());
    }
    return false;
}
    
    public boolean customerCheck(String user_Name, String password)throws SQLException{

   
    try (PreparedStatement stmt = StatementProvider.getPreparedStatement(SQLQueries.CUSTOMER_CHECK)) {
        stmt.setString(1, user_Name);
     //   stmt.setString(2, password);
        try (ResultSet rs = stmt.executeQuery()) {
         
          while(rs.next()){
          String userName = rs.getString("user_Name");
          String passWord = rs.getString("passWord");
          if(user_Name.equals(userName) && password.equals(passWord)){
          return true;
          }
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return false;
  }
 } 
 
  public boolean passwordCheck(String Password,long accountNumber)throws SQLException{
     
     try(PreparedStatement statement = StatementProvider.getPreparedStatement(SQLQueries.Password_Check)){
            statement.setLong(1, accountNumber);
            
            try(ResultSet rs = statement.executeQuery()){
                 while(rs.next()){
                       String password = rs.getString("password");
                       if(password.equals(Password)){
                             return true;
                       }
               }
           }
           catch(SQLException e) {
                 System.out.println(e.getMessage());
           }
           return false;
     }
 }                                 
                       
  
  
	public double balaceEnquiry(long accountNumber,String password)throws SQLException{
	//String query = SQLQueries.BALANCE_ENQUIRY;
	
       if(  passwordCheck(password,accountNumber)){
    try (PreparedStatement stmt = StatementProvider.getPreparedStatement(SQLQueries.BALANCE_ENQUIRY)){
        stmt.setLong(1, accountNumber);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                System.out.println("No customer found with the given Account number.");
                 return -1.0;
            }
        }
    } catch (SQLException e) {
        System.out.println("SQL Exception: " + e.getMessage());
    }
    }
    else{
         System.out.println("password mismatch.sure before enter your password");
     }    
    return -1.0;
}
   public void displayBanks(){
          List<String> bankNames = new ArrayList<>();
          
           
           try{
               Statement stmt = connection.createStatement();
               ResultSet rs = stmt.executeQuery(SQLQueries.DISPLAY_BANKS);
                while(rs.next()){
                       bankNames.add(rs.getString("BankName"));
                }
                for(String bankName : bankNames){
                     System.out.println(bankName);
               }
          }
         
          catch(SQLException e){
                e.getMessage();
          }
         
    }   
    public Bank getBankByName(String bankName) {
        
        try (PreparedStatement stmt = StatementProvider.getPreparedStatement(SQLQueries.GET_BANK_BY_NAME)) {
            stmt.setString(1, bankName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int bankId = rs.getInt("Bank_Id");
                    String branchName = rs.getString("BranchName");
                    String ifscCode = rs.getString("IFSC_CODE");
                    long micrCode = rs.getLong("MICR_CODE");
                    String emailId = rs.getString("email_Id");
                    long mobileNo = rs.getLong("mobile_No");
                    return new Bank(bankId, bankName, branchName, ifscCode, micrCode, emailId, mobileNo);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return null; 
    }
    
    public boolean validateMobileNo(String mobile_No)throws NumberFormatException{
    	if(mobile_No.length() == 10){
		        
		Integer.parseInt(mobile_No);
		return true;
	}
	return false;


   }
    
}


