package com.bcpa.app.views.ViewManager;

import com.bcpa.app.services.display.IWidgetService;
import com.bcpa.app.services.io.IIOReader;
import com.bcpa.app.views.PageView;

public final class ViewManager implements IViewManager
{
    private final IWidgetService _widgetService;
    private final IIOReader _ioReader;
    private PageView activeView;

    public ViewManager(IWidgetService widgetService, IIOReader ioReader) 
    {
        _widgetService = widgetService;
        _ioReader = ioReader;
    }

    @Override
    public final void setActiveView(final PageView view) {
        activeView = view;
    }

    @Override
    public final PageView getActiveView() {
        return activeView;
    }

    @Override
    public final IWidgetService widgetService() {
        return _widgetService;
    }

    @Override
    public final IIOReader ioReader() {
        return _ioReader;
    }
}
