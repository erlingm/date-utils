package no.moldesoft.lib.utils;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by Erling Molde on 07.09.2016.
 */
public class DateUtils {

    public static final String EUROPE_OSLO = "Europe/Oslo";
    public static final ZoneId EUROPE_OSLO_ZONE_ID = ZoneId.of(EUROPE_OSLO);

    public static LocalInterval easter(int year) {
        LocalDate easterSunday = easterSunday(year);
        LocalDate mondayOfEasterWeek = easterSunday.minusDays(6);
        LocalDate tuesdayAfterEasterWeek = easterSunday.plusDays(2);
        return LocalInterval.of(mondayOfEasterWeek, tuesdayAfterEasterWeek);
    }

    /**
     * Computes the interval for Easter, starting from the Monday of Easter week to the Tuesday after Easter Sunday.
     *
     * @param year the year for which to calculate the Easter interval.
     * @return the interval from the Monday of Easter week to the Tuesday after Easter Sunday.
     */
    public static LocalDateInterval easterInterval(int year) {
        LocalDate easterSunday = easterSunday(year);
        LocalDate mondayOfEasterWeek = easterSunday.minusDays(6);
        LocalDate tuesdayAfterEasterWeek = easterSunday.plusDays(2);
        return LocalDateInterval.of(mondayOfEasterWeek, tuesdayAfterEasterWeek);
    }

    public static LocalDate easterSunday(int year) {
        /* Computus (ref. http://en.wikipedia.org/wiki/Computus) */

        /* Anonymous Gregorian algorithm
         *
         * "A New York correspondent" submitted this algorithm for determining the Gregorian Easter
         * to the journal Nature in 1876. It has been reprinted many times, in 1877 by Samuel
         * Butcher in The Ecclesiastical Calendar, in 1922 by H. Spencer Jones in General Astronomy,
         * in 1977 by the Journal of the British Astronomical Association, in 1977 by The Old
         * Farmer's Almanac, in 1988 by Peter Duffett-Smith in Practical Astronomy with your
         * Calculator, and in 1991 by Jean Meeus in Astronomical Algorithms. */

        final int a = year % 19;
        final int b = year / 100;
        final int c = year % 100;
        final int d = b / 4;
        final int e = b % 4;
        final int f = (b + 8) / 25;
        final int g = (b - f + 1) / 3;
        final int h = (19 * a + b - d - g + 15) % 30;
        final int i = c / 4;
        final int k = c % 4;
        final int l = (32 + 2 * e + 2 * i - h - k) % 7;
        final int m = (1 + 11 * h + 22 * l) / 451;
        final int n = h + l - 7 * m + 114;
        final int month = n / 31;
        final int day = n % 31 + 1;

        return LocalDate.of(year, month, day);
    }

    public static LocalDate ascensionThursday(LocalDate easterSunday) {
        Objects.requireNonNull(easterSunday, "easterSunday");
        return easterSunday.plusDays(39);
    }

    public static LocalDate ascensionThursday(LocalInterval easter) {
        Objects.requireNonNull(easter, "easter");
        return easter.getStartDateInclusive().plusDays(45);
    }

    public static LocalDate ascensionThursday(int year) {
        return ascensionThursday(easterSunday(year));
    }

    public static LocalDate pentecostSunday(LocalDate easterSunday) {
        Objects.requireNonNull(easterSunday, "easterSunday");
        return easterSunday.plusDays(49);
    }

    public static LocalDate pentecostSunday(LocalInterval easter) {
        Objects.requireNonNull(easter, "easter");
        return easter.getStartDateInclusive().plusDays(55);
    }

    public static LocalDate pentecostSunday(int year) {
        return pentecostSunday(easterSunday(year));
    }

    public static LocalDate pentecostMonday(LocalDate easterSunday) {
        Objects.requireNonNull(easterSunday, "easterSunday");
        return easterSunday.plusDays(50);
    }

    public static LocalDate pentecostMonday(LocalInterval easter) {
        Objects.requireNonNull(easter, "easter");
        return easter.getStartDateInclusive().plusDays(56);
    }

    public static LocalDate pentecostMonday(int year) {
        return pentecostMonday(easterSunday(year));
    }

    public static Optional<LocalDateTime> getLocalDateTime(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnIndex);
        if (ts == null) {
            return Optional.empty();
        }
        return Optional.of(ts.toLocalDateTime());
    }

    public static Optional<LocalDate> getLocalDate(ResultSet rs, int columnIndex) throws SQLException {
        Date date = rs.getDate(columnIndex);
        if (date == null) {
            return Optional.empty();
        }
        return Optional.of(date.toLocalDate());
    }

    public static Calendar localDateTimeToCalendar(LocalDateTime localDateTime) {
        java.util.Date date = localDateTimeToDate(localDateTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static java.util.Date localDateTimeToDate(LocalDateTime localDateTime) {
        return localDateTimeToDate(localDateTime, EUROPE_OSLO_ZONE_ID);
    }

    public static java.util.Date localDateTimeToDate(LocalDateTime localDateTime, ZoneId zoneId) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        return java.util.Date.from(zonedDateTime.toInstant());
    }

    public static LocalDateTime dateToLocalDateTime(java.util.Date date) {
        return dateToLocalDateTime(date, EUROPE_OSLO_ZONE_ID);
    }

    public static LocalDateTime dateToLocalDateTime(java.util.Date date, ZoneId zoneId) {
        return date.toInstant().atZone(zoneId).toLocalDateTime();
    }

    public static LocalDateTime calendarToLocalDateTime(Calendar cal) {
        return calendarToLocalDateTime(cal, EUROPE_OSLO_ZONE_ID);
    }

    public static LocalDateTime calendarToLocalDateTime(Calendar cal, ZoneId zoneId) {
        return cal.toInstant().atZone(zoneId).toLocalDateTime();
    }

    private DateUtils() {
    }
}
