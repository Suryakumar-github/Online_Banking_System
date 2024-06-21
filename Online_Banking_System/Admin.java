public class Admin{
	
	private String name;
	private String user_Name;
	private String password;
	
	private final String main_Admin_name = "root";
	private final String main_Admin_User_Name = "MainAdmin";
	private final String main_Admin_Password = "MainAdmin123";
	
	public static Admin admin = null;
	
	Admin(String name, String user_Name,String password){
		this.name = name;
		this.user_Name = user_Name;
		this.password = password;
	}
	private Admin(){
	}
	
	public static Admin getAdminInstance(){
		if(admin == null){
			admin = new Admin();
		}
		return admin;
	}
	
	public String getRootName(){
	        return main_Admin_name ;
	}
	
	public String getRootUserName(){
	        return    main_Admin_User_Name;
	}
	
	public String getRootPassword(){
	         return    main_Admin_Password ;
	}
	
	public void setNewUser(String newName){
	          this.name = newName;
	}
	
	public void setNewUserName(String userName){
	         this.user_Name = userName;
	}
	
	public void setPassword(String newPassword){
	        this.password =  newPassword;
	}
	
	public String getName(){
	        return name;
	}
	
	public String getUserName(){
	       return   user_Name;
	}
	
	public String getPassword(){
	      return    password;
	 } 
    
}
