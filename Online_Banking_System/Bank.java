import java.util.Random;

public class Bank extends SuperiorBank{
       
     /*  private String bankName;
       private String branch_Name;

	private String ifsc_Code;
	private long micr_Code;
	private String email_Id;
	private long phone_No;
	private int bank_Id;*/
	
	private static final Random random = new Random();
	public static Bank bank = null;
	
       public Bank(int bank_Id,String bankName,String  branch_Name, String ifsc_Code, long micr_Code, String email_Id, long phone_No){
             super(bank_Id,bankName,branch_Name,ifsc_Code,micr_Code,email_Id,phone_No);
       }      
	
	Bank(String bankName,String  branch_Name, String ifsc_Code, long micr_Code, String email_Id, long phone_No){
		
		this.bankName= bankName;
		this.branch_Name = branch_Name;
		this.ifsc_Code = ifsc_Code;
		this.micr_Code = micr_Code;
		this.email_Id = email_Id;
		this.phone_No = phone_No;
	}
	
	private Bank (){
	}
	
	public static Bank getBankInstance(){
		if(bank == null){
			bank = new Bank();
		}
		
		return bank;
	}
	
	public String getName(){
		return bankName;
	}
	public int getBankId(){
		return bank_Id;
	}
	
	public String getBranch_Name(){
		return branch_Name;
	}
	
	public String getIfsc_Code(){
		return ifsc_Code;
	}
	
	public long getMicr_Code(){
		return micr_Code;
	}
	
	public String getEmail_Id(){
		return email_Id;
	}
	
	public long getPhone_No(){
		return phone_No;
	}
	
	

    public long generateAccountNumber() {
    	int[] numbers = new int[]{0,1,2,3,4,5,6,7,8,9};
    	String account_number = "";
    	for(int i=1; i<=11; i++){
    		
    		account_number += numbers[random.nextInt(10)];
    		
    	}
    		
        return Long.valueOf(account_number);
    }
	

	        
	
	
}
	
