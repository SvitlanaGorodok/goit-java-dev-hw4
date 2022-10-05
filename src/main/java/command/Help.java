package command;

import command.Company.*;
import command.Customer.*;
import command.Developer.*;
import command.Project.*;
import command.Skills.*;

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
        System.out.println("-----------------------DEVELOPERS-----------------------");
        System.out.printf("To add developer please enter %s\n", AddDeveloper.COMMAND_NAME);
        System.out.printf("To update developer please enter %s\n", UpdateDeveloper.COMMAND_NAME);
        System.out.printf("To delete developer please enter %s\n", DeleteDeveloper.COMMAND_NAME);
        System.out.printf("To find developer by id please enter %s\n", FindDeveloper.COMMAND_NAME);
        System.out.printf("To select all developers please enter %s\n", SelectAllDevelopers.COMMAND_NAME);
        System.out.printf("To select all developers by project id please enter %s\n", SelectByProject.COMMAND_NAME);
        System.out.printf("To select all developers by skill level please enter %s\n", SelectBySkillLevel.COMMAND_NAME);
        System.out.printf("To select all developers by skill area please enter %s\n", SelectBySkillArea.COMMAND_NAME);
        System.out.println("------------------------PROJECTS------------------------");
        System.out.printf("To add project please enter %s\n", AddProject.COMMAND_NAME);
        System.out.printf("To update project please enter %s\n", UpdateProject.COMMAND_NAME);
        System.out.printf("To delete project please enter %s\n", DeleteProject.COMMAND_NAME);
        System.out.printf("To find project by id please enter %s\n", FindProject.COMMAND_NAME);
        System.out.printf("To select all projects please enter %s\n", SelectAllProjects.COMMAND_NAME);
        System.out.printf("To get cost of project by id please enter %s\n",SelectBySalary.COMMAND_NAME);
        System.out.printf("To select projects with amount of developers please enter %s\n", SelectAllByDevelopers.COMMAND_NAME);
        System.out.println("------------------------CUSTOMERS------------------------");
        System.out.printf("To add customer please enter %s\n", AddCustomer.COMMAND_NAME);
        System.out.printf("To update customer please enter %s\n", UpdateCustomer.COMMAND_NAME);
        System.out.printf("To delete customer please enter %s\n", DeleteCustomer.COMMAND_NAME);
        System.out.printf("To find customer by id please enter %s\n", FindCustomer.COMMAND_NAME);
        System.out.printf("To select all customers please enter %s\n", SelectAllCustomers.COMMAND_NAME);
        System.out.println("------------------------COMPANIES------------------------");
        System.out.printf("To add company please enter %s\n", AddCompany.COMMAND_NAME);
        System.out.printf("To update company please enter %s\n", UpdateCompany.COMMAND_NAME);
        System.out.printf("To delete company please enter %s\n", DeleteCompany.COMMAND_NAME);
        System.out.printf("To find company by id please enter %s\n", FindCompany.COMMAND_NAME);
        System.out.printf("To select all companies please enter %s\n", SelectAllCompanies.COMMAND_NAME);
        System.out.println("--------------------------SKILLS--------------------------");
        System.out.printf("To add skill please enter %s\n", AddSkill.COMMAND_NAME);
        System.out.printf("To update skill please enter %s\n", UpdateSkill.COMMAND_NAME);
        System.out.printf("To delete skill please enter %s\n", DeleteSkill.COMMAND_NAME);
        System.out.printf("To find skill by id please enter %s\n", FindSkill.COMMAND_NAME);
        System.out.printf("To select all skills please enter %s\n", SelectAllSkills.COMMAND_NAME);
        System.out.println("---------------------------------------------------------");
        System.out.printf("To exit program please enter %s\n", Exit.COMMAND_NAME);
    }
}
