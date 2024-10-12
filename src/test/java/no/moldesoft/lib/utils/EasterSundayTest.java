package no.moldesoft.lib.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Created by Erling Molde on 03.05.2017.
 */
public class EasterSundayTest {

    // @Parameters(name = "{index}: easterSunday({2}) is {0}-{1}-{2}")
    public static Stream<LocalDate> data() {
        return Stream.of(
                LocalDate.of(1800, Month.APRIL, 13),
                LocalDate.of(1885, Month.APRIL, 5),
                LocalDate.of(1900, Month.APRIL, 15),
                LocalDate.of(1957, Month.APRIL, 21),
                LocalDate.of(1976, Month.APRIL, 18),
                LocalDate.of(2000, Month.APRIL, 23),
                LocalDate.of(2001, Month.APRIL, 15),
                LocalDate.of(2017, Month.APRIL, 16),
                LocalDate.of(2018, Month.APRIL, 1),
                LocalDate.of(2019, Month.APRIL, 21),
                LocalDate.of(2020, Month.APRIL, 12),
                LocalDate.of(2021, Month.APRIL, 4),
                LocalDate.of(2022, Month.APRIL, 17),
                LocalDate.of(2023, Month.APRIL, 9),
                LocalDate.of(2024, Month.MARCH, 31)
        );
    }

    public static Stream<Arguments> data2() {
        return Stream.of(
                arguments(1800, Month.APRIL, 13),
                arguments(1885, Month.APRIL, 5),
                arguments(1900, Month.APRIL, 15),
                arguments(1957, Month.APRIL, 21),
                arguments(1976, Month.APRIL, 18),
                arguments(2000, Month.APRIL, 23),
                arguments(2001, Month.APRIL, 15),
                arguments(2017, Month.APRIL, 16),
                arguments(2018, Month.APRIL, 1),
                arguments(2019, Month.APRIL, 21),
                arguments(2020, Month.APRIL, 12),
                arguments(2021, Month.APRIL, 4),
                arguments(2022, Month.APRIL, 17),
                arguments(2023, Month.APRIL, 9),
                arguments(2024, Month.MARCH, 31)
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    public void easterSundayTest(LocalDate testDate) {
        LocalDate date = DateUtils.easterSunday(testDate.getYear());
        assertEquals(testDate.getYear(), date.getYear(), "year is wrong");
        assertEquals(testDate.getMonth(), date.getMonth(), "month is wrong");
        assertEquals(testDate.getDayOfMonth(), date.getDayOfMonth(), "dayOfMonth is wrong");
    }

    @ParameterizedTest
    @MethodSource("data2")
    public void easterSundayTest2(int year, Month month, int dayOfMonth) {
        LocalDate date = DateUtils.easterSunday(year);
        assertEquals(year, date.getYear(), "year is wrong");
        assertEquals(month, date.getMonth(), "month is wrong");
        assertEquals(dayOfMonth, date.getDayOfMonth(), "dayOfMonth is wrong");
    }

    @ParameterizedTest
    @MethodSource("data2")
    public void easterBeforeStartTest(int year, Month month, int dayOfMonth) {
        LocalInterval easter = DateUtils.easter(year);
        assertFalse(easter.contains(LocalDate.of(year, month, dayOfMonth).minusDays(7)));
    }

    @ParameterizedTest
    @MethodSource("data2")
    public void easterBeforeStartTestUsingLocalDateInterval(int year, Month month, int dayOfMonth) {
        LocalDateInterval easter = DateUtils.easterInterval(year);
        assertFalse(easter.contains(LocalDate.of(year, month, dayOfMonth).minusDays(7)));
    }

    @ParameterizedTest
    @MethodSource("data2")
    public void easterOnStartTest(int year, Month month, int dayOfMonth) {
        LocalInterval easter = DateUtils.easter(year);
        assertTrue(easter.contains(LocalDate.of(year, month, dayOfMonth).minusDays(6)));
    }

    @ParameterizedTest
    @MethodSource("data2")
    public void easterOnStartTestUsingLocalDateInterval(int year, Month month, int dayOfMonth) {
        LocalDateInterval easter = DateUtils.easterInterval(year);
        LocalDate localDate = LocalDate.of(year, month, dayOfMonth).minusDays(6);
        assertTrue(easter.contains(localDate), () -> "localDate: " + localDate + " is not in interval: " + easter);
    }

    @ParameterizedTest
    @MethodSource("data2")
    public void easterOnEndTest(int year, Month month, int dayOfMonth) {
        LocalInterval easter = DateUtils.easter(year);
        assertTrue(easter.contains(LocalDate.of(year, month, dayOfMonth).plusDays(1)));
    }

    @ParameterizedTest
    @MethodSource("data2")
    public void easterOnEndTestUsingLocalDateInterval(int year, Month month, int dayOfMonth) {
        LocalDateInterval easter = DateUtils.easterInterval(year);
        assertTrue(easter.contains(LocalDate.of(year, month, dayOfMonth).plusDays(1)));
    }

    @ParameterizedTest
    @MethodSource("data2")
    public void easterAfterEndTest(int year, Month month, int dayOfMonth) {
        LocalInterval easter = DateUtils.easter(year);
        assertFalse(easter.contains(LocalDate.of(year, month, dayOfMonth).plusDays(2)));
    }

    @ParameterizedTest
    @MethodSource("data2")
    public void easterAfterEndTestUsingLocalDateInterval(int year, Month month, int dayOfMonth) {
        LocalDateInterval easter = DateUtils.easterInterval(year);
        assertFalse(easter.contains(LocalDate.of(year, month, dayOfMonth).plusDays(2)));
    }
}