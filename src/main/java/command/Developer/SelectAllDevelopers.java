package command.Developer;

import command.Command;
import command.Console;
import service.crud.DeveloperService;

public class SelectAllDevelopers implements Command {
    public static final String COMMAND_NAME = "select_all_developers";
    Console console;
    DeveloperService developerService;

    public SelectAllDevelopers(Console console, DeveloperService developerService) {
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
