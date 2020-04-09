package com.achieveit.systemtest;

import com.achieveit.systemtest.entity.ProjectInfo;
import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ProjectRejectTestData {
    @DataProvider(name = "projectInfo", parallel = false)
    public static Object[][] parallel2Test() {
        int itemCount = 1;
        Faker faker = new Faker(Locale.ENGLISH);
        faker.app().name();
        Object[][] ret = new Object[itemCount][1];
        for (int i = 0; i < itemCount; i++) {
            String customerInfo = faker.company().name();
            String field = faker.company().profession();
            Date schedule = faker.date().future(100, TimeUnit.DAYS);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
            String scheduleDate = sdf.format(schedule);
            String scheduleTime = time.format(schedule);
            Date delivery = faker.date().future(daysBetween(Calendar.getInstance().getTime(), schedule), TimeUnit.DAYS);
            String deliveryDate = sdf.format(delivery);
            String deliveryTime = time.format(delivery);
            ProjectInfo projectInfo = new ProjectInfo(faker.app().name(), "c", customerInfo,
                    faker.gameOfThrones().house(), faker.matz().quote(), faker.gameOfThrones().house(),
                    field, scheduleDate+" "+ scheduleTime, deliveryDate+" "+deliveryTime);
            ret[i][0] = projectInfo;
        }
        return ret;
    }
    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
