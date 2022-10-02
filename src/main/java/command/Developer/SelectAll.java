package command.Developer;

import command.Command;
import command.Console;
import service.crud.DeveloperService;

public class SelectAll implements Command {
    public static final String COMMAND_NAME = "select_all_developers";
    Console console;
    DeveloperService developerService;

    public SelectAll(Console console, DeveloperService developerService) {
        this.console = console;
        this.developerService = developerService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        developerService.selectAll();
    }
}
