package api.test;

import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import api.utilities.SpecFactory;
import io.restassured.response.Response;

public class UserDDTest {

	
	
	@Test (priority=1,dataProvider="Data",dataProviderClass=DataProviders.class) //here data provider class name is used as the data provider is in different class
	public void postUser(String UserId, String UserName, String FirstName, String LastName, String email, String pwd, String ph ) {
		
		User userPayload=new User();
		
		userPayload.setFirstName(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setEmail(email);
		userPayload.setId(Integer.parseInt(UserId));
		userPayload.setUsername(UserName);
		userPayload.setPhone(ph);
		userPayload.setPassword(pwd);
		
		Response response=UserEndPoints.createUser(userPayload);
		response.then().spec(SpecFactory.getSuccessResponseSpec()).log().body();
		
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void deleteUser(String userName) {
		
		Response res=UserEndPoints.deleteUser(userName);
		res.then().spec(SpecFactory.getSuccessResponseSpec());
		
	}
	
	//getUser
	
	//updateUser
	
}




















