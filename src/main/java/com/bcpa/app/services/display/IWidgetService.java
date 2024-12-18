package com.bcpa.app.services.display;

import java.util.List;
import java.util.function.Function;

public interface IWidgetService {
    public <T, R> T menuOptions(final String header, final List<T> t, final Function<T, R> mapper);
    public boolean getChoice(final String message);
    public void showLoadingIcon(final String message);
    public String toTitle(final String title);
    public void wait_();
}