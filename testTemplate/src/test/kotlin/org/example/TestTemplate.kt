package org.example

import org.example.pages.DeityGeneratorPage
import org.example.pages.NameGeneratorPage
import org.junit.Assert
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test

class TestTemplate {

    private var driver: WebDriver? = null
    private var nameGeneratorPage: NameGeneratorPage? = null
    private var deityGeneratorPage: DeityGeneratorPage? = null

    @BeforeTest
    fun setupDriverAndPages() {
        System.setProperty("webdriver.chrome.driver", "src/main/kotlin/org/example/drivers/chromedriver")
        driver = ChromeDriver()

        nameGeneratorPage = NameGeneratorPage(driver!!)
        deityGeneratorPage = DeityGeneratorPage(driver!!)
    }

    @Test
    fun generateNameDisplaysText() {
        val expectedNumberOfNames = 10

        // Go to the website and verify the title
        driver!!.get("https://toolsandtaverns.com/nameGenerator")
        val titleText = driver!!.findElement(nameGeneratorPage!!.title).text
        Assert.assertEquals("Title text did not match.", "Name Generator", titleText)

        // Select values from dropdowns and generate names
        nameGeneratorPage!!.selectGender("Female")
        nameGeneratorPage!!.selectRace("Elf")
        nameGeneratorPage!!.clickGenerate()

        Thread.sleep(6000)

        // Save generated names in a list, check how many names, and generate another list of names
        val firstListOfNames = nameGeneratorPage!!.getGeneratedNames()
        Assert.assertEquals("Incorrect number of names first time.", firstListOfNames.size, expectedNumberOfNames)
        nameGeneratorPage!!.clickGenerate()

        Thread.sleep(6000)

        // Verify that the list of names from the first and second generation do not have any matching names
        val secondListOfNames = nameGeneratorPage!!.getGeneratedNames()
        Assert.assertEquals("Incorrect number of names second time.", secondListOfNames.size, expectedNumberOfNames)
        Assert.assertFalse(
            "There was a matching name. \nFirstList=${firstListOfNames}\nSecondList=${secondListOfNames}",
            firstListOfNames.any { name -> secondListOfNames.contains(name) })
    }

    @Test
    fun visitDeityPage() {


        driver!!.get("https://toolsandtaverns.com/deity-generator")
        val titleText = driver!!.findElement(deityGeneratorPage!!.title).text
        Assert.assertEquals("Title text did not match.", "Deity Generator", titleText)
    }



    @AfterTest
    fun tearDown() {
        driver!!.quit()
    }

}