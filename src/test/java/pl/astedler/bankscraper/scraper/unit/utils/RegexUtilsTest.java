package pl.astedler.bankscraper.scraper.unit.utils;

import org.junit.jupiter.api.Test;
import pl.astedler.bankscraper.scraper.utils.RegexUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegexUtilsTest {

    @Test
    public void getFirstMatchOrCrashGivenOneMatchingFragmentInTextShouldReturnCorrectResult() {
        String expected = "963852741poiuytrewq";
        String text = "{app.initialize('963852741poiuytrewq', 1, null, ':^)')}";
        String pattern = "app\\.initialize\\('(.*?)'";

        String result = RegexUtils.getFirstMatchOrCrash(pattern, text);

        assertEquals(expected, result);
    }

    @Test
    public void getFirstMatchOrCrashGivenManyMatchingFragmentsInTextShouldReturnFirst() {
        String expected = "555xxx";
        String text = "text.concat('555xxx').concat('777yyy').concat('999zzz');";
        String pattern = "\\.concat\\('(.*?)'";

        String result = RegexUtils.getFirstMatchOrCrash(pattern, text);

        assertEquals(expected, result);
    }

    @Test
    public void getFirstMatchOrCrashGivenMismatchedPatternAndTextShouldThrowRuntimeException() {
        String text = "This is text about something";
        String pattern = "There is a '(.*?')";
        assertThrows(RuntimeException.class, () -> RegexUtils.getFirstMatchOrCrash(pattern, text));
    }

}