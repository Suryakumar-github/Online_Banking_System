import java.util.Scanner;
import java.sql.SQLException;
public class EBanking
{
  static Scanner sc = new Scanner (System.in);
 

	static Bank bank = Bank.getBankInstance();
	static Account account = Account.getAccountInstance();
	static Admin admin = Admin.getAdminInstance();
	static Customer customer = Customer.getCustomerInstance();
	static Transaction transaction=Transaction.getTransactionInstance();
	static  OnlineBanking onlineBanking=OnlineBanking.getOnlineBankingInstance();
	static AccountCreation accountCreation =AccountCreation.getAccountCreationInstance();
	 
	

  public static void main (String args[])
  {

	while (true) {
            displayMainMenu();

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
            }

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    customerMenu();
                    break;
                case 3:
                    exitMenu();
                    break;
                default:
                    System.out.println("Invalid choice, Please Select Valid choice: " + choice);
            }
        }
    }

    public static void displayMainMenu() {
        System.out.println("--------------------------------------------");
        System.out.println("|Option |         Mainmenu                 |");
        System.out.println("|-------+----------------------------------|");
        System.out.println("|   1   |Admin                             |");
        System.out.println("|   2   |Customer                          |");
        System.out.println("|   3   |Exit                              |");
        System.out.println("--------------------------------------------");
        System.out.println("Enter the Choice");
    }

    public static void adminMenu() {
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("| Option |          Admin Mainmenu         |");
            System.out.println("|--------+---------------------------------|");
            System.out.println("|   1    |Login Admin                      |");
            System.out.println("|   2    |Add Admin                        |");
            System.out.println("|   3    |Back to Mainmenu                 |");
            System.out.println("--------------------------------------------");
            System.out.println("Enter the Choice");

            int adminChoice = sc.nextInt();

            switch (adminChoice) {
                case 1:
                   getAdminLoginDetails();
                    adminSubMenu();
                    break;
                case 2:
                     if(getAdminLoginDetails()){
				getAdminDetails ();
		     }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice, Please Select Valid choice: " + adminChoice);
            }
        }
    }

    public static void adminSubMenu() {
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("| Option |          Description            |");
            System.out.println("|--------+---------------------------------|");
            System.out.println("|   1    |Add Bank                         |");
            System.out.println("|   2    |Total Transaction                |");
            System.out.println("|   3    |Back to Admin Menu               |");
            System.out.println("|   4    |Back to MainMenu                 |");
            System.out.println("--------------------------------------------");
            System.out.println("Enter the Choice: ");
           
            int Choice = sc.nextInt();
           
            switch (Choice) {
                case 1:
                    getBankDetails();
                    break;
                case 2:
                    System.out.println(onlineBanking.showTransaction());
                    break;
                case 3:
                    return;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice, Please Select Valid choice: " + Choice);
            }
        }
    }

    public static void customerMenu() {
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("| Option |       Customer Mainmenu         |");
            System.out.println("|--------+---------------------------------|");
            System.out.println("|   1    |Login Customer                   |");
            System.out.println("|   2    |New Customer                     |");
            System.out.println("|   3    |Back to Mainmenu                 |");
            System.out.println("--------------------------------------------");
            System.out.println("Enter the choice");

            int customerChoice = sc.nextInt();

            switch (customerChoice) {
                case 1:
                    customerLogin();
               //     customerSubMenu();
                    break;
                case 2:
                    newCustomer();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice, Please Select Valid choice: " + customerChoice);
            }
        }
    }

    public static void customerSubMenu() {
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("| Option |          Description            |");
            System.out.println("|--------+---------------------------------|");
            System.out.println("|   1    |Transaction                      |");
            System.out.println("|   2    |Balance Enquiry                  |");
            System.out.println("|   3    |Transaction History              |");
            System.out.println("|   4    |Back to Customer Menu            |");
            System.out.println("|   5    |Back to Mainmenu                 |");
            System.out.println("--------------------------------------------");
            System.out.println("Enter the Choice: ");
           
            int mChoice = sc.nextInt();
           
            switch (mChoice) {
                case 1:
              
                    payment();
                    break;
                case 2:
                  try{
                    checkBalance();
                    }
                    catch(SQLException e){
                         System.out.println(e.getMessage());
                     }    
                    break;
                case 3:
                    viewTransactionHistory();
                    break;
                case 4:
                    return;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice, Please Select Valid choice: " + mChoice);
            }
        }
    }

    public static void exitMenu() {
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("| Option |            Exit Menu            |");
            System.out.println("|--------+---------------------------------|");
            System.out.println("|   1    |Are you Sure?                    |");
            System.out.println("|   2    |Stay Here                        |");
            System.out.println("--------------------------------------------");
            System.out.println("Enter the choice");

            int eChoice = sc.nextInt();

            switch (eChoice) {
                case 1:
                    System.exit(0);
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice, Please Select Valid choice: " + eChoice);
            }
        }
    }

  public static boolean getAdminLoginDetails ()
  {
	System.out.println ("--------------------");
	System.out.println ("Enter the User Name: ");
	String userName = sc.next ();
	System.out.println ("Enter the Password: ");
	String password = sc.next ();
	System.out.println ("--------------------");
	boolean adminCheck = onlineBanking.adminCheck (userName,password);
	if(adminCheck){
	     System.out.println("Welcome : "+ userName);
	}
	else{
	         System.out.println("Invalid login credentials");
	         return false;
	}
	return true;         
  }

  public static  void getAdminDetails ()
  {
	System.out.println ("--------------------");
	System.out.println ("Enter the Name: ");
	String name = sc.next ();
	System.out.println ("Enter the User Name: ");
	String userName = sc.next ();
	System.out.println ("Enter the Password: ");
	String password = sc.next ();
	System.out.println ("--------------------");
	onlineBanking.addAdmin(name,userName,password);
	System.out.println(name +" " +" is now an admin");
  }

  public static void  getBankDetails ()throws NumberFormatException
  {
	System.out.println ("--------------------");
	System.out.println ("Enter the Bank Id: ");
	int bank_Id = sc.nextInt();
	sc.nextLine();
	System.out.println ("Enter the Bank name: ");
	String bankName = sc.next ();
	System.out.println ("Enter the Branch Name: ");
	String branchName = sc.next();
	System.out.println ("Enter the IFSC_CODE: ");
	String ifscCode = sc.next ();
	System.out.println ("Enter the MICR_CODE: ");
	long micrCode = sc.nextLong ();
	System.out.println ("Enter the Mobile No: ");
	long mobileNo = sc.nextLong ();
	onlineBanking.validateMobileNo(String.valueOf(mobileNo));
	System.out.println ("Enter the EMail Id: ");
	String emailId = sc.next ();
	System.out.println ("--------------------");
	Bank new_Bank = new Bank(bank_Id,bankName,branchName,ifscCode,micrCode,emailId,mobileNo);
	onlineBanking.addBank(new_Bank);
	System.out.println("Bank added successfully");
  }

  public static void  getCustomerDetails ()
  {
	System.out.println ("--------------------");
	System.out.println ("Enter the Customer name: ");
	String customerName = sc.nextLine ();
	System.out.println ("Enter the EMail Id: ");
	String emailId = sc.nextLine ();
	System.out.println ("Enter the Mobile No: ");
	long mobileNo = sc.nextLong ();
	System.out.println ("Enter the Aadhar No: ");
	long aadhar = sc.nextLong ();
	System.out.println ("--------------------");
  }
  
  public static void customerLogin(){
      System.out.println ("--------------------");
      System.out.println("1.LOGIN");
   try{   
   
                 System.out.println("Enter user name");
                 String userName = sc.next();
                 System.out.println("Enter password");
                 String password = sc.next();
                boolean  check = onlineBanking.customerCheck(userName,password);
                if(check){
                    System.out.println("Welcome" + userName);
                    customerSubMenu();
               }
               else{
                    System.out.println("Invalid username or password , please check before login");
               }
               		
    }
    catch(SQLException e){
        e.getMessage();
     }   		
                
  }
  
  public static void newCustomer(){
            System.out.println ("--------------------");
            System.out.println("1.SIGN UP");
            System.out.println("Welcome to our E-Banking");
            long accountNum=accountCreation.createNewAccount();
            if(accountNum>0){       
             System.out.println("please wait an hour for the futher process.If You receive an message from the Bank, Then you can Login. !THANK YOU !");
             System.out.println("Your account number is :" + accountNum);
          }
             else{
                 return;
             }
   }              
         
                
            
  
  
  public static void payment(){
       System.out.println("Enter your Account Number");
       long sender = sc.nextLong();
       System.out.println("Enter your password");
       String password = sc.next();
       System.out.println("Enter the Receiver Account Number");
       long receiver = sc.nextLong();
       if(sender != receiver){
       System.out.println("Enter the amount to transfer");
       double amount = sc.nextDouble();
       try{
       
       	if(amount >0 ){
              transaction.sendMoney(sender,receiver,amount,password);
             }else{
          System.out.println("ENTER THE VALID AMOUNT");
       }

       
      }
      catch( SQLException e){
          e.getMessage();
      }     
  }
  else{
  	System.out.println("Sender and Receiver Should Not be Same");
  }
 }
  
  public static  void checkBalance()throws SQLException{
  	      System.out.println("Enter Your Account Number");
  	     
  	      long accountNumber = sc.nextLong();
  	      sc.nextLine();
  	      System.out.println("Enter your password");
  	      String password = sc.next();    
  	      System.out.println("your account Balance is :" +onlineBanking.balaceEnquiry(accountNumber,password));
  } 	
  
  
  public static void viewTransactionHistory(){
             System.out.println("Enter Your Account Number");
  	     long accountNumber = sc.nextLong();
  	     if(!onlineBanking.showMyTransaction(accountNumber)){
  	     		System.out.println("This Account Number is Not Available");
  	     	}
  }	     
               
  	      
                 

}

