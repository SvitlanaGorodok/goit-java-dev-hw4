package command.Project;

import command.Command;
import command.Console;
import entities.dto.ProjectDto;
import service.crud.ProjectService;

import java.sql.Date;

public class DeleteProject implements Command {
    public static final String COMMAND_NAME = "delete_project";
    Console console;
    ProjectService projectService;

    public DeleteProject(Console console, ProjectService projectService) {
        this.console = console;
        this.projectService = projectService;
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
                System.out.println("Invalid value. Use digits!");
            }
        }
        projectService.delete(projectId);
    }
}
