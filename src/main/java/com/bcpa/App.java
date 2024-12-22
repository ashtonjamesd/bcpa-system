package com.bcpa;

import com.bcpa.app.TicketSystem;
import com.bcpa.app.registry.IServiceContainer;
import com.bcpa.app.registry.ServiceContainer;
import com.bcpa.app.services.display.IWidgetService;
import com.bcpa.app.services.display.WidgetService;
import com.bcpa.app.services.io.IIOReader;
import com.bcpa.app.services.io.IOReader;
import com.bcpa.app.utils.AppMode;
import com.bcpa.app.views.BookingPayment.BookingPaymentViewController;
import com.bcpa.app.views.EventDetails.EventDetailsViewController;
import com.bcpa.app.views.Events.EventViewController;
import com.bcpa.app.views.Home.HomeView;
import com.bcpa.app.views.Home.HomeViewController;
import com.bcpa.app.views.Login.LoginView;
import com.bcpa.app.views.Login.LoginViewController;
import com.bcpa.app.views.Profile.ProfileViewController;
import com.bcpa.app.views.Register.RegisterView;
import com.bcpa.app.views.ShowBooking.ShowBookingViewController;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.app.views.ViewManager.ViewManager;
import com.bcpa.authentication.factories.Customer.CustomerFactory;
import com.bcpa.authentication.factories.Customer.ICustomerFactory;
import com.bcpa.authentication.repositories.IUserRepository;
import com.bcpa.authentication.repositories.UserRepository;
import com.bcpa.authentication.services.AuthService;
import com.bcpa.authentication.services.IAuthService;
import com.bcpa.authentication.services.PasswordHasher;
import com.bcpa.database.DbContext;
import com.bcpa.event.factories.EventFactory;
import com.bcpa.event.factories.IEventFactory;
import com.bcpa.event.repositories.EventRespository;
import com.bcpa.event.repositories.IEventRepository;
import com.bcpa.event.services.EventService;
import com.bcpa.event.services.IEventService;
import com.bcpa.logger.ConsoleLogger;
import com.bcpa.logger.ILogger;

/**
 * @authors Ashton Dunderdale, Harrison O'Leary, Joshua Ford, Natalie O'Callaghan
 */
public final class App
{
    // A singleton is used for the service container for object control
    public static final IServiceContainer container = ServiceContainer.instance();

    // ensures dependencies are registered only once
    private static boolean isRegistered = false;

    final public static void main(String[] args)
    {
        registerDependencies();

        final TicketSystem app = container.resolve(TicketSystem.class);
        
        app.setMode(AppMode.Debug);
        app.run();

        // l: 2800, vf nbu | c: 57...
    }

    /// i decided to create my own autowiring service injection container as it is just cool to do
    /// and the existing ones for Java are a bit naff
    private static void registerDependencies() 
    {
        if (isRegistered) return;

        // other services
        container.register(DbContext.class, DbContext.class);
        container.register(PasswordHasher.class, PasswordHasher.class);
        container.register(IIOReader.class, IOReader.class);
        container.register(IWidgetService.class, WidgetService.class);
        container.register(IViewManager.class, ViewManager.class);

        // factories
        container.register(ICustomerFactory.class, CustomerFactory.class);
        container.register(IEventFactory.class, EventFactory.class);

        // repositories
        container.register(IUserRepository.class, UserRepository.class);
        container.register(IEventRepository.class, EventRespository.class);

       // services 
        container.register(IAuthService.class, AuthService.class);
        container.register(IEventService.class, EventService.class);
        container.register(ILogger.class, ConsoleLogger.class);

        // non-primitive dependency views
        container.register(HomeView.class, HomeView.class);
        container.register(LoginView.class, LoginView.class);
        container.register(RegisterView.class, RegisterView.class);

        // view controllers
        container.register(HomeViewController.class, HomeViewController.class);
        container.register(LoginViewController.class, LoginViewController.class);
        container.register(EventDetailsViewController.class, EventDetailsViewController.class);
        container.register(EventViewController.class, EventViewController.class);
        container.register(ProfileViewController.class, ProfileViewController.class);
        container.register(BookingPaymentViewController.class, BookingPaymentViewController.class);
        container.register(ShowBookingViewController.class, ShowBookingViewController.class);
        
        container.register(TicketSystem.class, TicketSystem.class);

        isRegistered = true;
    }
}