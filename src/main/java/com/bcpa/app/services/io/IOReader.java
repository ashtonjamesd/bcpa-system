package com.bcpa.app.services.io;

import java.util.Scanner;

public final class IOReader implements IIOReader {
    private final Scanner scanner;

    public IOReader() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public final String read(final String message) {
        System.out.print(message);
    
        try {
            if (scanner.hasNextLine()) {
                return scanner.nextLine().trim();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }
    

    @Override
    public final void write(final String message) {
       System.out.println(message);
    }

    public final void close() {
        if (scanner != null) {
            scanner.close();
        }
    }

    @Override
    public final void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public void readKey() {
        read("");
    }
}
