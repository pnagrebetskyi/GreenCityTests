package com.softserve.edu.greencity.ui.tests.runner;

import com.softserve.edu.greencity.ui.tools.grid.GridHub;
import com.softserve.edu.greencity.ui.tools.grid.RegisterChrome;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverSetup extends GreenCityTestRunner {

    void optionsArguments() {
        if (remote) {
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--allow-failed-policy-fetch-for-test");
            options.addArguments("--disable-browser-side-navigation");
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--window-size=1920,1080", "--no-sandbox", "'--disable-dev-shm-usage");
            // options.addArguments("--headless");}
        }
        else {
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--allow-failed-policy-fetch-for-test");
            options.addArguments("--disable-browser-side-navigation");
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--window-size=1920,1080", "--no-sandbox", "'--disable-dev-shm-usage");
            GridHub.startLocally(4444);
            RegisterChrome.startNode(5551);
            RegisterChrome.startNode(5552);
            RegisterChrome.startNode(5553);
            RegisterChrome.startNode(5554);
        }
    }

}
