import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ShareLaneTest {
    private static final String URLRegister = "https://www.sharelane.com/cgi-bin/register.py";

    @Test
    public static void zipCode3digits () {
        open(URLRegister);

        $(By.name("zip_code")).sendKeys("111");
        $(By.cssSelector("input[value= 'Continue']")).click();
        String result = $(By.className("error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. ZIP code should have 5 digits");
    }
    @Test
    public static void zipCode5digits () {
        open(URLRegister);

        $(By.name("zip_code")).sendKeys("11111");
        $(By.cssSelector("input[value= 'Continue']")).click();
        String result =  $(By.cssSelector("[value=Register]")).getValue();
        Assert.assertEquals(result, "Register");
    }


    @Test
    public void testRegisterPositive () {
        open(URLRegister);

        $(By.name("zip_code")).sendKeys("11111");
        $(By.cssSelector("input[value = 'Continue']")).click();
        $(By.name("first_name")).sendKeys("Nastia");
        $(By.name("last_name")).sendKeys("Dulepova");
        $(By.name("email")).sendKeys("nastia@gmail.com");
        $(By.name("password1")).sendKeys("11111");
        $(By.name("password2")).sendKeys("11111");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".confirmation_message")).getText();
        Assert.assertEquals(result, "Account is created!");
    }

    @Test
    public void testRegisterWithoutName() {
        open(URLRegister);
        
        $(By.name("zip_code")).sendKeys("11111");
        $(By.cssSelector("input[value = 'Continue']")).click();
        $(By.name("email")).sendKeys("nastia@gmail.com");
        $(By.name("password1")).sendKeys("11111");
        $(By.name("password2")).sendKeys("11111");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.className("error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. Some of your fields have invalid data or email was previously used");
    }
    @Test
    public void testRegisterDifferentPasswords () {
        open(URLRegister);

        $(By.name("zip_code")).sendKeys("11111");
        $(By.cssSelector("input[value = 'Continue']")).click();
        $(By.name("first_name")).sendKeys("Nastia");
        $(By.name("last_name")).sendKeys("Dulepova");
        $(By.name("email")).sendKeys("nastia@gmail.com");
        $(By.name("password1")).sendKeys("11111");
        $(By.name("password2")).sendKeys("111");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.className("error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. Some of your fields have invalid data or email was previously used");
    }



}
