
import java.sql.*;
import java.util.Scanner;
class Dummy {
  private static Scanner input =new Scanner(System.in); 
    public static void main(String[] args) {
        OnlineBanking ob = new OnlineBanking();
    //   Admin admin = new Admin("MANI","Mani23","Mani@123");
        // Creating a Bank object
        Bank bank = new Bank("indian", "Srivi", "SBIO000007", 94321, "sk@2344", 98321L);
        //Bank bank2 = new Bank("Indian bank","srivi",IDIB
        // Adding the bank to the database
       // ob.addBank(bank);

        // Creating Customer objects
  //      Customer customer = new Customer("STR", "str@12", 9876509L, 1234567812L);
   //     Customer customer2 = new Customer("simbu", "silambarasan@123", 23456L, 6797L);
        
    //    Customer customer3 = new Customer("stephen ","srtm@123",34567l,12345678l);

        // Adding customers to the database
        //ob.addCustomer(customer);
        //ob.addCustomer(customer2);

        // Creating an Account object
     //   Account account = new Account("simbu", "pat@30", 6380629435L, customer2, bank);

        // Adding the account to the database
      //  ob.addAccount(account);
      
      
    
/*    System.out.println("Enter Admin name");
    String Name = input.nextLine();
    System.out.println("Enter username");
    String username = input.nextLine();
    System.out.println("Enter password");
    String password = input.nextLine();
    
   boolean check= ob.AdminCheck(username,password);
   
   
   if(check){
       System.out.println("welcome :"+Name);
       //ob.addCustomer(customer3);
           System.out.println("Enter Admin name");
      String newName = input.nextLine();
    System.out.println("Enter username");
    
    String newUserName = input.nextLine();
    
    System.out.println("Enter password");
   
    String newPassword = input.nextLine();
       ob.addAdmin(newName,newUserName,newPassword);
       System.out.println("Adnin was added successfully");
   }
   else{
       System.out.println("Invalid username or password");
   }  */
      //  Bank bank2 = new Bank(
        
     //  Account account6 = new Account("simbu","simbu@23",3456l,customer2,bank);
      // ob.addAccount(account6);
       //Customer customer6 = new Customer("Aryan","aryan@18",987654l,3405676534l);
     //  ob.addCustomer(customer6);
      // Account account7 = new Account("aryan","aryan@18",123490l,customer6,bank);
    //   ob.addAccount(account7);
       
     Transaction  dummy = new Transaction();
// System.out.println(ob.balaceEnquiry(123490L));
try{
System.out.println(ob.customerCheck("mahi","mahi@6"));
 }
 catch(SQLException w){
   }
     
/* Customer c1 = new Customer("prabha","prabha@122345",35345l,1287985l,5000.00);
     Account a1 = new Account("prabha", "prabha@122345", 98718871673l,c1,bank,5000.00);
    Customer  c2 = new Customer("mahi","mahi@986",98567776l,139905l,10000.00);
    Account a2 = new Account("mahi","mahi@986",12111766l,c2,bank,10000.00);
    
    ob.addCustomer(c1);
    ob.addCustomer(c2);
   ob.addAccount(a1);
    ob.addAccount(a2);*/
        
//    dummy.sendMoney(6797l,2341l,500.00); 

      OnlineBanking onlineBanking = new OnlineBanking();
	//long sender = a1.getAccountNo();
        //long receiver = a2.getAccountNo();
        
        // Create a Transaction object
     //   Transaction transaction = new Transaction(onlineBanking);
        
     //   try{
       // 	transaction.sendMoney(12111766l, 12908766l, 500.00);
      //  }
       // catch(SQLException e){
        //	e.getMessage();
        }

    }
 //}   
 
 
/* public class Dummy {
    public static void main(String[] args) {
        // Assuming you have initialized connection and other necessary objects
        
        // Assuming you have retrieved sender and receiver accounts from the database
        Account sender = ...; // Retrieve sender account
        Account receiver = ...; // Retrieve receiver account
        
        // Create a Transaction object
        Transaction transaction = new Transaction();
        
        // Call the sendMoney method with sender, receiver, and amount
        transaction.sendMoney(sender, receiver, 500.00);
    }
}  */
