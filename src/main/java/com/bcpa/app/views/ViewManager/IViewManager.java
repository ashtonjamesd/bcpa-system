package com.bcpa.app.views.ViewManager;

import com.bcpa.app.views.PageView;

public interface IViewManager {
    public void setActiveView(PageView view);
    public PageView getActiveView();
}
