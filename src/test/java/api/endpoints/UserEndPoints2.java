package api.endpoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.response.Response;


// Create UserEndPoints.java -- contains CRUD methods implementation (Create, Retrieve, Update, Delete) 
//Created to perform, CRUD operations on User API

public class UserEndPoints2 {
//getting urls from the properties file
	
	public static ResourceBundle gteURL(){
	ResourceBundle routes= ResourceBundle.getBundle("routes"); //this line looks for the routes.properties file under resources only, so entire path is not needed
	return routes;
	
	}
	
	public static Response createUser(User payload){

		String post_url=gteURL().getString("post_url"); //getting post url from routes.properties
		Response response=	given()
				.contentType("application/json")  //.contentType("ContentType.JSON") can also be used
				.accept("application/json")		//.accept("ContentType.JSON") can also be used
				.body(payload)

				.when()
				.post(post_url);

		return response;
	}


	public static Response readUser(String userName) {
		String get_url=gteURL().getString("get_url"); //getting url from routes.properties
		
		Response response=given()
		.pathParam("username", userName)

		.when()
		.get(get_url);
		
		return response;
	}


	public static Response updateUser(User payload, String userName){
		String update_url=gteURL().getString("update_url");
		Response response=	given()
				.contentType("application/json")  //.contentType("ContentType.JSON") can also be used
				.accept("application/json")		//.accept("ContentType.JSON") can also be used
				.pathParam("username", userName)
				.body(payload)

				.when()
				.put(update_url);

		return response;
	}


	public static Response deleteUser(String userName) {
		String delete_url=gteURL().getString("delete_url");
		
	Response response=	given()
		.pathParam("username", userName)

		.when()
		.delete(delete_url);
	
	return response;
	}
}

