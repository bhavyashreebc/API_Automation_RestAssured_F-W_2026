package api.endpoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;
import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.response.Response;


// Create UserEndPoints.java -- contains CRUD methods implementation (Create, Retrieve, Update, Delete) 
//Created to perform, CRUD operations on User API

public class UserEndPoints {

	public static Response createUser(User payload){

		Response response=	given()
				.contentType("application/json")  //.contentType("ContentType.JSON") can also be used
				.accept("application/json")		//.accept("ContentType.JSON") can also be used
				.body(payload)

				.when()
				.post(Routes.post_url);

		return response;
	}


	public static Response readUser(String userName) {
		Response response=given()
		.pathParam("username", userName)

		.when()
		.get(Routes.get_url);
		
		return response;
	}


	public static Response updateUser(User payload, String userName){

		Response response=	given()
				.contentType("application/json")  //.contentType("ContentType.JSON") can also be used
				.accept("application/json")		//.accept("ContentType.JSON") can also be used
				.pathParam("username", userName)
				.body(payload)

				.when()
				.put(Routes.update_url);

		return response;
	}


	public static Response deleteUser(String userName) {
	Response response=	given()
		.pathParam("username", userName)

		.when()
		.delete(Routes.delete_url);
	
	return response;
	}
}

