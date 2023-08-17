package group13.ecobikerental.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

public class Utils {
	/**
     * The date formatter used for converting dates to string.
     */
    public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    /**
     * The logger instance for logging.
     */
    private static Logger LOGGER = getLogger(Utils.class.getName());
    /**
     * Static initializer block to configure the logging format.
     */
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-4s] [%1$tF %1$tT] [%2$-7s] %5$s %n");
    }

    /**
     * Gets a logger instance for the specified class name.
     * @param className The name of the class for which the logger is requested.
     * @return A logger instance.
     */
    public static Logger getLogger(String className) {
        return Logger.getLogger(className);
    }

    /**
     * Formats an integer as currency using the specified locale.
     * @param num The integer to format as currency.
     * @return The formatted currency string.
     */
    public static String getCurrencyFormat(int num) {
        Locale vietname = new Locale("vi", "VN");
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(vietname);
        return defaultFormat.format(num);
    }

    /**
     * Return a {@link java.lang.String String} the current date and time as a formatted string.
     * @return the current time as {@link java.lang.String String}.
     */
    public static String getToday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * Returns the MD5 hash of a given plain text message.
     * @param message The plain text message to hash.
     * @return The MD5 hash of the message.
     */
//    public static String md5(String message) {
//        String digest = null;
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            byte[] hash = md.digest(message.getBytes("UTF-8"));
//            // converting byte array to Hexadecimal String
//            StringBuilder sb = new StringBuilder(2 * hash.length);
//            for (byte b : hash) {
//                sb.append(String.format("%02x", b & 0xff));
//            }
//            digest = sb.toString();
//        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
//            Utils.getLogger(Utils.class.getName());
//            digest = "";
//        }
//        return digest;
//    }

//    public static void convertObjectToJson(Object obj){
//
//    }

//    public static void convertJsonToObject(final String response){
//
//    }
}
