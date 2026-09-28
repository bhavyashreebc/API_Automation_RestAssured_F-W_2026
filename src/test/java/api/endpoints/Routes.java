package api.endpoints;

/*
Swagger URI --> https://petstore.swagger.io

Create user(Post) : https://petstore.swagger.io/v2/user
Get user (Get): https://petstore.swagger.io/v2/user/{username}
Update user (Put) : https://petstore.swagger.io/v2/user/{username}
Delete user (Delete) : https://petstore.swagger.io/v2/user/{username}

*/
public class Routes {

	public static String base_url="https://petstore.swagger.io/v2";
	
	//user module
	
	public static String post_url = base_url+"/user";
	public static String get_url = base_url+"/user/{username}"; //user name is added as pathParam b'cas, only after creating user from post_url we will capture the user name, that we should send to remaining url s
	public static String update_url = base_url+"/user/{username}";
	public static String delete_url = base_url+"/user/{username}";
	
	
	//store module
	
		//here we need to add store module URls
	
	//pet module
	
		//here we need to add pet module URls
		
		
	
}
