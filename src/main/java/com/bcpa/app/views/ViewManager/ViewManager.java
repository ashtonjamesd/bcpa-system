package com.bcpa.app.views.ViewManager;

import com.bcpa.app.views.PageView;

public final class ViewManager implements IViewManager
{
    private PageView activeView;

    @Override
    public void setActiveView(PageView view) {
        activeView = view;
    }

    public PageView getActiveView() {
        return activeView;
    }
}
