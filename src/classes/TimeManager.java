package classes;

import classes.exceptions.BadTimeFormatException;

/**
 * provides time managment functionalities
 */
public class TimeManager {

    /**
     * returns hour formatted to [hh-mm-ss]
     * @param input hour to format in format [hh:mm:ss] or [hh:mm] ot [hh] or [h]
     * @return formatted hour
     * @throws BadTimeFormatException if hour can not be formatted
     */
    public static String convertHour(String input) throws BadTimeFormatException {
        if (input.matches("\\d\\d:\\d\\d:\\d\\d"))
            return input;
        if (input.matches("\\d\\d:\\d\\d"))
            return input + ":00";
        if (input.matches("\\d\\d"))
            return input + ":00:00";
        if (input.matches("\\d"))
            return "0" + input + ":00:00";
        throw new BadTimeFormatException("Input " + input + " is not a proper hour format. Check --help for" +
                "more info.");
    }

    /**
     * checks if given day is formatted properly
     * @param input day to check
     * @return true if day is in format [yyyy-mm-dd]
     * @throws BadTimeFormatException when day is not formated properly
     */
    public static boolean checkDay(String input) throws BadTimeFormatException {
        if (input.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d"))
            return true;
        throw new BadTimeFormatException("Input " + input + " is not a proper day format. Check --help for" +
                "more info.");
    }

    /**
     * substracts day from date in format [day hour]
     * @param date date containing day to substract
     * @return day in format [yyyy-mm-dd]
     */
    public static String getDay(String date) {
        return date.substring(0,10);
    }

    /**
     * substracts hour from date in format [day hour]
     * @param date date containing hour to substract
     * @return hour in format [hh:mm:ss]
     */
    public static String getHour(String date) {
        return date.substring(11,19);
    }

    /**
     * compares the values of two hours in format [hh:mm:ss], only [hh] parts are compared
     * @param hour1 first argument
     * @param hour2 second argument
     * @return true if hours are equal, false otherwise
     */
    public static boolean compareHours(String hour1, String hour2) {
        return getHourNumber(hour1).equals(getHourNumber(hour2));
    }

    /**
     * returns integer value of part of given hour
     * @param hour houre to take the number from
     * @return [hh] part from an hour given in format [hh:mm:ss]
     */
    public static Integer getHourNumber(String hour) {
        return Integer.valueOf(hour.substring(0, 2));
    }

    /**
     * checks if and hour is between border hours (with inlusion of the border values)
     * @param startHour left border of hour interval
     * @param endHour right border of hour interval
     * @param testHour hour to test
     * @return true if test hour is between border hours
     */
    public static boolean isHourBetween(String startHour, String endHour, String testHour) {
        return (getHourNumber(startHour) <= getHourNumber(testHour)
                && getHourNumber(testHour) <= getHourNumber(endHour));
    }

    /**
     * function that compares two days in format [yyyy-mm-dd]
     * @param day1 main day
     * @param day2 secondary day
     * @return true if main day is later than secondary day, otherwise false
     */
    public static boolean compareDays(String day1, String day2) {
        if (getYearNumber(day1) > getYearNumber(day2))
            return true;
        if (getYearNumber(day1).equals(getYearNumber(day2)) && getMonthNumber(day1) > getMonthNumber(day2))
            return true;
        return getYearNumber(day1).equals(getYearNumber(day2)) && getMonthNumber(day1).equals(getMonthNumber(day2))
                && getDayNumber(day1) > getDayNumber(day2);
    }

    /**
     * function that compares if two days in format [yyyy-mm-dd] are equal
     * @param day1 first day
     * @param day2 second day
     * @return true if first day is equal to second day, otherwise false
     */
    public static boolean checkDaysEqual(String day1, String day2) {
        return getYearNumber(day1).equals(getYearNumber(day2)) && getMonthNumber(day1).equals(getMonthNumber(day2))
                && getDayNumber(day1).equals(getDayNumber(day2));
    }

    /**
     * finds the SString representation of the day following given day
     * @param day day to find its next day
     * @return String representation of the day after given day
     */
    public static String nextDay(String day) {
        StringBuilder result = new StringBuilder();
        switch (getDayNumber(day)) {
            case 28:
                if (getMonthNumber(day) == 2) {
                    if (getYearNumber(day) % 4 != 0)
                        return result.append(getYearNumber(day)).append("-").append("03").append("-").append("01")
                                .toString();
                    else
                        return result.append(getYearNumber(day)).append("-").append("02").append("-").append("29")
                                .toString();
                }
                break;
            case 29:
                if (getMonthNumber(day) == 2) {
                    return result.append(getYearNumber(day)).append("-").append("03").append("-").append("01")
                            .toString();
                }
                break;
            case 30:
                Integer month = getMonthNumber(day);
                if (month == 4 || month == 6 || month == 9 || month == 11)
                    return result.append(getYearNumber(day)).append("-").append(makeLength2(month + 1)).append("-")
                            .append("01").toString();
                break;
            case 31:
                if (getMonthNumber(day) == 12)
                    return result.append(getYearNumber(day) + 1).append("-").append("01").append("-").append("01")
                            .toString();
                return result.append(getYearNumber(day)).append("-").append(makeLength2(getMonthNumber(day) + 1))
                        .append("-").append("01")
                        .toString();
        }
        return result.append(getYearNumber(day)).append("-").append(makeLength2(getMonthNumber(day))).append("-")
                .append(makeLength2(getDayNumber(day) + 1)).toString();
    }

    /**
     * add 0 to the beggining if its length is 1
     * @param x param to return as a String of legnth 2, can be only of length 1 or 2
     * @return String of length 2
     */
    private static String makeLength2(Integer x) {
        String partResult = x.toString();
        if (partResult.length() == 1) return "0" + partResult;
        return partResult;
    }

    /**
     * returns year from day
     * @param day day to get year from
     * @return year in form of Integer
     */
    private static Integer getYearNumber(String day) {
        return Integer.valueOf(day.substring(0, 4));
    }

    /**
     * returns month from day
     * @param day day to get month from
     * @return month in form of Integer
     */
    private static Integer getMonthNumber(String day) {
        return Integer.valueOf(day.substring(5, 7));
    }

    /**
     * returns day ([dd] from [yyyy-mm-dd]) from day
     * @param day day to get day ([dd] from [yyyy-mm-dd]) from
     * @return day ([dd] from [yyyy-mm-dd]) in form of Integer
     */
    private static Integer getDayNumber(String day) {
        return Integer.valueOf(day.substring(8, 10));
    }
}
