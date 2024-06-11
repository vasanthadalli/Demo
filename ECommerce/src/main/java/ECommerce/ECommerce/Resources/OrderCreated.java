package ECommerce.ECommerce.Resources;

import java.util.List;

public class OrderCreated {

	private List<String> orders;
	public List<String> getOrders() {
		return orders;
	}
	public void setOrders(List<String> orders) {
		this.orders = orders;
	}
	public List<String> getProductOrderId() {
		return productOrderId;
	}
	public void setProductOrderId(List<String> productOrderId) {
		this.productOrderId = productOrderId;
	}
	private  List<String> productOrderId;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private String message;
	
	
}
