package no.moldesoft.utils;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Erling Molde on 25.04.2017.
 */
public class DateUtilsTest {

    @Test
    public void easterBeforeStart_2017() throws Exception {
        LocalInterval easter = DateUtils.easter(2017);
        assertFalse(easter.contains(LocalDate.of(2017, Month.APRIL, 9)));
    }

    @Test
    public void easterOnStart_2017() throws Exception {
        LocalInterval easter = DateUtils.easter(2017);
        assertTrue(easter.contains(LocalDate.of(2017, Month.APRIL, 10)));
    }

    @Test
    public void easterOnEnd_2017() throws Exception {
        LocalInterval easter = DateUtils.easter(2017);
        assertTrue(easter.contains(LocalDate.of(2017, Month.APRIL, 17)));
    }

    @Test
    public void easterAfterEnd_2017() throws Exception {
        LocalInterval easter = DateUtils.easter(2017);
        assertFalse(easter.contains(LocalDate.of(2017, Month.APRIL, 18)));
    }

    @Test
    public void easterBeforeStart_2000() throws Exception {
        LocalInterval easter = DateUtils.easter(2000);
        assertFalse(easter.contains(LocalDate.of(2000, Month.APRIL, 16)));
    }

    @Test
    public void easterOnStart_2000() throws Exception {
        LocalInterval easter = DateUtils.easter(2000);
        assertTrue(easter.contains(LocalDate.of(2000, Month.APRIL, 17)));
    }

    @Test
    public void easterOnEnd_2000() throws Exception {
        LocalInterval easter = DateUtils.easter(2000);
        assertTrue(easter.contains(LocalDate.of(2000, Month.APRIL, 24)));
    }

    @Test
    public void easterAfterEnd_2000() throws Exception {
        LocalInterval easter = DateUtils.easter(2000);
        assertFalse(easter.contains(LocalDate.of(2000, Month.APRIL, 25)));
    }

    @Test
    public void easterBeforeStart_2037() throws Exception {
        LocalInterval easter = DateUtils.easter(2037);
        assertFalse(easter.contains(LocalDate.of(2037, Month.MARCH, 29)));
    }

    @Test
    public void easterOnStart_2037() throws Exception {
        LocalInterval easter = DateUtils.easter(2037);
        assertTrue(easter.contains(LocalDate.of(2037, Month.MARCH, 30)));
    }

    @Test
    public void easterOnEnd_2037() throws Exception {
        LocalInterval easter = DateUtils.easter(2037);
        assertTrue(easter.contains(LocalDate.of(2037, Month.APRIL, 6)));
    }

    @Test
    public void easterAfterEnd_2037() throws Exception {
        LocalInterval easter = DateUtils.easter(2037);
        assertFalse(easter.contains(LocalDate.of(2037, Month.APRIL, 7)));
    }

    @Test
    public void easterSunday_2037() throws Exception {
        LocalDate localDate = DateUtils.easterSunday(2037);
        assertTrue("year is wrong, expected 2037",localDate.getYear() == 2037);
        assertTrue("month is wrong, expected april",localDate.getMonth() == Month.APRIL);
        assertTrue("day is wrong, expected 23",localDate.getDayOfMonth() == 5);
    }

}