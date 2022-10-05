package command.Developer;

import command.Command;
import command.Console;
import service.crud.DeveloperService;

public class SelectBySkillLevel implements Command {
    public static final String COMMAND_NAME = "select_by_skill_level";
    Console console;
    DeveloperService developerService;

    public SelectBySkillLevel(Console console, DeveloperService developerService) {
        this.console = console;
        this.developerService = developerService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        String skillLevel;
        System.out.println("Please enter skill level:");
        skillLevel = console.read();
        developerService.selectBySkillLevel(skillLevel);
    }
}
