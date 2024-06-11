package ECommerce.ECommerce.Resources;

public class Login {
	
	private String userEmail;
	private String userPassword;
	
	public void setUserPassword(String userPassword) {
		this.userPassword=userPassword;
	}
	
	public String getUserPassword() {
		return userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	

}
