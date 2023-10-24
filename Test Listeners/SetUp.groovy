import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory

import internal.GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext

class SetUp {
	/**
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 */
	@BeforeTestCase
	def sampleBeforeTestCase(TestCaseContext testCaseContext) {

		def driver = null

		try {
			driver = MobileDriverFactory.getDriver()
		}catch(Exception e) {
		}

		if(driver == null) {
			Mobile.startApplication('C:\\Users\\Pruebas\\Katalon Studio\\POC_KATALON_ANDROID_LACOMER\\Apks\\LaComer_14.9.1.apk', true)
		}
	}

	/**
	 * Executes after every test case ends.
	 * @param testCaseContext related information of the executed test case.
	 */
	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		
		Mobile.takeScreenshot()
		
		def flagResetTc = Mobile.waitForElementPresent(findTestObject('Object Repository/CARRITO/android.widget.ImageView - Carrito'),
				1, FailureHandling.OPTIONAL)
		
		if(flagResetTc == false) {
			Mobile.pressBack()
			
			if(Mobile.verifyElementVisible(findTestObject('Object Repository/GLOBALES/Alertas/android.widget.Button - No'), 3, FailureHandling.OPTIONAL)) {
				Mobile.tap(findTestObject('Object Repository/GLOBALES/Alertas/android.widget.Button - No'), 0)
			}
		}
		
		Mobile.tap(findTestObject('Object Repository/GLOBALES/Menu/android.widget.TextView - Departamentos'), 0)
	}

	/**
	 * Executes before every test suite starts.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@BeforeTestSuite
	def sampleBeforeTestSuite(TestSuiteContext testSuiteContext) {
		println testSuiteContext.getTestSuiteId()
	}

	/**
	 * Executes after every test suite ends.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {
		println testSuiteContext.getTestSuiteId()
	}
}