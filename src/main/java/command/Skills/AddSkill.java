package command.Skills;

import command.Command;
import command.Console;
import entities.dto.SkillDto;
import service.crud.SkillService;

public class AddSkill implements Command {
    public static final String COMMAND_NAME = "add_skill";
    Console console;
    SkillService skillService;

    public AddSkill(Console console, SkillService skillService) {
        this.console = console;
        this.skillService = skillService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        SkillDto skill = new SkillDto();
        System.out.println("Please enter skill area:");
        skill.setArea(console.read());
        System.out.println("Please enter skill level:");
        skill.setLevel(console.read());
        skillService.create(skill);
    }
}
