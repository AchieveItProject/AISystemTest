package com.achieveit.systemtest;

import org.testng.annotations.DataProvider;

public class ProjectSearchTestData {
    @DataProvider
    public static Object[][] idInList() {
     return new Object[][]{
             {"51"},{"1"}
     };
    }
    @DataProvider
    public static Object[][] nameInList(){
        return new Object[][]{
                {"222"},{"1"}
        };
    }
    @DataProvider
    public static Object[][] leaderNameInList(){
        return new Object[][]{
                {"string"},{"c"}
        };
    }
    @DataProvider
    public static Object[][] idAndLeaderNameInList(){
        return new Object[][]{
                {"11","string"}
        };
    }
    @DataProvider
    public static Object[][] nameAndLeaderNameInList(){
        return new Object[][]{
                {"项目","string"},{"项目100","string"}
        };
    }
    @DataProvider
    public static Object[][]   idAndNameInList(){
        return new Object[][]{
                {"11","项目100"}
        };
    }
}
