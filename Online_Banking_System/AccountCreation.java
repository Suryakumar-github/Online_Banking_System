import java.util.Scanner;

class AccountCreation implements CreateAccount {
    private OnlineBanking onlineBank = OnlineBanking.getOnlineBankingInstance() ;
   public static  AccountCreation  accountCreation = null;

    public AccountCreation(OnlineBanking onlineBank) {
        this.onlineBank = onlineBank;
    }
     private  AccountCreation(){
     }
     
     public static AccountCreation getAccountCreationInstance(){
             if(accountCreation==null){
                    accountCreation = new AccountCreation();
             }
             return   accountCreation;
    }         

    public long createNewAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter initial amount: ");
        double initialAmount = scanner.nextDouble();

        System.out.print("Enter customer name: ");
        scanner.nextLine();  // Consume newline
        String customerName = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter mobile number: ");
        long mobileNo = scanner.nextLong();

        System.out.print("Enter aadhar number: ");
        long aadharNo = scanner.nextLong();

        onlineBank.displayBanks();
        System.out.println("Enter the Bank Name: ");
        String bankName = scanner.next().toUpperCase();
        
        Bank bank = onlineBank.getBankByName(bankName);
        if (bank == null) {
            System.out.println("Bank not found.");
            return 0; // Exit if bank not found
        }

        // Create Customer for New account
        Customer customer = new Customer(customerName, email, mobileNo, aadharNo, initialAmount);

        // Add Customer and Account
        onlineBank.addCustomer(customer);
        long account_number = bank.generateAccountNumber();
        Account account = new Account(username, password, account_number, customer, bank, initialAmount);
        onlineBank.addAccount(account);

        System.out.println("Account created successfully!");
        return account_number;
    }
}

