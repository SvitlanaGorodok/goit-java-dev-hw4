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
        ProjectDto project = new ProjectDto();
        System.out.println("Please enter project id:");
        while (true){
            try{
                project.setId(Integer.parseInt(console.read()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Use digits!");
            }
        }
        System.out.println("Please enter project name:");
        project.setName(console.read());
        System.out.println("Please enter project description:");
        project.setDescription(console.read());
        System.out.println("Please enter project start date:");
        while (true){
            try{
                project.setStartDate(Date.valueOf(console.read()));
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Invalid value. Please use format YYYY-MM-DD!");
            }
        }
        projectService.delete(project);
    }
}
