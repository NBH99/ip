import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String info) {
        super(info);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String task;
        String time;
        int size;
        Task t;

        try {
            task = Parser.getTask(info);
            time = Parser.getTimeBy(info);
            LocalDate date = LocalDate.parse(time);
            time = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! The timing is not in the correct format.");
        }

        tasks.addDeadline(task, time);
        size = tasks.size;
        t = (tasks.list).get(size - 1);

        ui.showAddMessage(t, size);
        storage.store(tasks.list);
    }

    public boolean isExit() {
        return false;
    }

}
