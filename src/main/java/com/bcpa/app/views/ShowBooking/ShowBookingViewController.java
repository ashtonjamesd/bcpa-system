package com.bcpa.app.views.ShowBooking;

import com.bcpa.app.views.ViewManager.IViewManager;
import com.bcpa.event.enums.SeatStatus;
import com.bcpa.event.models.Seat;
import com.bcpa.event.models.Show;

public final class ShowBookingViewController {
    private final IViewManager _viewManager;
    public boolean isBackRequested = false;

    private int currentRow = 0;
    private int currentCol = 0;

    public ShowBookingViewController(final IViewManager viewManager)
    {
        _viewManager = viewManager;
    }

    public final void run(final Show show)
    {
        _viewManager.ioReader().clear();

        final var title = _viewManager.widgetService().toTitle("Show Booking: " + show.getVenue());
        _viewManager.ioReader().write(title);

        boolean exit = false;

        while (!exit)
        {
            _viewManager.ioReader().clear();
            displaySeatLayout(show);
            displayActiveSeatDetails(show);

            final var input = _viewManager.ioReader()
                .read("\nUse WASD to move, Q to quit: ");
            
            if (input.isBlank() || input.isEmpty()) continue;

            switch (input.charAt(0))
            {
                case 'w': moveUp(show); break;
                case 'a': moveLeft(show); break;
                case 's': moveDown(show); break;
                case 'd': moveRight(show); break;
                case 'q': exit = true; break;
                case 'e': onSeatSelected();
                default: break;
            }
        }
    }

    private final void onSeatSelected()
    {
        
    }

    private final void moveUp(final Show show)
    {
        if (currentRow > 0)
        {
            currentRow--;
        }
    }

    private final void moveDown(final Show show)
    {
        final int maxRow = show.getSeats()
            .stream().mapToInt(Seat::getRow)
            .max().orElse(0);

        if (currentRow < maxRow)
        {
            currentRow++;
        }
    }

    private final void moveLeft(final Show show)
    {
        if (currentCol > 0)
        {
            currentCol--;
        }
    }

    private final void moveRight(final Show show)
    {
        final int maxCol = show.getSeats().stream()
            .filter(seat -> seat.getRow() == currentRow)
            .mapToInt(Seat::getCol).max().orElse(0);
            
        if (currentCol < maxCol)
        {
            currentCol++;
        }
    }

    private final void displaySeatLayout(final Show show)
    {
        System.out.println("\nSeats:\n");
    
        int currentRowDisplay = -1;
        for (Seat seat : show.getSeats())
        {
            if (seat.getRow() != currentRowDisplay)
            {
                if (currentRowDisplay != -1)
                {
                    System.out.println();
                }

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
            System.out.println("\nSelected Seat Details:");
            System.out.println("  Position: " + activeSeat.getPosition());
            System.out.println("  Status:   " + activeSeat.getStatus());
        }
        else
        {
            System.out.println("\nNo active seat selected.");
        }
    }
}