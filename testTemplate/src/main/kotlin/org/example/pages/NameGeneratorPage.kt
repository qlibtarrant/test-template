package org.example.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.Select

class NameGeneratorPage(val driver: WebDriver) {

    val title: By = By.cssSelector(".title > h2")
    val genderDropdown: By = By.cssSelector(".gender-select")
    val raceDropdown: By = By.cssSelector(".race-select")
    val generateButton: By = By.cssSelector(".button-generate")
    private val nameTextFieldValues: By = By.cssSelector(".name > span")

    fun getGeneratedNames(): List<String> {
        return driver.findElements(nameTextFieldValues).map { element -> element.text }
    }

    private fun selectByValue(value: String, by: By) {
        val element = driver.findElement(by)
        val dropdown = Select(element)
        dropdown.selectByValue(value)
    }

    fun selectGender(value: String) {
        selectByValue(value, genderDropdown)
    }

    fun selectRace(value: String) {
        selectByValue(value, raceDropdown)
    }

    fun clickGenerate() {
        driver!!.findElement(generateButton).click()
    }
}