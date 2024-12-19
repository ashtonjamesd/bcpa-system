package com.bcpa.app.views.Help;

import com.bcpa.app.views.PageView;
import com.bcpa.app.views.Home.HomeView;
import com.bcpa.app.views.ViewManager.IViewManager;

public final class HelpView extends PageView
{
    private final IViewManager _viewManager;

    public HelpView(final IViewManager viewManager) 
    {
        _viewManager = viewManager;
    }

    @Override
    public final void show()
    {
        _viewManager.ioReader().clear();

        final String title = _viewManager.widgetService().toTitle("BCPA Ticket System Help");
        _viewManager.ioReader().write(title);

        _viewManager.ioReader().write("\nGet in touch\n");

        _viewManager.ioReader().write("  Email: support@bcpa.co.uk");
        _viewManager.ioReader().write("  Phone: 02017 322943");

        _viewManager.ioReader().read("\nPress enter to return.");
        _viewManager.setActiveView(HomeView.class);
    }
}
