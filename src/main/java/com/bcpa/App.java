package com.bcpa;

import com.bcpa.app.TicketSystem;
import com.bcpa.app.services.display.IWidgetService;
import com.bcpa.app.services.display.WidgetService;
import com.bcpa.app.services.io.IIOReader;
import com.bcpa.app.services.io.IOReader;
import com.bcpa.app.utils.AppMode;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.app.views.ViewManager.ViewManager;
import com.bcpa.authentication.models.User;
import com.bcpa.authentication.models.VenueManager;
import com.bcpa.authentication.repositories.IUserRepository;
import com.bcpa.authentication.repositories.UserRepository;
import com.bcpa.authentication.services.AuthService;
import com.bcpa.authentication.services.IAuthService;
import com.bcpa.authentication.services.PasswordHasher;
import com.bcpa.database.DbContext;
import com.bcpa.database.IDbContext;
import com.bcpa.event.repositories.EventRespository;
import com.bcpa.event.repositories.IEventRepository;
import com.bcpa.event.services.EventService;
import com.bcpa.event.services.IEventService;
import com.bcpa.registry.IServiceContainer;
import com.bcpa.registry.ServiceContainer;

/**
 * @authors Ashton Dunderdale, Harrison O'Leary, Joshua Ford, Natalie O'Callaghan
 */
public final class App 
{
    public static final IServiceContainer container = new ServiceContainer();


    final public static void main(String[] args)
    {
        registerDependencies();

        final TicketSystem app = container.resolve(TicketSystem.class);

        app.setMode(AppMode.Debug);
        app.run();
    }

    private static void registerDependencies() {
        container.register(DbContext.class, DbContext.class);
        container.register(PasswordHasher.class, PasswordHasher.class);

        container.register(IUserRepository.class, UserRepository.class);
        container.register(IEventRepository.class, EventRespository.class);

        container.register(IAuthService.class, AuthService.class);
        container.register(IIOReader.class, IOReader.class);
        container.register(IWidgetService.class, WidgetService.class);
        container.register(IViewManager.class, ViewManager.class);
        container.register(IEventService.class, EventService.class);

        container.register(TicketSystem.class, TicketSystem.class);
    }
}