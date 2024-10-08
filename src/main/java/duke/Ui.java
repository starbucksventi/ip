package duke;

import java.util.Scanner;

/**
 * The Class that handles the interaction between the user and the program.
 */
public class Ui {

    private static final String logo = """
            '  $$$$$$$$\\                             $$\\          \s
            '  \\__$$     __|                             $$ |         \s
            '      $$ | $$$$$$\\      $$$$$$$\\ $$ | $$$$$$$\\ \s
            '      $$ | $$   __$$\\   $$    _____|$$ |  \\______$$\\\s
            '      $$ | $$$$$$$$|  \\$$$$$$\\  $$ | $$$$$$$$ |
            '      $$ | $$      ____|   \\_____$$\\  $$ |$$  ____$$ |
            '      $$ | \\$$$$$$$\\  $$$$$$$ | $$ | \\$$$$$$$ |
            '      \\__|   \\________|   \\_______/    \\__|  \\_________|

            """;

    private Scanner scanner;

    /**
     * Initialises the Ui object as the constructor.
     *
     */
    public Ui() {
        setScanner(new Scanner(System.in));
    }

    /**
     * Setter method for the Ui class
     *
     * @param scanner Takes a Scanner object to be stored within the Ui for interactions with the user.
     *
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Displays a custom message to be displayed to the user at the start of the program.
     *
     */
    public void greet() {
        System.out.println(getGreet());

    }

    public String getGreet() {
        return "____________________________________________________\n"
                + "Hello! I'm\n" + logo
                + "How may I be of service to you in this moment?\n"
                + "____________________________________________________\n";

    }

    /**
     * Displays the tasks hitherto entered by the user.
     * @param tasks A TaskList object storing tasks entered so far.
     */
    public void printList(TaskList tasks) throws TaskListOutOfBoundsException {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * This method returns the tasks hitherto entered by the user as a list
     * @param tasks The tasks listed by the user.
     * @return The tasks as an enumerated string.
     */
    public String stringList(TaskList tasks) throws TaskListOutOfBoundsException {
        StringBuilder acc = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            acc.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return acc.toString();
    }

    /**
     * Displays the customized header when the user enters the appropriate command before listing tasks.
     *
     * @param tasks The {@code Tasklist} object containing the tasks currently stored in the program.
     */
    public void display(TaskList tasks) throws TaskListOutOfBoundsException {
        System.out.println("Behold, the tasks you have envisioned are now before your eyes:");
        printList(tasks);
    }
    public String guiDisplay(TaskList tasks) throws TaskListOutOfBoundsException {
        return "Behold, the tasks you have envisioned are now before your eyes:\n" + stringList(tasks);
    }

    /**
     * The method that displays the customised header when the user searches for a specific description within the
     * tasks currently stored in the program before it lists the said tasks.
     * @param tasks The Tasklist object holding tasks currently stored in the program.
     */
    public void displaySearch(TaskList tasks) throws TaskListOutOfBoundsException {
        System.out.println("Here are the matching tasks in your list:");
        printList(tasks);
    }

    /**
     * The method that returns as a string the customised header when the user searches for a specific description
     * within the tasks currently stored in the program before it lists the said tasks.
     * @param tasks The Tasklist object holding tasks currently stored in the program.
     * @return A customised message and the enumerated tasks as a string.
     */
    public String guiDisplaySearch(TaskList tasks) throws TaskListOutOfBoundsException {
        return "Here are the matching tasks in your list:\n" + stringList(tasks);
    }

    /**
     * The method that displays the customised header and footer, includng the task, when the user adds or deletes a
     * task.
     * @param task The task that was added or deleted.
     * @param addOrDelete The String holding the action (add or delete) done to the task.
     * @param tasks The Tasklist that holds or held the task of interest.
     */
    public void taskAddOrDeleteDisplay(Task task, String addOrDelete, TaskList tasks) {
        System.out.println("Got it. I've " + addOrDelete + "ed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * The method that returns as a string the customised header and footer, includng the task, when the user adds or
     * deletes a task.
     * @param task The task that was added or deleted.
     * @param addOrDelete The String holding the action (add or delete) done to the task.
     * @param tasks The Tasklist that holds or held the task of interest.
     * @return A customised string showing what action (add or delete) was done to the task with respect to the
     *     TaskList tasks.
     */
    public String guiTaskActionDisplay(Task task, String addOrDelete, TaskList tasks) {
        return "Got it. I've " + addOrDelete + "ed this task:\n"
                + task + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * This method gives the user's input on the following line.
     * @return The user's command line input as a String.
     */
    public String out() {
        return scanner.nextLine();
    }
}
