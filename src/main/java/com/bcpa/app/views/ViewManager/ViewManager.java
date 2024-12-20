package com.bcpa.app.views.ViewManager;

import com.bcpa.App;
import com.bcpa.app.TicketSystem;
import com.bcpa.app.services.display.IWidgetService;
import com.bcpa.app.services.io.IIOReader;
import com.bcpa.app.views.PageView;

public final class ViewManager implements IViewManager
{
    private static volatile ViewManager _instance;

    private final IWidgetService _widgetService;
    private final IIOReader _ioReader;

    public ViewManager(final IWidgetService widgetService, final IIOReader ioReader)
    {
        _widgetService = widgetService;
        _ioReader = ioReader;
    }

    public final static ViewManager get()
    {
        if (_instance == null)
        {
            synchronized (ViewManager.class)
            {
                if (_instance == null)
                {
                    _instance = new ViewManager(
                        App.container.resolve(IWidgetService.class),
                        App.container.resolve(IIOReader.class)
                    );
                }
            }
        }

        return _instance;
    }

    @Override
    public final void setActiveView(final PageView view)
    {
        TicketSystem.GlobalActivePage = view;
    }

    @Override
    public final PageView getActiveView()
    {
        return TicketSystem.GlobalActivePage;
    }

    @Override
    public final IWidgetService widgetService()
    {
        return _widgetService;
    }

    @Override
    public final IIOReader ioReader()
    {
        return _ioReader;
    }

    @Override
    public final void setActiveView(final Class<? extends PageView> viewClass)
    {
        TicketSystem.GlobalActivePage = App.container.resolve(viewClass);
    }
}