package com.bcpa;

import com.bcpa.app.TicketSystem;
import com.bcpa.app.services.IIOReader;
import com.bcpa.app.services.IOReader;
import com.bcpa.app.utils.AppMode;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.app.views.ViewManager.ViewManager;
import com.bcpa.authentication.models.Customer;
import com.bcpa.authentication.models.VenueManager;
import com.bcpa.authentication.repositories.IUserRepository;
import com.bcpa.authentication.repositories.UserRepository;
import com.bcpa.authentication.services.AuthService;
import com.bcpa.authentication.services.IAuthService;
import com.bcpa.authentication.services.PasswordHasher;
import com.bcpa.database.DbContext;

/**
 * @authors Ashton Dunderdale, Harrison O'Leary, Joshua Ford, Natalie O'Callaghan
 */
public final class App 
{
    public static void main(String[] args)
    {
        // registering dependencies
        DbContext db = new DbContext();
        PasswordHasher hasher = new PasswordHasher();

        IUserRepository userRepository = new UserRepository(db, hasher);

        // register an admin user
        userRepository.CreateUser(new VenueManager("admin", "admin"));

        IAuthService authService = new AuthService(db, hasher);
        IViewManager viewManager = new ViewManager();
        IIOReader inputReader = new IOReader();

        TicketSystem app = new TicketSystem(viewManager, authService, inputReader);
        
        app.setMode(AppMode.Debug);
        app.run();
    }
}