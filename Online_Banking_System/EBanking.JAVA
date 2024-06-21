import java.util.Scanner;
public class EBanking
{
  static Scanner sc = new Scanner (System.in);
  static Bank bank = new Bank ();
  static Admin admin = new Admin ();
  static Account account = new Account ();
  static Customer customer = new Customer ();
  static Transaction transaction = new Transaction ();
  static OnlineBanking ob = new OnlineBanking ();
  static AccountCreation newAccount = new AccountCreation();


  public static void main (String args[])
  {

	while (true)
	  {
		System.out.println ("\n-----Mainmenu-----\n");
		System.out.println ("1---> Admin");
		System.out.println ("2---> Customer");
		System.out.println ("3---> Exit");
		System.out.println ("--------------------");

		System.out.println ("Enter the Choice");
		int Choice = sc.nextInt ();

		switch (Choice)
		  {
		  case 1:
			{
			l1:

			  while (true)
				{
				  System.out.println ("\n-----Admin Mainmenu-----\n");
				  System.out.println ("1---> Login Admin");
				  System.out.println ("2---> Add Admin");
				  System.out.println ("3---> Add Bank");
				  System.out.println ("4---> Add New Customer");
				  System.out.println ("5---> Total Transaction");
				  System.out.println ("6---> Total Customer");
				  System.out.println ("7---> Exit");

				  System.out.println ("Enter the Choice");
				  int AdminChoice = sc.nextInt ();

				  switch (AdminChoice)
					{
					case 1:
					  {
						admin.getAdminLoginDetials ();
						
						break;
					  }
					  case 2:
					  {
					       admin.getAdminLoginDetials ();
						admin.getAdminDetials ();
						
						break;
					  }
					case 3:
					  {
						bank.getBankDetials ();
						
						break;
					  }
					
					case 4:
					  {
						ob.showTransaction ();
						break;
					  }
					case 5:
					  {
						admin.getTotalUser ();
						break;
					  }
					case 6:
					  break l1;
					}
				}
			  break;
			}
		  case 2:
			{
			l2:

			  while (true)
				{
				  System.out.println ("\n-----Customer Mainmenu-----\n");
				  System.out.println ("1---> Login Customer");
				  System.out.println ("2---> Transaction");
				  System.out.println ("3---> Balance Enquiry");
				  System.out.println ("4---> Transaction History");
				  System.out.println ("5---> Exit");

				  System.out.println ("Enter the choice");
				  int CustomerChoice = sc.nextInt ();

				  switch (CustomerChoice)
					{
					case 1:
					  {  
					       customerLogin();
						break;
					  }
					case 2:
					  {
					       payment();
						break;
					  }
					case 3:
					  {  checkBalance();
						break;
					  }
					case 4:
					  {
					       viewTransactionHistory();
						break;
					  }
					case 5:
					  break l2;
					}
				}
			  break;
			}
		  case 3:
			System.exit (0);
		  }
	  }
  }

  public static void  getAdminLoginDetials ()
  {
	System.out.println ("--------------------");
	System.out.println ("Enter the User Name: ");
	String userName = sc.nextLine ();
	System.out.println ("Enter the Password: ");
	String password = sc.nextLine ();
	System.out.println ("--------------------");
	boolean adminCheck = ob.adminCheck (userName,password);
	if(adminCheck){
	     System.out.println("Welcome : "+ userName);
	}
	else{
	         System.out.println("Invalid login credentials");
	}         
  }

  public static  void  getAdminDetials ()
  {
	System.out.println ("--------------------");
	System.out.println ("Enter the Name: ");
	String name = sc.nextLine ();
	System.out.println ("Enter the User Name: ");
	String userName = sc.nextLine ();
	System.out.println ("Enter the Password: ");
	String password = sc.nextLine ();
	System.out.println ("--------------------");
	ob.addAdmin(name,userName,password);
	System.out.println("You are now an admin");
  }

  public static void  getBankDetials ()
  {
	System.out.println ("--------------------");
	System.out.println ("Enter the Bank Id: ");
	int bank_Id = sc.nextInt();
	sc.nextLine();
	System.out.println ("Enter the Bank name: ");
	String bankName = sc.nextLine ();
	System.out.println ("Enter the Branch Name: ");
	String branchName = sc.nextLine ();
	System.out.println ("Enter the IFSC_CODE: ");
	String ifscCode = sc.nextLine ();
	System.out.println ("Enter the MICR_CODE: ");
	long micrCode = sc.nextLong ();
	System.out.println ("Enter the Mobile No: ");
	long mobileNo = sc.nextLong ();
	System.out.println ("Enter the EMail Id: ");
	String emailId = sc.nextLine ();
	System.out.println ("--------------------");
	ob.addBank (bank_Id,bankName,branchName,ifscCode,micrCode,emailId,mobileNo);
	
  }

  public static void  getCustomerDetials ()
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
      System.out.println("2.SIGN UP");
      
      System.out.println("Enter your choice");
      int choice = sc.nextInt();
      switch(choice){
          case 1:
                 System.out.println("Enter user name");
                 String userName = sc.nextLine();
                 System.out.println("Enter password");
                 String password = sc.next();
                boolean  check = ob.customerCheck(userName,password);
                if(check){
                    System.out.println("Welcome" + userName);
               }
               else{
                    System.out.println("Invalid username or password , please check before login");
               }
               break;
          
          case 2:
                 System.out.println("Welcome to our E-Banking");
                 newAccount.createNewAccount();            
                 System.out.println("please wait an hour for the futher process.If You receive an message from the Bank, Then you can Login. !THANK YOU !");
                 break;
          default :
		System.out.println("INVALID CHOICE , PLEASE ENTER 1 0R 2");
    }		
                
  }
  
  public static void payment(){
       System.out.println("Enter your Account Number");
       long sender = sc.nextLong();
       System.out.println("Enter the Receiver Account Number");
       long receiver = sc.nextLong();
       System.out.println("Enter the amount to transfer");
       double amount = sc.nextDouble();
       if(amount >0){
              transaction.sendMoney(sender,receiver,amount);
       }
       else{
          System.out.println("ENTER THE VALID AMOUNT");
       }
  }
  
  public static  void checkBalance(){
  	      System.out.println("Enter Your Account Number");
  	      long accountNumber = sc.nextLong();
  	      System.out.println("your account Balance is :" +ob.balaceEnquiry(accountNumber));
  } 	
  
  
  public static void viewTransactionHistory(){
             System.out.println("Enter Your Account Number");
  	     long accountNumber = sc.nextLong();
  	     ob.showMyTransaction(accountNumber);
  }	     
               
  	      
                 

}

