abstract class SuperiorBank{

        public String bankName;
	public String branch_Name;
	public String ifsc_Code;
	public long micr_Code;
	public String email_Id;
	public long phone_No;
	public int bank_Id;
	
   public SuperiorBank(){
   
   }
	
	
     public SuperiorBank(int bank_Id,String bankName,String  branch_Name, String ifsc_Code, long micr_Code, String email_Id, long phone_No){
		this.bank_Id = bank_Id;
		this.bankName= bankName;
		this.branch_Name = branch_Name;
		this.ifsc_Code = ifsc_Code;
		this.micr_Code = micr_Code;
		this.email_Id = email_Id;
		this.phone_No = phone_No;
  }
}
