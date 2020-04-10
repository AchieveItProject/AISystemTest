package com.achieveit.systemtest;

import com.achieveit.systemtest.entity.ProjectInfo;
import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

import java.util.Locale;

public class ProjectInfoData {
    @DataProvider(parallel = false)
    public static Object[][] projectInfo() {
        int itemCount = 1;
        Faker faker = new Faker(Locale.ENGLISH);
        faker.app().name();
        Object[][] ret = new Object[itemCount][1];
        for (int i = 0; i < itemCount; i++) {
            String customerInfo = faker.company().name();
            String field = faker.company().profession();
            ProjectInfo projectInfo = new ProjectInfo(faker.app().name(), "c", customerInfo,
                    faker.gameOfThrones().house(), faker.matz().quote().substring(0,20), faker.gameOfThrones().house(),
                    field, "2020-07-01 12:00:00","2020-08-01 12:00:00");
            ret[i][0] = projectInfo;
        }
        return ret;
    }
}
