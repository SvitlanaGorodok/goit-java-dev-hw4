package command.Company;

import command.Command;
import command.Console;
import service.crud.CompanyService;

public class SelectAllCompanies implements Command {
    public static final String COMMAND_NAME = "select_all_companies";
    Console console;
    CompanyService companyService;

    public SelectAllCompanies(Console console, CompanyService companyService) {
        this.console = console;
        this.companyService = companyService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        companyService.selectAll();
    }
}
