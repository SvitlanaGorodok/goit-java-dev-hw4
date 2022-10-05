package command.Company;

import command.Command;
import command.Console;
import service.crud.CompanyService;

public class FindCompany implements Command {
    public static final String COMMAND_NAME = "find_company";
    Console console;
    CompanyService companyService;

    public FindCompany(Console console, CompanyService companyService) {
        this.console = console;
        this.companyService = companyService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        Integer companyId;
        System.out.println("Please enter company id:");
        while (true){
            try{
                companyId = Integer.parseInt(console.read());
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Please use digits!");
            }
        }
        companyService.findById(companyId);
    }
}
