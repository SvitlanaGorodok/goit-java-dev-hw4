package command.Developer;

import command.Command;
import command.Console;
import entities.dto.DeveloperDto;
import service.crud.DeveloperService;

public class FindDeveloper implements Command {
    public static final String COMMAND_NAME = "find_developer";
    Console console;
    DeveloperService developerService;

    public FindDeveloper(Console console, DeveloperService developerService) {
        this.console = console;
        this.developerService = developerService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        DeveloperDto developer = new DeveloperDto();
        System.out.println("Please enter developer id:");
        while (true){
            try{
                developer.setId(Integer.parseInt(console.read()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Please use digits!");
            }
        }
        developerService.findById(developer.getId());
    }
}
