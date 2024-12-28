package com.bcpa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bcpa.app.views.Home.HomeView;
import com.bcpa.app.views.Home.HomeViewController;
import com.bcpa.authentication.models.Customer;
import com.bcpa.authentication.repositories.IUserRepository;
import com.bcpa.authentication.repositories.UserRepository;
import com.bcpa.authentication.services.AuthService;
import com.bcpa.event.services.EventService;
import com.bcpa.event.services.IEventService;

public class AppTest 
{
    @Test
    public void shouldFindRepositoryDependency() 
    {
        App.container.register(IUserRepository.class, UserRepository.class);
        UserRepository repo = App.container.resolve(UserRepository.class);

        assertTrue(repo != null);
    }

    @Test
    public void shouldFindPageViewDependency() 
    {
        App.container.register(HomeView.class, HomeView.class);
        HomeView view = App.container.resolve(HomeView.class);

        assertTrue(view != null);
    }

    @Test
    public void shouldFindControllerDependency() 
    {
        App.container.register(HomeViewController.class, HomeViewController.class);
        HomeViewController controller = App.container.resolve(HomeViewController.class);

        assertTrue(controller != null);
    }

    @Test
    public void shouldFindServiceDependency() 
    {
        App.container.register(IEventService.class, EventService.class);
        EventService service = App.container.resolve(EventService.class);

        assertTrue(service != null);
    }

    @Test
    public void shouldAuthenticateValidUser() 
    {
        AuthService authService = App.container.resolve(AuthService.class);
        authService.registerUser(new Customer("user@example.com", "password123",""));

        assertTrue(authService.loginUser("user@example.com", "password123") != null);
        assertFalse(authService.loginUser("user@example.com", "wrongpassword") == null);
    }
}
