package pl.astedler.bankscraper.scraper.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegexUtilsTest {

    @Test
    public void getFirstMatchOrCrashGivenOneMatchingFragmentInTextShouldReturnCorrectResult() {
        // given
        String expected = "963852741poiuytrewq";
        String text = "{app.initialize('963852741poiuytrewq', 1, null, ':^)')}";
        String pattern = "app\\.initialize\\('(.*?)'";

        // when
        String result = RegexUtils.getFirstMatchOrCrash(pattern, text);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void getFirstMatchOrCrashGivenManyMatchingFragmentsInTextShouldReturnFirst() {
        // given
        String expected = "555xxx";
        String text = "text.concat('555xxx').concat('777yyy').concat('999zzz');";
        String pattern = "\\.concat\\('(.*?)'";

        // when
        String result = RegexUtils.getFirstMatchOrCrash(pattern, text);

        // then
        assertEquals("555xxx", result);
    }

    @Test
    public void getFirstMatchOrCrashGivenMismatchedPatternAndTextShouldThrowRuntimeException() {
        // given
        String text = "This is text about something";
        String pattern = "There is a '(.*?')";

        // when

        // then
        assertThrows(RuntimeException.class, () -> RegexUtils.getFirstMatchOrCrash(pattern, text));
    }
}