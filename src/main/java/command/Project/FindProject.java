package command.Project;

import command.Command;
import command.Console;
import entities.dto.ProjectDto;
import service.crud.ProjectService;

public class FindProject implements Command {
    public static final String COMMAND_NAME = "find_project";
    Console console;
    ProjectService projectService;

    public FindProject(Console console, ProjectService projectService) {
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
                System.out.println("Invalid value. Please use digits!");
            }
        }
        projectService.findById(project.getId());
    }
}
