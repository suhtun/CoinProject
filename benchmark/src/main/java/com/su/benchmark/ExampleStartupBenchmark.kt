package com.su.benchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = "com.su.coinproject",
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
    }

    @Test
    fun navigateToSearchBox() = benchmarkRule.measureRepeated(
        packageName = "com.su.coinproject",
        metrics = listOf(StartupTimingMetric()),
        iterations = 1,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()

        navigateToSearchBoxAndSearchedResult()
    }

    @Test
    fun scrollLastAndShowCoinDetail() = benchmarkRule.measureRepeated(
        packageName = "com.su.coinproject",
        metrics = listOf(StartupTimingMetric()),
        iterations = 1,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()

        waitCoinLoadedAndScrollDown()
    }
}

fun MacrobenchmarkScope.navigateToSearchBoxAndSearchedResult() {
    val searchBox = device.findObject(By.text("Search"))
    searchBox.click()

    device.wait(Until.hasObject(By.text("Buy, sell and hold crypto")), 5000)

    val searchBar = device.findObject(By.res("searchbar"))
    searchBar.click()

    searchBar.text = "bit"

    device.pressSearch()

    device.waitForIdle()

    device.wait(Until.hasObject(By.text("Bit")), 5000)
}

fun MacrobenchmarkScope.waitCoinLoadedAndScrollDown() {
    device.wait(Until.hasObject(By.text("Buy, sell and hold crypto")), 5000)
    val list = device.findObject(By.res("coin_list"))

    list.setGestureMargin(device.displayWidth / 5)
    list.fling(Direction.DOWN)

    device.findObject(By.text("Bitcoin Cash")).click()

    device.waitForIdle()

    device.wait(Until.hasObject(By.text("Bitcoin Cash")), 5000)

//    val button = device.findObject(By.text("GO TO WEBSITE"))
//    button.click()
//
//    device.wait(Until.hasObject(By.text("BitcoinCash")), 5000)
}