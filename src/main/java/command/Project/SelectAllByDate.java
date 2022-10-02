package command.Project;

import command.Command;
import command.Console;
import service.crud.ProjectService;

public class SelectAllByDate implements Command {
    public static final String COMMAND_NAME = "select_projects";
    Console console;
    ProjectService projectService;

    public SelectAllByDate(Console console, ProjectService projectService) {
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
