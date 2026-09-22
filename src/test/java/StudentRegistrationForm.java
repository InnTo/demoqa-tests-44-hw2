import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationForm {

    @BeforeAll
    static void setUp(){  //аннотация BeforeAll должна идти с методом static
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillFullRegistrationFormTest() {
        open("/automation-practice-form");


        //Заполнение формы
        $("#firstName").setValue("Test");
        $("#lastName").setValue("TestLastName");
        $("#userEmail").setValue("Test@test.ru");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("8961530834");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__year-select").selectOption("2002");
        $$(".react-datepicker__day").findBy(text("30")).click();

        $("#subjectsInput").setValue("Maths").pressEnter();

        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("test.jpg");
        $("#currentAddress").setValue("Current test address");

        $("#state").scrollTo().click();
        $(byText("Uttar Pradesh")).click();
        $("#city").click();
        $(byText("Agra")).click();

        $("#submit").click();

        //Проверка заполнения
        $(".table-responsive").shouldHave(text("Test TestLastName"));
        $(".table-responsive").shouldHave(text("Test@test.ru"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("8961530834"));
        $(".table-responsive").shouldHave(text("30 April,2002"));
        $(".table-responsive").shouldHave(text("Maths"));
        $(".table-responsive").shouldHave(text("Sports"));
        $(".table-responsive").shouldHave(text("test.jpg"));
        $(".table-responsive").shouldHave(text("Current test address"));
        $(".table-responsive").shouldHave(text("Uttar Pradesh Agra"));
    }

    @Test
    void fillOnlyNecessaryFieldsRegistrationFormTest() {
        open("/automation-practice-form");

        //Заполнение формы
        $("#firstName").setValue("Test");
        $("#lastName").setValue("TestLastName");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("8961530834");
        $("#submit").scrollTo().click();

        //Проверка заполнения
        $(".table-responsive").shouldHave(text("Test TestLastName"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("8961530834"));
    }

    @Test
    void fillWithoutFirstNameFieldRegistrationFormTest() {
        open("/automation-practice-form");

        //Заполнение формы
        $("#lastName").setValue("TestLastName");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("8961530834");
        $("#submit").scrollTo().click();

        //Проверка ошибки
        $("#firstName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void fillWithoutLastNameFieldRegistrationFormTest() {
        open("/automation-practice-form");

        //Заполнение формы
        $("#firstName").setValue("TestFirstName");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("8961530834");
        $("#submit").scrollTo().click();

        //Проверка ошибки
        $("#lastName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void fillInvalidEmailRegistrationFormTest() {
        open("/automation-practice-form");

        //Заполнение формы
        $("#firstName").setValue("TestFirstName");
        $("#lastName").setValue("TestLastName");
        $("#userEmail").setValue("Test");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("8961530834");
        $("#submit").scrollTo().click();

        //Проверка ошибки
        $("#userEmail").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }
}
