package command.Developer;

import command.Command;
import command.Console;
import entities.dto.DeveloperDto;
import service.crud.DeveloperService;

public class AddDeveloper implements Command {
    public static final String COMMAND_NAME = "add_developer";
    Console console;
    DeveloperService developerService;

    public AddDeveloper(Console console, DeveloperService developerService) {
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
        System.out.println("Please enter developer name:");
        developer.setFirstName(console.read());
        System.out.println("Please enter developer last name:");
        developer.setLastName(console.read());
        System.out.println("Please enter developer age:");
        while (true){
            try{
                developer.setAge(Integer.parseInt(console.read()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Please use digits!");
            }
        }
        System.out.println("Please enter developer salary:");
        while (true){
            try{
                developer.setSalary(Integer.parseInt(console.read()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Please use digits!");
            }
        }
        developerService.create(developer);
    }
}
