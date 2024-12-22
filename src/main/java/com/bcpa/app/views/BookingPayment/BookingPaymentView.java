package com.bcpa.app.views.BookingPayment;

import com.bcpa.app.views.PageView;

public final class BookingPaymentView extends PageView {
    final BookingPaymentViewController _controller;

    public BookingPaymentView(final BookingPaymentViewController controller) 
    {
        _controller = controller;
    }

    @Override
    public void show() 
    {
        while (!_controller.isBackRequested)
        {
            _controller.run();
        }
    }
}
