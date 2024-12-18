package com.bcpa.app.services.display;

import java.util.List;
import java.util.function.Function;

public interface IWidgetService {
    public <T, R> T menuOptions(List<T> t, Function<T, R> mapper);
    public boolean getChoice(String message);
    public void showLoadingIcon(String message);
    public void wait_();
}