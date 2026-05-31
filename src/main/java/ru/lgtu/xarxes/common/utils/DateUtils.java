package ru.lgtu.xarxes.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Утилиты для работы с датами и временем.
 * Предоставляет удобные методы форматирования и манипуляции датами.
 */
public class DateUtils {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    private static final DateTimeFormatter PRETTY_DATE_FORMATTER =
            DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                    .withLocale(new Locale("ru"));

    /**
     * Форматирует дату в формате дд.vv.гггг (например: 24.05.2026)
     */
    public static String formatDate(LocalDate date) {
        return date != null ? date.format(DATE_FORMATTER) : "";
    }

    /**
     * Форматирует дату и время в формате дд.MM.гггг ЧЧ:мм
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DATE_TIME_FORMATTER) : "";
    }

    /**
     * Красивое форматирование даты на русском языке
     * (например: 24 мая 2026)
     */
    public static String formatPrettyDate(LocalDate date) {
        return date != null ? date.format(PRETTY_DATE_FORMATTER) : "";
    }


    /**
     * Возвращает время в формате "X времени назад" с правильными русскими окончаниями
     * Примеры: "2 часа назад", "3 дня назад", "1 минуту назад"
     */
    public static String getTimeAgo(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }

        LocalDateTime now = LocalDateTime.now();
        long secondsAgo = java.time.temporal.ChronoUnit.SECONDS.between(dateTime, now);

        if (secondsAgo < 60) {
            return formatTimeAgo(secondsAgo, "секунду", "секунды", "секунд");
        }

        long minutesAgo = java.time.temporal.ChronoUnit.MINUTES.between(dateTime, now);
        if (minutesAgo < 60) {
            return formatTimeAgo(minutesAgo, "минуту", "минуты", "минут");
        }

        long hoursAgo = java.time.temporal.ChronoUnit.HOURS.between(dateTime, now);
        if (hoursAgo < 24) {
            return formatTimeAgo(hoursAgo, "час", "часа", "часов");
        }

        long daysAgo = java.time.temporal.ChronoUnit.DAYS.between(dateTime, now);
        if (daysAgo < 7) {
            return formatTimeAgo(daysAgo, "день", "дня", "дней");
        }

        long weeksAgo = daysAgo / 7;
        if (weeksAgo < 4) {
            return formatTimeAgo(weeksAgo, "неделю", "недели", "недель");
        }

        long monthsAgo = daysAgo / 30;
        if (monthsAgo < 12) {
            return formatTimeAgo(monthsAgo, "месяц", "месяца", "месяцев");
        }

        long yearsAgo = daysAgo / 365;
        return formatTimeAgo(yearsAgo, "год", "года", "лет");
    }

    /**
     * Вспомогательный метод для форматирования времени с правильными окончаниями
     * @param amount количество единиц времени
     * @param singular окончание для 1 (например: "час")
     * @param dual окончание для 2-4 (например: "часа")
     * @param plural окончание для 5-20, 25-30 и т.д. (например: "часов")
     */

    private static String formatTimeAgo(long amount, String singular, String dual, String plural) {
        String ending;

        if (amount % 10 == 1 && amount % 100 != 11) {
            ending = singular;
        } else if (amount % 10 >= 2 && amount % 10 <= 4 && (amount % 100 < 10 || amount % 100 >= 20)) {
            ending = dual;
        } else {
            ending = plural;
        }

        return amount + " " + ending + " назад";
    }

}