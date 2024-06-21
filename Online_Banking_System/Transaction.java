import java.sql.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Transaction extends CustomException{
    private int transactionId;
    private long senderAccountNo;
    private long receiverAccountNo;
    private double amount;
    private LocalDateTime dateAndTiming;
    public static Transaction transaction = null;
    
    
    static OnlineBanking onlineBanking = OnlineBanking.getOnlineBankingInstance();


   private Transaction(){
    	
    }
    
      public Transaction(int transactionId, long senderAccountNo, long receiverAccountNo, double amount, LocalDateTime dateAndTiming) {
        this.transactionId = transactionId;
        this.senderAccountNo = senderAccountNo;
        this.receiverAccountNo = receiverAccountNo;
        this.amount = amount;
        this.dateAndTiming = dateAndTiming;
     }
     
     public static Transaction getTransactionInstance(){
             if(transaction==null){
                   transaction = new Transaction();
              }
              return transaction;
     }              

    public void sendMoney(long senderAccountNumber, long receiverAccountNumber, double amount,String password) throws SQLException 
      {
         
        Account sender = onlineBanking.getAccountByNumber(senderAccountNumber);
        Account receiver = onlineBanking.getAccountByNumber(receiverAccountNumber);

        if (sender != null && receiver != null ) {
            if (sender.hasSufficientBalance(amount)) {
              if(onlineBanking.passwordCheck(password,senderAccountNumber)){
                sender.updateBalance(-amount);
                receiver.updateBalance(amount);
                System.out.println(amount + " sent from " + sender.getUserName() + " to " + receiver.getUserName());
               // Optionally, update the database with the new balances
             // updateAccountBalance(sender);
              //  updateAccountBalance(receiver);
              onlineBanking.updateTransaction(senderAccountNumber,receiverAccountNumber,amount);
              }
              else{
                 System.out.println("Password mismatch.Sure before enter your password");
                } 
            } else {
                System.out.println("Insufficient balance for transaction.");
            }
        } else {
            if (sender == null) {
                System.out.println("Sender account is null.");
            }
            if (receiver == null) {
                System.out.println("Receiver account is null.");
            }
        }
    }
    
    public String toString() {
        return 
                "\n\t\ttransactionId=" + transactionId +
                "\t\t\t senderAccountNo=" + senderAccountNo +
                "\t\treceiverAccountNo=" + receiverAccountNo +
                "\t\t amount=" + amount +
                "\t\t dateAndTiming ="+dateAndTiming ;
                
               }
                
   

 
}


