package command.Developer;

import command.Command;
import command.Console;
import entities.dto.DeveloperDto;
import service.crud.DeveloperService;

public class SelectByProject implements Command {
    public static final String COMMAND_NAME = "select_developers_by_project";
    Console console;
    DeveloperService developerService;

    public SelectByProject(Console console, DeveloperService developerService) {
        this.console = console;
        this.developerService = developerService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        Integer projectId;
        System.out.println("Please enter project id:");
        while (true){
            try{
                projectId = Integer.parseInt(console.read());
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Please use digits!");
            }
        }
        developerService.selectByProject(projectId);
    }
}
