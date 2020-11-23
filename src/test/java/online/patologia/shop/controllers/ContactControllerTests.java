package online.patologia.shop.controllers;

import org.testng.annotations.*;

public class ContactControllerTests {

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][] { { "data one" }, { "data two" } };

    }

    @BeforeClass
    public void init() {
        System.out.println("Before init class, one time");
    }

    @Test(dataProvider = "data-provider")
    public void testMethod(String data) {
        System.out.println("data is: " + data);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testMethod2() {
        throw new RuntimeException();
    }

    @AfterClass
    public void cleanUp() {
        System.out.println("After class, one time");
    }


}
