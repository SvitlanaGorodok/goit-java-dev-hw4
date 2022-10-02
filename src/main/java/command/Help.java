package command;

import command.Customer.*;
import command.Developer.*;
import command.Project.*;

public class Help implements Command{
    public static final String COMMAND_NAME = "help";
    Console console;

    public Help(Console console) {
        this.console = console;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        System.out.printf("To see list of commands please enter %s\n", Help.COMMAND_NAME);
        System.out.printf("To add developer please enter %s\n", AddDeveloper.COMMAND_NAME);
        System.out.printf("To update developer please enter %s\n", UpdateDeveloper.COMMAND_NAME);
        System.out.printf("To delete developer please enter %s\n", DeleteDeveloper.COMMAND_NAME);
        System.out.printf("To find developer by id please enter %s\n", FindDeveloper.COMMAND_NAME);
        System.out.printf("To select all developers by project id please enter %s\n", SelectByProject.COMMAND_NAME);
        System.out.printf("To select all developers please enter %s\n", SelectAll.COMMAND_NAME);
        System.out.printf("To select all middle developers please enter %s\n", SelectBySkill.COMMAND_NAME);
        System.out.printf("To add project please enter %s\n", AddProject.COMMAND_NAME);
        System.out.printf("To update project please enter %s\n", UpdateProject.COMMAND_NAME);
        System.out.printf("To delete project please enter %s\n", DeleteProject.COMMAND_NAME);
        System.out.printf("To find project by id please enter %s\n", FindProject.COMMAND_NAME);
        System.out.printf("To get cost of project by id please enter %s\n",SelectBySalary.COMMAND_NAME);
        System.out.printf("To select projects with start date please enter %s\n",SelectAllByDate.COMMAND_NAME);
        System.out.printf("To add customer please enter %s\n", AddCustomer.COMMAND_NAME);
        System.out.printf("To update customer please enter %s\n", UpdateCustomer.COMMAND_NAME);
        System.out.printf("To delete customer please enter %s\n", DeleteCustomer.COMMAND_NAME);
        System.out.printf("To find customer by id please enter %s\n", FindCustomer.COMMAND_NAME);
        System.out.printf("To exit program please enter %s\n", Exit.COMMAND_NAME);
    }
}
