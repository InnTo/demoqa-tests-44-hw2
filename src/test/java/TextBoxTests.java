import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {

    @BeforeAll
    static void beforeAll(){  //аннотация BeforeAll должна идти с методом static
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillFormTest() {
        open("/text-box");
        $("#userName").setValue("Test");
        $("#userEmail").setValue("Test@test.ru");
        $("#currentAddress").setValue("Test address");
        $("#permanentAddress").setValue("Another test address");
        $("#submit").click();

        $("#output #name").shouldHave(text("Test"));
        $("#output #email").shouldHave(text("Test@test.ru"));
        $("#output #currentAddress").shouldHave(text("Test address"));
        $("#output #permanentAddress").shouldHave(text("Another test address"));
    }

    @Test
    void fillInvalidEmailFormTest() {
        open("/text-box");
        $("#userName").setValue("Test");
        $("#userEmail").setValue("Test");
        $("#currentAddress").setValue("Test address");
        $("#permanentAddress").setValue("Another test address");
        $("#submit").click();


        $("#userEmail").shouldHave(cssValue("border","0.8px solid rgb(255, 0, 0)"));
    }
}
