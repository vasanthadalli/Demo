package ECommerce.ECommerce;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ECommerce.ECommerce.Resources.CreateOrder;
import ECommerce.ECommerce.Resources.Login;
import ECommerce.ECommerce.Resources.LoginResponse;
import ECommerce.ECommerce.Resources.OrderCreated;
import ECommerce.ECommerce.Resources.Orders;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    
    @org.testng.annotations.Test
    public void shouldAnswerWithTrue()
    {
    	
    	//Login
    	
    	
    	RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/api/ecom")
    			.setContentType(ContentType.JSON).build() ;
    	
    	ResponseSpecification resSpec= new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
    	
    	Login l1= new Login();
    	l1.setUserEmail("dvasantha2010@gmail.com");
    	l1.setUserPassword("India@22");
    	
    	LoginResponse res = given().spec(req).body(l1)
    	.when().post("auth/login").then().log().all().statusCode(200).extract().as(LoginResponse.class);
    	
    	String token =res.getToken();
    	String userId =res.getUserId();
    	
    	System.out.println("User has logged in");
    	
    	
    	//createProduct
    	
    	RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/api/ecom")
    			.addHeader("Authorization",token).build();
    			
    	
    	
    	
    	String response = given().log().all().spec(reqSpec)
    			.formParams("productName","qwerty")
    			.formParams("productAddedBy",userId)
    			.formParams("productCategory","fashion")
    			.formParams("productSubCategory","shirts")
    			.formParams("productPrice","10000")
    			.formParams("productDescription","adidas")
    			.formParams("productFor","women")
    			.multiPart("productImage",new File("C:\\Users\\ramak\\Postman\\files\\133567165689155504.jpg"))    			  			
    			.when().post("/product/add-product").then().log().all().statusCode(201).extract().response().asString();
    	JsonPath js= new JsonPath(response);
    	String ProductId = js.get("productId");
    	
    	System.out.println("Product has been added with productId "+ ProductId );
    	
    	//CreateOrder
    	
    	Orders or1= new Orders();
    	
    	or1.setCountry("India");
    	or1.setProductOrderedId(ProductId);
    	
    	List<Orders> orList= new ArrayList<Orders>();
    	orList.add(or1);
    	CreateOrder cr1= new CreateOrder();
    	cr1.setOrders(orList);
    	
    	
    	OrderCreated o1= given().log().all().spec(reqSpec).header("Content-Type", "application/json")
    			.body(cr1)
    	.when().post("order/create-order")
    	.then().log().all().statusCode(201).extract().as(OrderCreated.class);
    	String orderId = o1.getOrders().get(0);
    	
    	System.out.println("Order has been created with orderid "+ orderId);
    	
    	
    	
    	
    	//Retrieve Order
    	
    	
    	given().log().all().spec(reqSpec).queryParam("id",orderId)
    	.when().get("/order/get-orders-details")
    	.then().statusCode(200);
    	
    	System.out.println("Order has been retrieved");
    	
    	//Delete Product
    	
    	String re= given().relaxedHTTPSValidation().spec(reqSpec).pathParam("productId", ProductId)
    	.when().delete("/product/delete-product/{productId}")
    	.then().statusCode(200).extract().response().asString();
    	
    	JsonPath js1= new JsonPath(re);
    	String message = js.get("message");
    	
    	System.out.println("Product has been deleted");
    	
        
    }
}
