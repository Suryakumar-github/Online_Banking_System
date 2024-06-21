abstract class SuperiorCustomer{

        public String name, email;
	public long mobile_No,aadhar_No;
	public int customerCount = 0;
	public double balance;
	
	
	public SuperiorCustomer(String name, String email, long mobile_No, long aadhar_No,double balance){
		this.name = name;
		this.email = email;
		this.mobile_No = mobile_No;
		this.aadhar_No = aadhar_No;
		this.balance = balance;
	}
	
	public SuperiorCustomer(){
	
	}
}	
	
	


