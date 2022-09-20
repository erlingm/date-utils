package no.moldesoft.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Erling Molde on 03.05.2017.
 */
@RunWith(Parameterized.class)
public class EasterSundayTest {

    private final int dayOfMonth;
    private final Month month;
    private final int year;
    private LocalInterval easter;

    @Parameters(name = "{index}: easterSunday({2}) is {0}-{1}-{2}")
    public static Object[][] data() {
        return new Object[][]{
                {13, Month.APRIL, 1800},
                {5, Month.APRIL, 1885},
                {15, Month.APRIL, 1900},
                {21, Month.APRIL, 1957},
                {18, Month.APRIL, 1976},
                {23, Month.APRIL, 2000},
                {15, Month.APRIL, 2001},
                {16, Month.APRIL, 2017},
                {1, Month.APRIL, 2018},
                {21, Month.APRIL, 2019},
                {12, Month.APRIL, 2020},
                {4, Month.APRIL, 2021},
                {17, Month.APRIL, 2022},
                {9, Month.APRIL, 2023},
                {31, Month.MARCH, 2024},
        };
    }

    public EasterSundayTest(int dayOfMonth, Month month, int year) {
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
    }

    @Test
    public void easterSundayTest() {
        LocalDate date = DateUtils.easterSunday(year);
        assertThat("year is wrong", date.getYear(), equalTo(year));
        assertThat("month is wrong", date.getMonth(), equalTo(month));
        assertThat("dayOfMonth is wrong", date.getDayOfMonth(), equalTo(dayOfMonth));
    }

    @Test
    public void easterBeforeStartTest() {
        easter = DateUtils.easter(year);
        assertFalse(easter.contains(LocalDate.of(year, month, dayOfMonth).minusDays(7)));
    }

    @Test
    public void easterOnStartTest() {
        easter = DateUtils.easter(year);
        assertTrue(easter.contains(LocalDate.of(year, month, dayOfMonth).minusDays(6)));
    }

    @Test
    public void easterOnEndTest() {
        easter = DateUtils.easter(year);
        assertTrue(easter.contains(LocalDate.of(year, month, dayOfMonth).plusDays(1)));
    }

    @Test
    public void easterAfterEndTest() {
        easter = DateUtils.easter(year);
        assertFalse(easter.contains(LocalDate.of(year, month, dayOfMonth).plusDays(2)));
    }
}