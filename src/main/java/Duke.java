import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you?");

        Scanner scan = new Scanner(System.in);
        String string = scan.nextLine();
        Task[] tasks = new Task[100];
        int k, i = 0;

        while (true) {

            try {
                if (string.equals("bye")) {

                    System.out.println("Bye. Hope to see you again soon");
                    break;

                } else if (string.equals("list")) {

                    if (i == 0) {
                        throw new DukeException("☹ OOPS!!! There is currently no tasks for you.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int j = 0; j < i; j++) {
                            k = j + 1;
                            System.out.println(k + "." + tasks[j]);
                        }
                    }

                } else {

                    String front = "", back = "";
                    if (string.contains(" ")) {
                        String[] str = string.split(" ", 2);
                        front = str[0];
                        back = str[1];
                    }

                    if (back.equals("")) {
                        if (string.equals("todo") || string.equals("deadline")
                                || string.equals("event") || string.equals("done")) {
                            throw new DukeException("☹ OOPS!!! The description of a " + string + " cannot be empty.");
                        } else {
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                    }

                    if (front.equals("done")) {
                        k = Integer.parseInt(back);
                        if (k > i) {
                            throw new DukeException("☹ OOPS!!! There is no task number " + k + ".");
                        } else {
                            tasks[k - 1].markAsDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("  " + tasks[k - 1]);
                        }
                    } else {

                        String task = "", time = "";
                        if (back.contains("/")) {
                            String[] str = back.split("/", 2);
                            task = str[0];
                            time = str[1];
                        } else {
                            task = back;
                        }

                        if (front.equals("todo")) {
                            tasks[i++] = new Todo(task);
                        } else if (front.equals("deadline")) {
                            tasks[i++] = new Deadline(task, time);
                        } else if (front.equals("event")) {
                            tasks[i++] = new Event(task, time);
                        }

                        System.out.println("Got it. I've added this task:\n"
                                + "  " + tasks[i - 1].toString()
                                + "\nNow you have " + i + " tasks in the list.");
                    }

                }

            } catch (DukeException e) {
                System.out.println(e);
            }
            string = scan.nextLine();

        }

    }

}
