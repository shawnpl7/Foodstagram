package controllers;

import entities.InOut;
import entities.ShellAction;
import use_cases.DatabaseManager;
import use_cases.LoginManager;

import java.io.IOException;

public class LoginController {

    private LoginManager loginManager;
    private String loginMessage = "Please enter your login info.";
    private String signUpMessage = "Please signup below:";
    private String welcomeMessage = "Welcome to the Recipe App!";
    private String welcomeActionPrompt = "Please select an action:\n" +
            "0 Sign up\n" +
            "1 Login\n";
    private InOut inOut;

    /**
     * Create a LoginController with the given InOut and LoginManager
     * @param inOut the InOut interface for managing input/output
     * @param loginManager the LoginManager
     */
    public LoginController(InOut inOut, LoginManager loginManager) {
        this.loginManager = loginManager;
        this.inOut = inOut;
    }

    /**
     * Run the appropriate ShellAction
     * @param action ShellAction corresponding to the action that needs to be run
     */
    public void run(ShellAction action) {
        if (action == ShellAction.LOGOUT) {
            this.runLogout();
        }
    }

    /**
     * Run the welcome page logic.
     */
    public void runWelcomePage() {

        this.inOut.setOutput(this.getWelcomeMessage());

        try {
            String welcomeAction = inOut.getInput(welcomeActionPrompt);
            boolean isComplete = runWelcomeAction(Integer.parseInt(welcomeAction));

            //Loops on chosen page until valid input is given
            while (!isComplete) {
                isComplete = runWelcomeAction(Integer.parseInt(welcomeAction));
            }
        } catch (IOException e) {
            inOut.setOutput("There was an error: " + e);
        }
    }

    /**
     * Run the login page logic
     * @return boolean true if the login is successful, false otherwise
     */
    private boolean runLoginPage() {
        this.inOut.setOutput(this.loginMessage);
        try {
            //get username and password input
            String username = this.inOut.getInput("Enter username: ");
            String password = this.inOut.getInput("Enter password: ");

            //check if given username and password are valid and set appropriate output
            if (this.loginManager.login(username, password)) {
                this.inOut.setOutput("Login successful.");
                return true;
            } else {
                this.inOut.setOutput("Your username or password was incorrect.");
                return false;
            }
        } catch (IOException e) {
            this.inOut.setOutput("There was an error: " + e);
            return false;
        }

    }

    /**
     * Runs the signup page logic.
     *
     * @return boolean true if user successfully signed up, false otherwise.
     */
    private boolean runSignUpPage() {
        try {
            this.inOut.setOutput(this.signUpMessage);
            String username = this.inOut.getInput("Set username: ");
            String password = this.inOut.getInput("Set password: ");
            if (this.loginManager.signUp(username, password)) {
                this.inOut.setOutput("Sign up successful.");
                return true;
            } else {
                this.inOut.setOutput("Your username or password was invalid.");
                return false;
            }
        } catch (IOException e) {
            inOut.setOutput("There was an error: " + e);
            return false;
        }
    }

    /**
     * Handles actions from the welcome page
     *
     * @param welcomeAction The entered action from welcome page
     * @return boolean true if action was successful, false otherwise
     */
    private boolean runWelcomeAction(Integer welcomeAction) {
        switch (welcomeAction) {
            case (0):
                return this.runSignUpPage();
            case (1):
                return this.runLoginPage();
            default:
                this.inOut.setOutput("You entered an invalid action input.");
                return true;
        }
    }

    /**
     * Run logout logic
     */
    private void runLogout() {
        this.loginManager.logout();
        System.out.println("Logged out of the Recipe App.");
    }

    /**
     *
     * @return String representing the welcome message
     */
    private String getWelcomeMessage() {
        return this.welcomeMessage;
    }
}
