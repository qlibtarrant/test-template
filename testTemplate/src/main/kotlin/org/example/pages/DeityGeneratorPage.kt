package org.example.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class DeityGeneratorPage(val driver: WebDriver) {

    val title: By = By.cssSelector(".title > h2")

}