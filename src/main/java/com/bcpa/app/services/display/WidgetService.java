package com.bcpa.app.services.display;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.bcpa.app.services.io.IIOReader;

public final class WidgetService implements IWidgetService 
{
    private final IIOReader _inputReader;

    public WidgetService(final IIOReader inputReader) 
    {
        _inputReader = inputReader;
    }

    @Override
    public final <T, R> T menuOptions(final List<T> items, final Function<T, R> mapper) 
    {
        if (items.isEmpty()) return null;

        final var options = items.stream()
            .map(mapper).collect(Collectors.toList());

        int pointer = 0;

        while (true) 
        {
            System.out.println("\033[H\033[2J");
            System.out.flush();

            for (int i = 0; i < options.size(); i++) 
            {
                if (i == pointer) 
                {
                    System.out.println("-> " + options.get(i));
                } else 
                {
                    System.out.println("   " + options.get(i));
                }
            }

            System.out.println("\nUse W (up), S (down), and Enter (select): ");

            String input = _inputReader.read("");

            switch (input)
            {
                case "w":
                    if (pointer > 0) pointer--;
                    break;
                case "s":
                    if (pointer < options.size() - 1) pointer++;
                    break;
                case "":
                    return items.get(pointer);
                default:
                    System.out.println("Invalid input. Use W, S, or Enter.");
            }
        }
    }

    @Override
    public final boolean getChoice(final String message) {
        while (true) {
            String choice = _inputReader.read(message + " (y/n): ").toLowerCase();
    
            if (choice.isEmpty()) {
                _inputReader.write("No input provided. Please enter 'y' or 'n'.");
                continue;
            }

            if (!choice.equals("y") && !choice.equals("n")) {
                _inputReader.write("Invalid option '" + choice + "'. Please enter 'y' or 'n'.");
                continue;
            }
    
            return choice.equals("y");
        }
    }

    @Override
    public final void wait_()
    {
        try
        {
            Thread.sleep(Long.MAX_VALUE);
        }
        catch (Exception ex) { }
    }
}
