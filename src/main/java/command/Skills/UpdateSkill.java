package command.Skills;

import command.Command;
import command.Console;
import entities.dto.SkillDto;
import service.crud.SkillService;

public class UpdateSkill implements Command {
    public static final String COMMAND_NAME = "update_skill";
    Console console;
    SkillService skillService;

    public UpdateSkill(Console console, SkillService skillService) {
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
        System.out.println("Please enter skill id:");
        while (true){
            try{
                skill.setId(Integer.parseInt(console.read()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Use digits!");
            }
        }
        System.out.println("Please enter skill area:");
        skill.setArea(console.read());
        System.out.println("Please enter skill level:");
        skill.setLevel(console.read());
        skillService.update(skill);
    }
}
