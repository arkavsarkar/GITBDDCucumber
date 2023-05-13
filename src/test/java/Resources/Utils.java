package Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification RequestSpec;
	//for logging request purpose
	
public RequestSpecification requestSpecification() throws IOException {
	if(RequestSpec==null) {
	PrintStream log= new PrintStream(new FileOutputStream("logging.txt"));
	RequestSpec = new RequestSpecBuilder().setBaseUri(getGlobalData("BaseUri"))
			.addFilter(RequestLoggingFilter.logRequestTo(log))//logging request and response to log file
			.addFilter(ResponseLoggingFilter.logResponseTo(log))
			.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
	return RequestSpec;
	}
	return RequestSpec;
	}
public static String getGlobalData(String key) throws IOException {
	Properties prop =new Properties();
	FileInputStream fs =new FileInputStream("C:\\Selenium\\PROG2\\BDDFinalBM\\src\\test\\java\\Resources\\gloabalData.properties");
	prop.load(fs);
	return prop.getProperty(key);
	}
public String getJsonPath(Response reqResponse,String key) {
	String response= reqResponse.asString();
	JsonPath js = new JsonPath(response);
	return js.get(key).toString();
}
}
