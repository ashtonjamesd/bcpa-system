package com.bcpa.app.views.ViewManager;

import com.bcpa.app.services.display.IWidgetService;
import com.bcpa.app.services.io.IIOReader;
import com.bcpa.app.views.PageView;

public interface IViewManager {
    public void setActiveView(final PageView view);
    public PageView getActiveView();

    public IWidgetService widgetService();
    public IIOReader iioReader();
}
