package lib.ui;

import io.appium.java_client.AppiumDriver;

import static org.openqa.selenium.By.xpath;

public class MyListsPageObject extends MainPageObject {
    private static final String
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']";

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getFolderXpathByName(String folderName) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folderName);
    }

    public void openFolderByName(String folderName) {
        String folderNameXpath = getFolderXpathByName(folderName);
        this.waitForElementAndClick(
                xpath(folderNameXpath),
                "Could not find folder by name" + folderName,
                5
        );
    }
}
