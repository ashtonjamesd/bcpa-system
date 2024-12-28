package com.bcpa.app.views.BookingPayment;

import java.util.List;

import com.bcpa.app.views.PageView;
import com.bcpa.event.models.Seat;

public final class BookingPaymentView extends PageView
{
    final BookingPaymentViewController _controller;

    private final List<Seat> _seats;


    public BookingPaymentView(final BookingPaymentViewController controller, final List<Seat> seats) 
    {
        _controller = controller;
        _seats = seats;
    }

    @Override
    public void show() 
    {
        while (!_controller.isBackRequested)
        {
            _controller.run(_seats);
        }
    }
}
