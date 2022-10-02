package command.Project;

import command.Command;
import command.Console;
import entities.dto.ProjectDto;
import service.crud.ProjectService;

import java.sql.Date;

public class AddProject implements Command {
    public static final String COMMAND_NAME = "add_project";
    Console console;
    ProjectService projectService;

    public AddProject(Console console, ProjectService projectService) {
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
        projectService.create(project);
    }
}
