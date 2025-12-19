package Utilities;

import org.testng.annotations.DataProvider;

public class TestData {

	@DataProvider(name = "InvalidCredentialsData")
	public String[][] InvalidData() {
	 return new String[][] {
	   { "standard_user","9876abc" },
	   { "hello2345","secret_sauce"},
	   {"teytwudg","9876abc"}
	 };
	}
}
