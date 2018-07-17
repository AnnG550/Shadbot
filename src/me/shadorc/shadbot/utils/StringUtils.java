package me.shadorc.shadbot.utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringUtils {

	/**
	 * @param str - the string to split
	 * @param limit - the result threshold
	 * @param delimiter - the delimiting regular expression
	 * @return A list with a maximum number of {@code limit} elements containing all the results of {@code str} splitted using {@code delimiter} excluding
	 *         empty results
	 */
	public static List<String> split(String str, int limit, String delimiter) {
		return Arrays.stream(str.split(delimiter, limit))
				.map(String::trim)
				.filter(word -> !word.isEmpty())
				.collect(Collectors.toList());
	}

	/**
	 * @param str - the string to split
	 * @param limit - the result threshold
	 * @param delimiter - the delimiting regular expression
	 * @return A endless list containing all the elements resulting of {@code str} splitted using space excluding empty results
	 */
	public static List<String> split(String str, int limit) {
		return StringUtils.split(str, limit, " ");
	}

	/**
	 * @param str - the string to split
	 * @param delimiter - the delimiting regular expression
	 * @return A endless list all the elements resulting of {@code str} splitted using {@code delimiter} excluding empty results
	 */
	public static List<String> split(String str, String delimiter) {
		return StringUtils.split(str, -1, delimiter);
	}

	/**
	 * @param str - the string to split
	 * @return A list without limits containing all the elements resulting of {@code str} splitted using space excluding empty results
	 */
	public static List<String> split(String str) {
		return StringUtils.split(str, -1);
	}

	/**
	 * @param str - the String to capitalize, may be null
	 * @return The capitalized String, null if null String input
	 */
	public static String capitalizeFully(String str) {
		if(str == null || str.isEmpty()) {
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.toLowerCase().substring(1);
	}

	/**
	 * @param count - the number of elements
	 * @param str - the string to get plural from
	 * @return {@code String.format("%d %ss", count, str)} if count > 1, String.format("%d %s", count, str) otherwise
	 */
	public static String pluralOf(long count, String str) {
		if(count > 1) {
			return String.format("%d %ss", count, str);
		}
		return String.format("%d %s", count, str);
	}

	/**
	 * @param str - the string from which to remove patterns
	 * @param toRemove - the strings to be substituted for each match
	 * @return The resulting {@code String}
	 */
	public static String remove(String str, String... toRemove) {
		return str.replaceAll(Arrays.stream(toRemove)
				.filter(replacement -> !replacement.isEmpty())
				.map(Pattern::quote)
				.collect(Collectors.joining("|")), "");
	}

}
