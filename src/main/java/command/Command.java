package command;

import java.util.Scanner;

public interface Command {
    boolean canExecute(String input);
    void execute();
}
