package com.bcpa.app.services;

import java.util.Scanner;

public final class IOReader implements IIOReader {
    private final Scanner scanner;

    public IOReader() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String read(String message) {
        System.out.print(message);

        String input = scanner.nextLine();
        return input;
    }

    @Override
    public void write(String message) {
       System.out.println(message);
    }

    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }

    @Override
    public void clear() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}
