package com.bcpa;

import com.bcpa.app.TicketSystem;
import com.bcpa.app.services.display.IWidgetService;
import com.bcpa.app.services.display.WidgetService;
import com.bcpa.app.services.io.IIOReader;
import com.bcpa.app.services.io.IOReader;
import com.bcpa.app.utils.AppMode;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.app.views.ViewManager.ViewManager;
import com.bcpa.authentication.models.VenueManager;
import com.bcpa.authentication.repositories.IUserRepository;
import com.bcpa.authentication.repositories.UserRepository;
import com.bcpa.authentication.services.AuthService;
import com.bcpa.authentication.services.IAuthService;
import com.bcpa.authentication.services.PasswordHasher;
import com.bcpa.database.DbContext;
import com.bcpa.event.repositories.EventRespository;
import com.bcpa.event.repositories.IEventRepository;
import com.bcpa.event.services.EventService;
import com.bcpa.event.services.IEventService;

/**
 * @authors Ashton Dunderdale, Harrison O'Leary, Joshua Ford, Natalie O'Callaghan
 */
public final class App 
{
    final public static void main(String[] args)
    {
        // registering dependencies
        final DbContext db = new DbContext();
        final PasswordHasher hasher = new PasswordHasher();

        final IUserRepository userRepository = new UserRepository(db, hasher);
        userRepository.createUser(new VenueManager("admin", "admin"));

        final IAuthService authService = new AuthService(hasher, userRepository);

        final IIOReader inputReader = new IOReader();
        final IWidgetService widgetService = new WidgetService(inputReader);
        final IViewManager viewManager = new ViewManager(widgetService, inputReader);

        final IEventRepository eventRepository = new EventRespository(db);
        final IEventService eventService = new EventService(eventRepository);
        
        final TicketSystem app = new TicketSystem(viewManager, authService, eventService);
        
        app.setMode(AppMode.Debug);
        app.run();
    }
}