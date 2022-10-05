package command.Developer;

import command.Command;
import command.Console;
import service.crud.DeveloperService;

public class SelectBySkillArea implements Command {
    public static final String COMMAND_NAME = "select_by_skill_area";
    Console console;
    DeveloperService developerService;

    public SelectBySkillArea(Console console, DeveloperService developerService) {
        this.console = console;
        this.developerService = developerService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        String skillArea;
        System.out.println("Please enter skill area:");
        skillArea = console.read();
        developerService.selectBySkillArea(skillArea);
    }
}
