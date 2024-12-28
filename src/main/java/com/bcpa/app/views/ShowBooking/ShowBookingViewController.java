package com.bcpa.app.views.ShowBooking;

import java.util.ArrayList;
import java.util.List;

import com.bcpa.App;
import com.bcpa.app.views.BookingPayment.BookingPaymentView;
import com.bcpa.app.views.BookingPayment.BookingPaymentViewController;
import com.bcpa.app.views.Home.HomeView;
import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.event.enums.SeatStatus;
import com.bcpa.event.models.Seat;
import com.bcpa.event.models.Show;

public final class ShowBookingViewController {
    private final IViewManager _viewManager;
    public boolean isBackRequested = false;

    private int currentRow = 0;
    private int currentCol = 0;

    private List<Seat> selectedSeats = new ArrayList<Seat>();

    public ShowBookingViewController(final IViewManager viewManager)
    {
        _viewManager = viewManager;
    }

    public final void run(final Show show)
    {
        _viewManager.ioReader().clear();

        final var title = _viewManager.widgetService().toTitle("Show Booking: " + show.getVenue());
        _viewManager.ioReader().write(title);

        _viewManager.ioReader().clear();

        displaySeatLayout(show);
        displayActiveSeatDetails(show);
        displaySelectedSeats();

        final var input = _viewManager.ioReader()
            .read("\nUse WASD to move, Q to quit, E to book, F to finish booking: ");
        
        if (input.isBlank() || input.isEmpty()) return;

        switch (input.charAt(0))
        {
            case 'w': moveUp(show); break;
            case 'a': moveLeft(show); break;
            case 's': moveDown(show); break;
            case 'd': moveRight(show); break;
            case 'q': onExitRequested(); break;
            case 'e': onSeatSelected(show); break;
            case 'f': onBookingFinished(); break;
            default: break;
        }
    }

    private final void onBookingFinished()
    {
        if (selectedSeats.isEmpty())
        {
            _viewManager.ioReader().write("No seats have been selected.");
            _viewManager.ioReader().readKey();
            return;
        }

        _viewManager.ioReader().clear();
        _viewManager.ioReader().write("Selected seat bookings:");
        for (Seat seat : selectedSeats)
        {
            _viewManager.ioReader().write("  " + seat.getPosition());
        }

        boolean continueToPayment = _viewManager.widgetService().getChoice("\nContinue to payment?");
        
        if (continueToPayment)
        {
            isBackRequested = true;
            _viewManager.setActiveView(new BookingPaymentView(App.container.resolve(BookingPaymentViewController.class), selectedSeats));
        } 
    }

    private final void onExitRequested()
    {
        isBackRequested = true;
        _viewManager.setActiveView(HomeView.class);
    }

    private final void onSeatSelected(final Show show)
    {
        final List<Seat> seats = show.getSeats();
        
        for (final Seat seat : seats)
        {
            if (seat.getCol() == currentCol && seat.getRow() == currentRow) 
            {
                if (selectedSeats.contains(seat))
                {
                    selectedSeats.remove(seat);
                    seat.setStatus(SeatStatus.Open);

                    _viewManager.ioReader().write("Removed seat from booking.");
                    _viewManager.ioReader().readKey();
                    break;
                }

                if (seat.getStatus() == SeatStatus.Booked) 
                {
                    _viewManager.ioReader().write("This seat is already booked.");
                    _viewManager.ioReader().readKey();
                    break;
                }

                if (seat.getStatus() == SeatStatus.Held) 
                {
                    _viewManager.ioReader().write("This seat is currently being held.");
                    _viewManager.ioReader().readKey();
                    break;
                }

                selectedSeats.add(seat);
                seat.setStatus(SeatStatus.Held);

                _viewManager.ioReader().write("Added seat: " + seat.getPosition());
                _viewManager.ioReader().readKey();
                break;
            }
        }
    }

    private final void moveUp(final Show show)
    {
        if (currentRow > 0) currentRow--;
    }

    private final void moveDown(final Show show)
    {
        final int maxRow = show.getSeats()
            .stream().mapToInt(Seat::getRow)
            .max().orElse(0);

        if (currentRow < maxRow) currentRow++;
    }

    private final void moveLeft(final Show show)
    {
        if (currentCol > 0) currentCol--;
    }

    private final void moveRight(final Show show)
    {
        final int maxCol = show.getSeats().stream()
            .filter(seat -> seat.getRow() == currentRow)
            .mapToInt(Seat::getCol).max().orElse(0);
            
        if (currentCol < maxCol) currentCol++;
    }

    private final void displaySelectedSeats() 
    {
        if (selectedSeats.isEmpty()) return;

        _viewManager.ioReader().write("\nBooked: ");

        System.out.print("  ");
        for (final Seat seat : selectedSeats) 
        {
            System.out.print(seat.getPosition() + ", ");
        }

        System.out.println();
    }

    private final void displaySeatLayout(final Show show)
    {
        _viewManager.ioReader().write("\nSeats:\n");
    
        int currentRowDisplay = -1;
        for (Seat seat : show.getSeats())
        {
            if (seat.getRow() != currentRowDisplay)
            {
                if (currentRowDisplay != -1) System.out.println();
                currentRowDisplay = seat.getRow();
            }
    
            String seatStr = getSeatStr(seat.getStatus());
    
            if (seat.getRow() == currentRow && seat.getCol() == currentCol)
            {
                seatStr = "[" + seatStr + "]";
            }
            else
            {
                seatStr = " " + seatStr + " ";
            }
    
            System.out.print(String.format("%-5s", seatStr));
        }
    
        System.out.println();
    }
    

    private final String getSeatStr(SeatStatus status)
    {
        return switch (status)
        {
            case Booked -> "X";
            case Held   -> "@";
            case Open   -> "0";
            default     -> "";
        };
    }

    private final void displayActiveSeatDetails(final Show show)
    {
        final Seat activeSeat = show.getSeats().stream()
            .filter(seat -> seat.getRow() == currentRow && seat.getCol() == currentCol)
            .findFirst().orElse(null);

        if (activeSeat != null)
        {
            _viewManager.ioReader().write("\nSelected Seat Details:");
            _viewManager.ioReader().write("  Position: " + activeSeat.getPosition());
            _viewManager.ioReader().write("  Status:   " + activeSeat.getStatus());
            _viewManager.ioReader().write("  Price:    £" + activeSeat.getPrice());

            double totalPrice = 0.00;
            for (Seat seat : selectedSeats)
            {
                totalPrice += seat.getPrice();
            }

            _viewManager.ioReader().write("  Total:    £" + totalPrice);
        }
        else
        {
            _viewManager.ioReader().write("\nNo active seat selected.");
        }
    }
}