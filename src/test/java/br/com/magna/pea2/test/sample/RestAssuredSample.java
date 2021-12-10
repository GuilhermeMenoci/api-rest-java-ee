//package br.com.magna.pea2.test.sample;
//
//import static org.hamcrest.CoreMatchers.containsString;
//import static org.hamcrest.CoreMatchers.equalTo;
//
//import org.junit.Test;
//
//public class RestAssuredSample {
//	private String url = "https://reqres.in/api/users";
//
//	@Test
//	public void getPageOneTest() {
//		given().param("page", "1").when().get(url).then().statusCode(200).body("page", equalTo(1));
//	}
//
//	@Test
//	public void getUserTest() {
//		get(url + "/2").then().body("data.id", equalTo(2));
//	}
//
//	@Test
//	public void postUserTest() {
//		JSONObject requestParams = new JSONObject();
//		requestParams.put("nomeAutor", "Marcelo Da Costa");
//		requestParams.put("nacionalidade", "França");
//		requestParams.put("cpf", "11111111122");
//		given().body(requestParams.toJSONString()).when().post(url).then().statusCode(201)
//				.body(containsString("createdAt"));
//	}
//
//	@Test
//	public void putUserTest() {
//		JSONObject requestParams = new JSONObject();
//		requestParams.put("nomeAutor", "Marcelo Da Costa Oliveira");
//		requestParams.put("nacionalidade", "FrançaAAAAA");
//		requestParams.put("cpf", "11111111122-33");
//
//		given().body(requestParams.toJSONString()).when().put(url + "/2").then().statusCode(200)
//				.body(containsString("updatedAt"));
//	}
//
//	@Test
//	public void deleteUserTest() {
//		when().delete(url + "/2").then().statusCode(204);
//	}
//}
