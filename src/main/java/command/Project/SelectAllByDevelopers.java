package command.Project;

import command.Command;
import command.Console;
import service.crud.ProjectService;

public class SelectAllByDevelopers implements Command {
    public static final String COMMAND_NAME = "select_projects_developers_amount";
    Console console;
    ProjectService projectService;

    public SelectAllByDevelopers(Console console, ProjectService projectService) {
        this.console = console;
        this.projectService = projectService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        projectService.selectAllProjects();
    }
}
