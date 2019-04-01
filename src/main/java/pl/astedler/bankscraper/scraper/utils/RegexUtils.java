package pl.astedler.bankscraper.scraper.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
    public static String getFirstMatchOrCrash(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (!matcher.find())
            throw new RuntimeException("Pattern not found.");
        return matcher.group(1);
    }
}
