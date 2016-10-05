package me.mikey.challenges.week1;

import org.junit.Test;

import java.io.IOException;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by Michael on 04/10/2016.
 */
public class Week1Test {

	@Test
	public void testRegex() {
		Pattern pattern = UserProfile.PROPERTY_PATTERN;

		//Valid expressions
		assertTrue(pattern.matcher("<a property='email'>test@test.com</a>").find());
		assertTrue(pattern.matcher("<a property=\"email\">test@test.com</a>").find());
		assertTrue(pattern.matcher("<a property='email' another-attribute='here'>test@test.com</a>").find());
		assertTrue(pattern.matcher("<input property='email'>test@test.com</a>").find());

		//Invalid expressions
		assertFalse(pattern.matcher("<input property='email>test@test.com</a>").find());
		assertFalse(pattern.matcher("<input property='email'test@test.com</a>").find());
	}

	@Test
	public void testLookup() {
		try {
			UserProfile profile = new UserProfile("dem");

			assertTrue("Name is scraped correctly", "Dr David Millard".equals(profile.getProperties().get("name")));
			assertTrue("Job title is scraped correctly", "Academic Staff".equals(profile.getProperties().get("jobTitle")));
			assertTrue("Phone number is scraped correctly", "+442380595567".equals(profile.getProperties().get("telephone")));
			assertTrue("Email is scraped correctly", "dem@ecs.soton.ac.uk".equals(profile.getProperties().get("email")));
		} catch (IOException | UserProfileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testException() {
		Exception exception = null;

		try {
			new UserProfile("test");
		} catch (IOException | UserProfileNotFoundException e) {
			exception = e;
		}

		assertTrue("UserProfileNotFoundException is thrown for invalid user", exception instanceof UserProfileNotFoundException);
	}
}
