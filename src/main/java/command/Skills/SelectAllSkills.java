package command.Skills;

import command.Command;
import command.Console;
import service.crud.SkillService;

public class SelectAllSkills implements Command {
    public static final String COMMAND_NAME = "select_all_skills";
    Console console;
    SkillService skillService;

    public SelectAllSkills(Console console, SkillService skillService) {
        this.console = console;
        this.skillService = skillService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        skillService.selectAll();
    }
}
