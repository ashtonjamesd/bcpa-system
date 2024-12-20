package com.bcpa.app.views.Home;

import com.bcpa.app.views.PageView;

public final class HomeView extends PageView
{
    private final HomeViewController _controller;

    public HomeView(final HomeViewController controller)
    {
        _controller = controller;
    }

    @Override
    public final void show()
    {
        while (!_controller.isBackRequested)
        {
            _controller.run();
        }
    }
}
