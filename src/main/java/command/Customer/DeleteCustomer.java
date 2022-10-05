package command.Customer;

import command.Command;
import command.Console;
import service.crud.CustomerService;


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
        Integer customerId;
        System.out.println("Please enter customer id:");
        while (true){
            try{
                customerId = Integer.parseInt(console.read());
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Use digits!");
            }
        }
        customerService.delete(customerId);
    }
}
