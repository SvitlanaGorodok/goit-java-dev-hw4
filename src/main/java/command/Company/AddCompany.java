package command.Company;

import command.Command;
import command.Console;
import entities.dto.CompanyDto;
import entities.dto.CustomerDto;
import service.crud.CompanyService;
import service.crud.CustomerService;

public class AddCompany implements Command {
    public static final String COMMAND_NAME = "add_company";
    Console console;
    CompanyService companyService;

    public AddCompany(Console console, CompanyService companyService) {
        this.console = console;
        this.companyService = companyService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        CompanyDto company = new CompanyDto();
        System.out.println("Please enter company name:");
        company.setName(console.read());
        System.out.println("Please enter company description:");
        company.setDescription(console.read());
        companyService.create(company);
    }
}
