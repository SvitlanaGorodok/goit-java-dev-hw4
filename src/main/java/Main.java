import command.*;
import command.Company.*;
import command.Customer.*;
import command.Developer.*;
import command.Project.*;
import command.Skills.*;
import config.DatabaseManagerConnector;
import config.PropertiesConfig;
import service.crud.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String dbPassword = System.getenv("dbPassword");
        String dbUsername = System.getenv("dbusername");
        PropertiesConfig propertiesConfig = new PropertiesConfig();
        Properties properties = propertiesConfig.loadProperties("application.properties");
        DatabaseManagerConnector manager = new DatabaseManagerConnector(properties, dbUsername, dbPassword);
        try{
            Connection connection = manager.getConnection();
        } catch (SQLException e){
            e.printStackTrace();
        }

        DeveloperService developerService = new DeveloperService(manager);
        ProjectService projectService = new ProjectService(manager);
        CustomerService customerService = new CustomerService(manager);
        CompanyService companyService = new CompanyService(manager);
        SkillService skillService = new SkillService(manager);
        System.out.printf("To see all commands please enter %s\n",Help.COMMAND_NAME);
        Scanner scanner = new Scanner(System.in);
        Console console = new Console(scanner);
        List<Command> commands = new ArrayList<>();
        commands.add(new Help(console));
        commands.add(new Exit(console));
        commands.add(new AddDeveloper(console, developerService));
        commands.add(new UpdateDeveloper(console, developerService));
        commands.add(new DeleteDeveloper(console, developerService));
        commands.add(new FindDeveloper(console, developerService));
        commands.add(new SelectAllDevelopers(console, developerService));
        commands.add(new SelectByProject(console, developerService));
        commands.add(new SelectBySkillLevel(console, developerService));
        commands.add(new SelectBySkillArea(console, developerService));
        commands.add(new AddProject(console, projectService));
        commands.add(new UpdateProject(console, projectService));
        commands.add(new DeleteProject(console, projectService));
        commands.add(new FindProject(console, projectService));
        commands.add(new SelectAllProjects(console, projectService));
        commands.add(new SelectBySalary(console, projectService));
        commands.add(new SelectAllByDevelopers(console, projectService));
        commands.add(new AddCustomer(console, customerService));
        commands.add(new UpdateCustomer(console, customerService));
        commands.add(new DeleteCustomer(console, customerService));
        commands.add(new FindCustomer(console, customerService));
        commands.add(new SelectAllCustomers(console, customerService));
        commands.add(new AddCompany(console, companyService));
        commands.add(new UpdateCompany(console, companyService));
        commands.add(new DeleteCompany(console, companyService));
        commands.add(new FindCompany(console, companyService));
        commands.add(new SelectAllCompanies(console, companyService));
        commands.add(new AddSkill(console, skillService));
        commands.add(new UpdateSkill(console, skillService));
        commands.add(new DeleteSkill(console, skillService));
        commands.add(new FindSkill(console, skillService));
        commands.add(new SelectAllSkills(console, skillService));
        console.run(commands);
        scanner.close();
    }

}
