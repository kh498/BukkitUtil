package no.kh498.util;

import info.ronjenkins.slf4bukkit.ColorString;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * @author Elg
 */
@SuppressWarnings("WeakerAccess")
public class ChatUtil {

    public static void sendFormattedMsg(CommandSender sender, ColorString msg, final Object... obj) {
        sendFormattedMsg(sender, msg.toString(), obj);
    }

    /**
     * @param sender
     *     The receiver of the message
     * @param msg
     *     The message to format, supports '&amp;' as chat color prefix
     * @param obj
     *     The objects to replace
     */
    public static void sendFormattedMsg(final CommandSender sender, final String msg, final Object... obj) {
        sender.sendMessage(createFormattedMsg(msg, obj).split("\n"));
    }

    /**
     * @param msg
     *     The message to format
     * @param obj
     *     The objects to replace
     *
     * @return A message that can use '&amp;' as bukkit color and {@link String#format(String, Object...)}
     */
    public static String createFormattedMsg(final String msg, final Object... obj) {
        if (msg == null) { return null; }
        return toBukkitColor(String.format(msg, obj));
    }

    /**
     * @param str
     *     The String to colorify
     *
     * @return Add color to string that is using the color code '&amp;'
     */
    public static String toBukkitColor(final String str) {
        if (str == null) { return null; }
        return ChatColor.translateAlternateColorCodes('&', sanitizeSpecialChars(str));
    }

    /**
     * Minecraft only accept '\n' as a new line. And also not \t
     *
     * @param str
     *     The string to sanitize
     *
     * @return convert windows and mac os new line to linux newline, and replace tab char with four spaces
     */
    public static String sanitizeSpecialChars(final String str) {
        if (str == null) { return null; }
        return str.replace("\n\r", "\n").replace('\r', '\n').replace("\t", "    ");
    }

    /**
     * Create a list of string that is easier to ready than a mono colored text. Each object is colored by the secondary
     * color while the rest is colored by the primary color
     * <p>
     * Example:
     * <p>
     * {@code colorString(ChatColor.RED, ChatColor.GRAY, "test1","test2")}
     * <p>
     * returns
     * <p>
     * {@code ChatColor.GRAY + "test1" + ChatColor.RED + ", " + ChatColor.GRAY + "test1"}
     *
     * @param pri
     *     The primary color
     * @param sec
     *     The secondary color
     * @param args
     *     The objects to insert into the list
     *
     * @return A formatted string
     */
    public static String colorString(final ChatColor pri, final ChatColor sec, final Object[] args) {
        return colorString(pri, sec, "", args);
    }

    /**
     * Create a list of string that is easier to ready than a mono colored text. Each object is colored by the secondary
     * color while the rest is colored by the primary color
     * <p>
     * Example:
     * <p>
     * {@code colorString(ChatColor.RED, ChatColor.GRAY, "Test list: ", "test1","test2")}
     * <p>
     * returns
     * <p>
     * {@code ChatColor.RED + "Test list: " + ChatColor.GRAY + "test1" + ChatColor.RED + ", " + ChatColor.GRAY +
     * "test1"}
     *
     * @param pri
     *     The primary color
     * @param sec
     *     The secondary color
     * @param prefix
     *     The prefix of the list
     * @param args
     *     The objects to insert into the list
     *
     * @return A formatted string
     */
    public static String colorString(final ChatColor pri, final ChatColor sec, final String prefix,
                                     final Object[] args) {
        return colorString(pri, sec, prefix, ", ", args);
    }

    /**
     * Create a list of string that is easier to ready than a mono colored text. Each object is colored by the secondary
     * color while the rest is colored by the primary color
     * <p>
     * Example:
     * <p>
     * {@code colorString(ChatColor.RED, ChatColor.GRAY, "Test list: ", " | ", "test1","test2")}
     * <p>
     * returns
     * <p>
     * {@code ChatColor.RED + "Test list: " + ChatColor.GRAY + "test1" + ChatColor.RED + " | " + ChatColor.GRAY +
     * "test1"}
     *
     * @param pri
     *     The primary color
     * @param sec
     *     The secondary color
     * @param prefix
     *     The prefix of the list
     * @param divider
     *     What divides the different objects
     * @param args
     *     The objects to insert into the list
     *
     * @return A formatted string
     */
    public static String colorString(final ChatColor pri, final ChatColor sec, final String prefix,
                                     final String divider, final Object[] args) {
        final StringBuilder sb = new StringBuilder(pri + prefix);
        final String formattedDiv = pri + divider;

        for (final Object obj : args) {
            sb.append(sec).append(obj.toString()).append(formattedDiv);
        }
        sb.setLength(sb.length() - formattedDiv.length());
        return sb.toString();
    }
}