package com.bcpa.logger;

import com.bcpa.database.IDbContext;

public final class DbLogger implements ILogger {

    @SuppressWarnings("unused")
    private final IDbContext _db;

    public DbLogger(IDbContext db)
    {
        _db = db;
    }

    @Override
    public void LogInfo(String message) {

    }

    @Override
    public void LogError(String message) {
        
    }
}
