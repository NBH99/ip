import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateCommand extends Command {

    public DateCommand(String action, String info) {
        super(action, info);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        String time;

        if (info.equals("")) {
            throw new DukeException("☹ OOPS!!! The description cannot be empty.");
        } else {
            try {
                LocalDate date = LocalDate.parse(info);
                time = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException e) {
                throw new DukeException("☹ OOPS!!! The timing is not in the correct format.");
            }
            ui.showDate(time);
            tasks.dateTask(time);
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}