package command.Skills;

import command.Command;
import command.Console;
import service.crud.SkillService;


public class DeleteSkill implements Command {
    public static final String COMMAND_NAME = "delete_skill";
    Console console;
    SkillService skillService;

    public DeleteSkill(Console console, SkillService skillService) {
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
                System.out.println("Invalid value. Use digits!");
            }
        }
        skillService.delete(skillId);
    }
}
