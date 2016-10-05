package me.mikey.challenges.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Michael on 04/10/2016.
 */
public class UserProfile {
	// The URL to use for lookups
	private static final String ECS_URL = "http://www.ecs.soton.ac.uk/people/";

	// The properties to include in the profile
	private static final List<String> WHITELISTED_PROPERTIES = Arrays.asList("jobTitle", "name", "telephone", "email");

	// The regex used to match property attributes in html (works with " and ')
	public static final Pattern PROPERTY_PATTERN = Pattern.compile(
			"property=['\"]" + //Matches property= then either type of quote
					"(?<property>(.*?))" + //Matches value inside property attribute
					"['\"](.*?)>" + //Matches either type of quote to end the attribute value, then matches the ending >
					"(?<data>(.*?))" + //Matches content of tag...
					"<" //... up until the next <
	);

	// The username of the profile
	private String username;

	// The properties (key -> value) e.g. name -> Dr David Millard
	private Map<String, String> properties;

	protected UserProfile(String username) throws IOException, UserProfileNotFoundException {
		this.username = username;
		this.properties = new HashMap<>();
		lookup();
	}

	/**
	 * Outputs the user profile to the console.
	 */
	public void display() {
		System.out.println("username: " + username);

		//Output properties map
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

		System.out.println();
	}

	/**
	 * Looks up the user profile from the ECS website and loads the data into
	 * the {@link UserProfile#properties} map.
	 *
	 * @throws IOException if the page was unable to load
	 * @throws UserProfileNotFoundException if the username was invalid
	 */
	private void lookup() throws IOException, UserProfileNotFoundException {
		//Pull contents of page
		URL url = new URL(ECS_URL + username);
		InputStreamReader inputStream = new InputStreamReader(url.openStream());

		//Close reader automatically
		try (BufferedReader reader = new BufferedReader(inputStream)) {
			String line;

			//Check each line against the regex
			while ((line = reader.readLine()) != null) {
				checkProperties(line);
			}
		}

		//No properties loaded from the page, throw an exception
		if(this.properties.size() == 0) {
			throw new UserProfileNotFoundException();
		}
	}

	/**
	 * Checks a single line of HTML in a page against the PROPERTY_PATTERN
	 * regex.
	 *
	 * @param line the line of HTML to check
	 */
	private void checkProperties(String line) {
		//Faster to do this first then the regex
		if (line.contains("property")) {
			Matcher matcher = PROPERTY_PATTERN.matcher(line);

			//For each match (the data seems to all be on one line)
			while (matcher.find()) {
				String property = matcher.group("property");
				String data = matcher.group("data");

				if(WHITELISTED_PROPERTIES.contains(property)) {
					this.properties.put(property, data);
				}
			}
		}
	}

	public Map<String, String> getProperties() {
		return properties;
	}
}
