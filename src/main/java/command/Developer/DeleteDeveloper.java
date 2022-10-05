package command.Developer;

import command.Command;
import command.Console;
import service.crud.DeveloperService;

public class DeleteDeveloper implements Command {
    public static final String COMMAND_NAME = "delete_developer";
    Console console;
    DeveloperService developerService;

    public DeleteDeveloper(Console console, DeveloperService developerService) {
        this.console = console;
        this.developerService = developerService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        Integer developerId;
        System.out.println("Please enter developer id:");
        while (true){
            try{
                developerId = Integer.parseInt(console.read());
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Use digits!");
            }
        }
        developerService.delete(developerId);
    }
}
