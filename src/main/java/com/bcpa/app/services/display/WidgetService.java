package com.bcpa.app.services.display;
import java.util.Arrays;
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
    public final <T, R> T menuOptions(final String header, final List<T> items, final Function<T, R> mapper) 
    {
        if (items.isEmpty()) return null;

        final var options = items.stream()
            .map(mapper).collect(Collectors.toList());

        int pointer = 0;
        while (true) 
        {
            System.out.println("\033[H\033[2J");
            System.out.flush();

            System.out.println(header + "\n\n");

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
                    else pointer = items.size() - 1;
                    break;
                case "s":
                    if (pointer < options.size() - 1) pointer++;
                    else pointer = 0;
                    break;
                case "":
                    return items.get(pointer);
                default:
                    System.out.println("Invalid input. Use W, S, or Enter.");
            }
        }
    }

    @Override
    public final String menuOptions(final String header, final List<String> items) 
    {
        if (items.isEmpty()) return null;

        int pointer = 0;
        while (true) 
        {
            System.out.println("\033[H\033[2J");
            System.out.flush();

            System.out.println(header + "\n\n");

            for (int i = 0; i < items.size(); i++) 
            {
                if (i == pointer) 
                {
                    System.out.println("-> " + items.get(i));
                } else 
                {
                    System.out.println("   " + items.get(i));
                }
            }

            System.out.println("\nUse W (up), S (down), and Enter (select): ");

            String input = _inputReader.read("");

            switch (input.toLowerCase()) 
            {
                case "w":
                    if (pointer > 0) pointer--;
                    else pointer = items.size() - 1;
                    break;
                case "s":
                    if (pointer < items.size() - 1) pointer++;
                    else pointer = 0;
                    break;
                case "":
                    return items.get(pointer);
                default:
                    System.out.println("Invalid input. Use W, S, or Enter.");
            }
        }
    }


    @Override
    public final boolean getChoice(final String message) 
    {
        while (true) 
        {
            String choice = _inputReader.read(message + " (y/n): ").toLowerCase();
    
            if (choice.isEmpty()) 
            {
                _inputReader.write("No input provided. Please enter 'y' or 'n'.");
                continue;
            }

            if (!choice.equals("y") && !choice.equals("n")) 
            {
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
        catch (Exception ex) 
        { 

        }
    }

    @Override
    public void showLoadingIcon(String message) 
    {
        try 
        {
            // final var symbols = Arrays.asList("|", "/", "-", "\\");
            final var symbolsAlt = Arrays.asList(".", "o", "0", "O", "0", "o");
            
            System.out.println(message + "...");
            for (int i = 0; i < 20; i++) {
                System.out.print("\r" + symbolsAlt.get(i % symbolsAlt.size()));
                Thread.sleep(10);
            }
            
            System.out.print("\r");
        } 
        catch (final InterruptedException ex) 
        {
            Thread.currentThread().interrupt();
            System.err.println("Loading interrupted: " + ex.getMessage());
        }
    }

    @Override
    public String toTitle(String title) {
        return " < == " + title + " == >";
    }
}
