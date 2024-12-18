package com.bcpa.logger;

public final class ConsoleLogger implements ILogger {
    @Override
    public final void LogInfo(final String message) {
        System.out.println(message);
    }

    @Override
    public final void LogError(final String message) {
        System.out.println("ERROR: " + message);
    }
}
