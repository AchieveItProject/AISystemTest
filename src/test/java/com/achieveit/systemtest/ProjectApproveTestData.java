package com.achieveit.systemtest;

import com.achieveit.systemtest.entity.ProjectInfo;
import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ProjectApproveTestData {


    // Not same as "9" since checkFunction will fail (same with old Value)
    @DataProvider
    public static Object[][] cancelEPG(){
        return new Object[][]{{"10"},{"7"}};
    }
    @DataProvider
    public static Object[][] applyEPG(){
        return new Object[][]{{"9"}};
    }
    // Not same as "9" since checkFunction will fail (system api fail)
    @DataProvider
    public static Object[][] modifyEPG(){
        return new Object[][]{{"8"},{"7"}};
    }

    // Not same as "5" since checkFunction will fail (same with old Value)
    @DataProvider
    public static Object[][] cancelQA(){
        return new Object[][]{{"10"},{"7"}};
    }
    @DataProvider
    public static Object[][] applyQA(){
        return new Object[][]{{"5"}};
    }
    // Not same as "5" since checkFunction will fail (system api fail)
    @DataProvider
    public static Object[][] modifyQA(){
        return new Object[][]{{"1"},{"2"}};
    }

    // Not same as "8" since checkFunction will fail (same with old Value)
    @DataProvider
    public static Object[][] cancelCM(){
        return new Object[][]{{"2"},{"5"}};
    }
    @DataProvider
    public static Object[][] applyCM(){
        return new Object[][]{{"8"}};
    }
    // Not same as "8" since checkFunction will fail (system api fail)
    @DataProvider
    public static Object[][] modifyCM(){
        return new Object[][]{{"2"},{"4"}};
    }
}
