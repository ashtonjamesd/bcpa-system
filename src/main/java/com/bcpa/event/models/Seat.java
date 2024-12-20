package com.bcpa.event.models;

import com.bcpa.event.enums.SeatStatus;

public final class Seat
{
    private final String position;
    private final int row;
    private final int col;
    private SeatStatus status;

    public Seat(final String position, final int row, final int col, final SeatStatus status)
    {
        this.position = position;
        this.row = row;
        this.col = col;
        this.status = status;
    }

    public final String getPosition()
    {
        return position;
    }

    public final int getRow()
    {
        return row;
    }

    public final int getCol()
    {
        return col;
    }

    public final SeatStatus getStatus()
    {
        return status;
    }

    public final void setStatus(final SeatStatus status)
    {
        this.status = status;
    }
}

