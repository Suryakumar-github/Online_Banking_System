public class SQLQueries
{
  public static final String CREATE_BANK_TABLE =
	"CREATE TABLE IF NOT EXISTS Bank(Bank_Id int primary key auto_increment, BankName varchar(20) not null,BranchName varchar(20) not null,IFSC_CODE varchar(11) unique not null, MICR_CODE bigint unique not null, email_Id varchar(20) unique not null,mobile_No bigint unique not null)";
  public static final String CREATE_CUSTOMER_TABLE =
	"CREATE TABLE IF NOT EXISTS Customer(Customer_Id int primary key auto_increment,Customer_Name varchar(30) not null,email_id varchar(20) unique not null,mobile_No bigint unique not null, aadhar_No bigint unique not null, initial_Balance double)";
  public static final String CREATE_ACCOUNT_TABLE =
	"CREATE TABLE IF NOT EXISTS Account(user_Name varchar(20) unique not null,password varchar(15) not null,account_No bigint primary key unique not null,Customer_Id int ,foreign key(Customer_Id) references Customer(Customer_Id) not null,Bank_Id int,foreign key(Bank_Id) references Bank(Bank_Id) not null, Balance double)";
  public static final String CREATE_ADMIN_TABLE =
	"CREATE TABLE IF NOT EXISTS Admin(Admin_Id int primary key auto_increment,Admin_Name varchar(20) not null,Admin_UserName varchar(20) unique not null,Admin_Password varchar(15) not null)";
  public static final String CREATE_TRANSACTION_TABLE =
	"CREATE TABLE IF NOT EXISTS Transaction(Transaction_Id int primary key auto_increment,Sender_Account_No bigint ,foreign key(Sender_Account_No) references Account(account_No) not null,Receiver_Account_No bigint, foreign key(Receiver_Account_No) references Account(account_No) not null,Amount double not null, DateAndTiming TIMESTAMP)";
  public static final String ADD_BANK =
	"INSERT INTO Bank(Bank_Id,BankName, BranchName, IFSC_CODE, MICR_CODE, email_Id, mobile_No) VALUES(?, ?, ?, ?, ?, ?,?)";
  public static final String ADD_CUSTOMER =
	"INSERT INTO Customer(Customer_Name, email_id, mobile_No, aadhar_No,Balance) VALUES(?, ?, ?, ?, ?)";
  public static final String ADD_ACCOUNT =
	"INSERT INTO Account(user_Name, password, account_No, Customer_Id, Bank_Id, Balance) VALUES(?, ?, ?, ?, ?, ?)";
  public static final String CHECK_ADMIN_EXISTENCE =
	"SELECT COUNT(*) FROM Admin WHERE Admin_UserName = ?";
  public static final String ADD_ADMIN =
	"INSERT INTO Admin (Admin_Name, Admin_UserName, Admin_Password) VALUES (?, ?, ?)";
  public static final String GET_CUSTOMER_ID_BY_AADHAR =
	"SELECT Customer_Id FROM Customer WHERE aadhar_No = ?";
  public static final String GET_BANK_ID_BY_IFSC =
	"SELECT Bank_Id FROM Bank WHERE IFSC_CODE = ?";
  public static final String GET_ACCOUNT_BY_NUMBER =
	"SELECT * FROM Account WHERE account_No = ?";
  public static final String GET_BANK_BY_ID =
	"SELECT * FROM Bank WHERE Bank_Id = ?";
  public static final String GET_CUSTOMER_BY_ID =
	"SELECT * FROM Customer WHERE Customer_Id = ?";
  public static final String INSERT_TRANSACTION =
	"INSERT INTO Transaction (Sender_Account_No, Receiver_Account_No, Amount, DateAndTiming) VALUES (?, ?, ?, ?)";
  public static final String VIEW_ALL_TRANSACTIONS =
	"SELECT * FROM Transaction";
  public static final String VIEW_MY_TRANSACTIONS =
	"SELECT t.Sender_Account_No, t.Receiver_Account_No, t.Amount, t.DateAndTiming, a.user_Name FROM Transaction t LEFT JOIN Account a ON t.Sender_Account_No = a.account_No WHERE t.Sender_Account_No = ? GROUP BY t.Sender_Account_No, t.Receiver_Account_No, t.Amount, t.DateAndTiming, a.user_Name";
  public static final String GET_BANK_BY_NAME =
	"SELECT Bank_Id, BranchName, IFSC_CODE, MICR_CODE, email_Id, mobile_No FROM Bank WHERE BankName = ?";
  public static final String BALANCE_ENQUIRY =
	"SELECT Balance FROM Account WHERE account_No = ?";
  public static final String CUSTOMER_CHECK =
	"SELECT user_Name, password FROM Account WHERE user_Name = ?";
  public static final String DISPLAY_BANKS = 
         "SELECT BankName FROM Bank";
  public static final String Password_Check=
        "SELECT password FROM Account where account_No = ?";   
}

