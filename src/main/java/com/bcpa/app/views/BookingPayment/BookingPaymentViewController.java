package com.bcpa.app.views.BookingPayment;

import java.util.List;

import com.bcpa.app.views.Home.HomeView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.event.enums.SeatStatus;
import com.bcpa.event.models.Seat;

public final class BookingPaymentViewController 
{
    private final IViewManager _viewManager;

    public boolean isBackRequested = false;

    public BookingPaymentViewController(final IViewManager viewManager)
    {
        _viewManager = viewManager;
    }

    public void run(final List<Seat> seats)
    {
        _viewManager.ioReader().clear();

        String title = _viewManager.widgetService().toTitle("Seat Payments");
        _viewManager.ioReader().write(title);

        _viewManager.ioReader().write("\nSeats:\n");
        for (Seat seat : seats)
        {
            _viewManager.ioReader().write("  " + seat.getPosition() + "  £" + seat.getPrice());
        }

        boolean confirmedPayment = _viewManager.widgetService().getChoice("\n\nConfirm Payment");
        if (!confirmedPayment)
        {
            _viewManager.setActiveView(HomeView.class);
            isBackRequested = true;
            return;
        }
        
        for (Seat seat : seats)
        {
            seat.setStatus(SeatStatus.Booked);
        }
        
        double totalPrice = 0;
        for (Seat seat : seats)
        {
            totalPrice += seat.getPrice();
        }

        _viewManager.ioReader().write("\nPayment complete for £" + totalPrice);
        _viewManager.ioReader().write("Press enter to return to home");
        _viewManager.ioReader().readKey();
        _viewManager.widgetService().showLoadingIcon("\nReturning to home");

        _viewManager.setActiveView(HomeView.class);
        isBackRequested = true;
    }
}
