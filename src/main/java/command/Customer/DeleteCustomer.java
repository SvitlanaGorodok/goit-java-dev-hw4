package command.Customer;

import command.Command;
import command.Console;
import entities.dto.CustomerDto;
import service.crud.CustomerService;

import java.sql.Date;

public class DeleteCustomer implements Command {
    public static final String COMMAND_NAME = "delete_customer";
    Console console;
    CustomerService customerService;

    public DeleteCustomer(Console console, CustomerService customerService) {
        this.console = console;
        this.customerService = customerService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        CustomerDto customer = new CustomerDto();
        System.out.println("Please enter customer id:");
        while (true){
            try{
                customer.setId(Integer.parseInt(console.read()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Use digits!");
            }
        }
        System.out.println("Please enter customer name:");
        customer.setName(console.read());
        System.out.println("Please enter customer description:");
        customer.setDescription(console.read());
        customerService.delete(customer);
    }
}
