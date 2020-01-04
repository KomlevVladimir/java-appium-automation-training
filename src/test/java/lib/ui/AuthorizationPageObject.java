package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
        LOGIN_BUTTON = "xpath://body/div/a[text()='Log in']",
        LOGIN_INPUT = "css:input[name=wpName]",
        PASSWORD_INPUT = "css:input[name=wpPassword]",
        SUBMIT_BUTTON = "css:button[name=wploginattempt]";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton() {
        this.waitForElementPresent(LOGIN_BUTTON, "Could not find auth button", 15);
        this.waitForElementAndClick(LOGIN_BUTTON, "Could not find auth button", 15);
    }

    public void enterLoginData(String login, String password) {
        this.waitElementAndSendKeys(LOGIN_INPUT, login, "Could not find and put a login to the login input", 15);
        this.waitElementAndSendKeys(PASSWORD_INPUT, password, "Could not find and put a password to the password input", 15);
    }

    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Could not find and click  the submit button", 15);
    }
}
