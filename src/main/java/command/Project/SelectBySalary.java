package command.Project;

import command.Command;
import command.Console;
import entities.dto.ProjectDto;
import service.crud.ProjectService;

public class SelectBySalary implements Command {
    public static final String COMMAND_NAME = "select_project_by_salary";
    Console console;
    ProjectService projectService;

    public SelectBySalary(Console console, ProjectService projectService) {
        this.console = console;
        this.projectService = projectService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        int projectId;
        System.out.println("Please enter project id:");
        while (true){
            try{
                projectId = Integer.parseInt(console.read());
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Please use digits!");
            }
        }
        projectService.selectBySalary(projectId);
    }
}
