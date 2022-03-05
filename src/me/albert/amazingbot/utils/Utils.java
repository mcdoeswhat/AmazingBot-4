package me.albert.amazingbot.utils;

import me.albert.amazingbot.AmazingBot;
import org.bukkit.configuration.ConfigurationSection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static boolean hasGroup(Long groupID) {
        ConfigurationSection section = AmazingBot.getInstance().getConfig().getConfigurationSection("groups");
        for (String s : section.getKeys(false)) {
            if (s.equalsIgnoreCase(String.valueOf(groupID))) {
                return true;
            }
        }
        return false;
    }


    public static boolean isSameDay(final Date date1, final Date date2) {
        if (date1 != null && date2 != null) {
            final Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            final Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameDay(cal1, cal2);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    private static boolean isSameDay(final Calendar cal1, final Calendar cal2) {
        if (cal1 != null && cal2 != null) {
            return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        }
        throw new IllegalArgumentException("The date must not be null");
    }
}
