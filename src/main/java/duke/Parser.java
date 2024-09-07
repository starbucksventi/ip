package duke;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Processes the information from the user. Based upon the user's commands, calls upon and relays the necessary
 * information accordingly.
 */
public class Parser {

    private String inputString;

    public Parser(String inputString) {
        setInputString(inputString);
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    public String getString() {
        return inputString;
    }

    /**
     * Calls ui to ask the user for input, process the input accordingly and calls ui again to display information back
     * to the user.
     *
     * @param tasks Stores the user's tasks inside the TaskList object while the program is running.
     * @param ui Handles the interaction between the user and program.
     */
    public void process(TaskList tasks, Ui ui) throws EmptyTaskException, InvalidInstructionException,
            EmptyCommandException {
        String instruction = inputString.split(" ", 2)[0];
        if (instruction.equals("list")) {
            ui.display(tasks);
            return;
        }
        if (inputString.split(" ", 2).length == 0) {
            throw new EmptyCommandException();
        }
        boolean flag1 = !(Arrays.asList("list", "mark", "unmark", "todo", "event", "deadline", "delete")
                .contains(inputString.split(" ", 2)[0]));
        if (inputString.split(" ", 2).length == 1 && flag1) {
            throw new InvalidInstructionException();
        }
        boolean flag2 = Arrays.asList("todo", "event", "deadline", "delete").contains(inputString.split(" ", 2)[0]);
        if (inputString.split(" ", 2).length == 1 && flag2) {
            throw new EmptyTaskException();
        }
        String remainingInput = inputString.split(" ", 2)[1];
        if (instruction.equals("mark")) {
            int idx = Integer.parseInt(remainingInput) - 1;
            tasks.set(idx, true);
            System.out.println("Nice! I've marked this task as done:\n"
                    + tasks.get(idx));
        } else if (instruction.equals("unmark")) {
            int idx = Integer.parseInt(remainingInput) - 1;
            tasks.set(idx, false);
            System.out.println("OK, I've marked this task as not done yet:\n"
                    + tasks.get(idx));
        } else if (instruction.equals("todo")) {
            Todo task = new Todo(remainingInput);
            tasks.add(task);
            ui.taskAddOrDeleteDisplay(task, "add", tasks);
        } else if (instruction.equals("deadline")) {
            String name = remainingInput.split(" /by ", 2)[0];
            LocalDate endDate = LocalDate.parse(remainingInput.split(" /by ", 2)[1]);
            Deadline task = new Deadline(name, endDate);
            tasks.add(task);
            ui.taskAddOrDeleteDisplay(task, "add", tasks);
        } else if (instruction.equals("event")) {
            String name = remainingInput.split(" /from ", 2)[0];
            remainingInput = remainingInput.split(" /from ", 2)[1];
            String start = remainingInput.split(" /to ", 2)[0];
            String end = remainingInput.split(" /to ", 2)[1];
            Event task = new Event(name, start, end);
            tasks.add(task);
            ui.taskAddOrDeleteDisplay(task, "add", tasks);
        } else if (instruction.equals("delete")) {
            int idx = Integer.parseInt(remainingInput) - 1;
            Task taskToBeDeleted = tasks.get(idx);
            tasks.delete(idx);
            ui.taskAddOrDeleteDisplay(taskToBeDeleted, "delet", tasks);
        } else if (instruction.equals("find")) {
            TaskList matchingTasks = tasks.findAll(remainingInput);
            ui.display_search(matchingTasks);
        } else {
            throw new InvalidInstructionException();
        }
    }

}
