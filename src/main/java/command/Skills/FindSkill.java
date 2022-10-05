package command.Skills;

import command.Command;
import command.Console;
import service.crud.SkillService;

public class FindSkill implements Command {
    public static final String COMMAND_NAME = "find_skill";
    Console console;
    SkillService skillService;

    public FindSkill(Console console, SkillService skillService) {
        this.console = console;
        this.skillService = skillService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        Integer skillId;
        System.out.println("Please enter skill id:");
        while (true){
            try{
                skillId = Integer.parseInt(console.read());
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Please use digits!");
            }
        }
        skillService.findById(skillId);
    }
}
