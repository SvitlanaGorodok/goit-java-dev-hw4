import command.*;
import command.Customer.*;
import command.Developer.*;
import command.Project.*;
import config.DatabaseManagerConnector;
import config.PropertiesConfig;
import service.crud.CustomerService;
import service.crud.DeveloperService;
import service.crud.ProjectService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String dbPassword = "12345";
        String dbUsername = "postgres";
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
        commands.add(new SelectAll(console, developerService));
        commands.add(new SelectByProject(console, developerService));
        commands.add(new SelectBySkill(console, developerService));
        commands.add(new AddProject(console, projectService));
        commands.add(new UpdateProject(console, projectService));
        commands.add(new DeleteProject(console, projectService));
        commands.add(new FindProject(console, projectService));
        commands.add(new SelectBySalary(console, projectService));
        commands.add(new SelectAllByDate(console, projectService));
        commands.add(new AddCustomer(console, customerService));
        commands.add(new UpdateCustomer(console, customerService));
        commands.add(new DeleteCustomer(console, customerService));
        commands.add(new FindCustomer(console, customerService));
        console.run(commands);
        scanner.close();
    }

}
