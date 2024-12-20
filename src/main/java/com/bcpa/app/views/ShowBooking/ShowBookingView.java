package com.bcpa.app.views.ShowBooking;

import com.bcpa.app.views.PageView;
import com.bcpa.event.models.Show;

public final class ShowBookingView extends PageView
{
    private final ShowBookingViewController _controller;
    
    private final Show _show;
    
    public ShowBookingView(ShowBookingViewController controller, final Show show)
    {
        _controller = controller;
        _show = show;
    }

    @Override
    public void show()
    {
        while (!_controller.isBackRequested)
        {
            _controller.run(_show);
        }
    }
}
