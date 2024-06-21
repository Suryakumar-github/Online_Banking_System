public class Customer extends SuperiorCustomer{
	
	
	public static Customer customer = null;
	
	
	public Customer(String name, String email, long mobile_No, long aadhar_No,double balance){
	     super(name,email,mobile_No,aadhar_No,balance);
	 }    
	
	
	Customer(String name, String email, long mobile_No, long aadhar_No){
		this.name = name;
		this.email = email;
		this.mobile_No = mobile_No;
		this.aadhar_No = aadhar_No;
		
	}
	private Customer (){
	
	}
	
	public static Customer getCustomerInstance(){
		if(customer == null){
			customer = new Customer();
		}
		return customer;
	}

	
	public String getName(){
		return name;
	}
	
	public String getEmail(){
		return email;
	}
	
	public long getMobile_No(){
		return mobile_No;
	}
	
	public long getAadhar_No(){
		return aadhar_No;
	}
	
	
	
	public double getBalance(){
		return balance;
	}
}
