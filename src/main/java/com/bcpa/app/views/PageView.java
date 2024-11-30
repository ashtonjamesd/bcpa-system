package com.bcpa.app.views;

import com.bcpa.app.services.IIOReader;

public abstract class PageView 
{
    // public String viewName;
    protected IIOReader _ioReader;

    public abstract void show();
}