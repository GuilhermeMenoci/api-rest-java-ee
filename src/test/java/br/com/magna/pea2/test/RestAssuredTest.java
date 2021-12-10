//package br.com.magna.pea2.test;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//public class RestAssuredTest {
//	String url = "https://reqres.in/api/users";
//
//	@Test
//	public void getPageOne() {
//		given().param("page", "1").when().get(url).then().statusCode(200).body("page", equalTo(1));
//		Assert.assertNotNull(url);
//	}
//
//	@Test
//	public void getUser() {
//		get(url + "/2").then().body("data.id", equalTo(2));
//	}
//}
