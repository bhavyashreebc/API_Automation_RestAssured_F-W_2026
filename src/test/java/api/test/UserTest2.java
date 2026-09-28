package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import api.utilities.SpecFactory;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

public class UserTest2{

	Faker faker;
	User userPayload;
	ResponseSpecification resSpec;
	
	public Logger logger; // to create logs

	@BeforeClass
	public void setup() {

		faker=new Faker();
		userPayload=new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		System.out.println("user name is: "+userPayload.getUsername());
		
		//logs
		
		logger=LogManager.getLogger(this.getClass());
	}

	@Test (priority=1)
	public void testPostUser() {
		logger.info("*****************Creating User********************");
		
		Response response = UserEndPoints2.createUser(userPayload);
		response.then().log().body();
		response.then().spec(SpecFactory.getSuccessResponseSpec()); //Applies status code 200, JSON content type checks automatically
		//below assertions is not required as its covered in the ResponseSpecification itself
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*****************User is created********************");
		logger.debug("Response body is: {}",response.getBody().asString());

	}

	@Test(priority=2)
	public void testGetUser() {
		
		logger.info("*****************Reading User info********************");
		Response response= UserEndPoints2.readUser(userPayload.getUsername());
		System.out.println("Got the USN as: "+userPayload.getUsername());

		// calling response specification
		response.then().spec(SpecFactory.getSuccessResponseSpec()); //Applies status code 200, JSON content type checks automatically
		response.then().log().body();
		
		logger.info("*****************User info displayed********************");
	}
	
	@Test(priority=3)
	public void updateUser() {
		logger.info("*****************Updating User********************");
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		System.out.println(userPayload.getUsername());
		Response response = UserEndPoints2.updateUser(userPayload,userPayload.getUsername());
		response.then().spec(SpecFactory.getSuccessResponseSpec());
		response.then().log().body();
		
		Response responseAterUpdate= UserEndPoints2.readUser(userPayload.getUsername());
	
//To check if update has happened user details
		System.out.println("******Response after update********");
		logger.info("******Response after update********");
		
		responseAterUpdate.then().log().body();
		responseAterUpdate.then().spec(SpecFactory.getSuccessResponseSpec());
		
		logger.info("*****************User is updated********************");
	}

	@Test(priority=4)
	public void deleteUser() {
		logger.info("*****************Deleting User********************");
		
		Response response=UserEndPoints2.deleteUser(userPayload.getUsername());
		response.then().spec(SpecFactory.getSuccessResponseSpec()).log().body();
		
		logger.info("*****************Deleted User********************");
	}
}
