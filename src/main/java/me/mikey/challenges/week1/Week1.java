package me.mikey.challenges.week1;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Michael on 04/10/2016.
 */
public class Week1 {
	// To read from the console
	private Scanner in = new Scanner(System.in);

	/**
	 * Requests the user to enter an ECS username, and then looks up the UserProfile for
	 * that username.
	 *
	 */
	public void inputUsername() {
		while(true) {
			System.out.println("Please enter a university username: ");
			String username = in.nextLine();

			if(username.isEmpty())
				System.exit(0);

			try {
				UserProfile profile = new UserProfile(username);
				profile.display();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("An error occurred whilst trying to retrieve the web page");
			} catch (UserProfileNotFoundException e) {
				System.err.println("Unable to find user with username " + username);
			}
		}
	}

	public static void main(String[] args) {
		new Week1().inputUsername();
	}
}
