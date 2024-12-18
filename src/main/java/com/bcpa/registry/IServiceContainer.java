package com.bcpa.DIContainer;

public interface IServiceContainer {
    public <TInterface, TType extends TInterface> void register(final Class<TInterface> t, final Class<TType> i);
    public <T> T resolve(final Class<T> t);
}
