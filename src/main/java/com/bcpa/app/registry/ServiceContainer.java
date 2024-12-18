package com.bcpa.app.registry;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public final class ServiceContainer implements IServiceContainer {
    private static ServiceContainer instance;
    
    private ServiceContainer() {}

    public static synchronized ServiceContainer instance() {
        if (instance == null) {
            instance = new ServiceContainer();
        }
        return instance;
    }

    private final Map<Type, Type> registry = new HashMap<>();

    /**
     * Registers a service implementation with its interface.
     *
     * @param <TInterface> The interface type.
     * @param <TType>      The implementation type.
     * @param interfaceType The interface class.
     * @param implementationType The implementation class.
     */
    public <TInterface, TType extends TInterface> void register(final Class<TInterface> interfaceType, final Class<TType> implementationType) {
        registry.put(interfaceType, implementationType);
    }

    /**
     * Resolves an instance of the specified type, including automatic dependency injection.
     *
     * @param <T>  The type to resolve.
     * @param type The class of the type to resolve.
     * @return An instance of the resolved type or null if resolution fails.
     */
    @SuppressWarnings("unchecked")
    public <T> T resolve(final Class<T> type) {
        try {
            final Type implementationType = registry.getOrDefault(type, type);
            final Class<T> implementationClass = (Class<T>)implementationType;

            final Constructor<?>[] constructors = implementationClass.getDeclaredConstructors();
            final Constructor<?> targetConstructor = findConstructorWithMostParameters(constructors);

            final Object[] parameters = resolveConstructorParameters(targetConstructor);

            return implementationClass.cast(targetConstructor.newInstance(parameters));
        } catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Finds the constructor with the most parameters.
     *
     * @param constructors The array of constructors.
     * @return The constructor with the most parameters.
     */
    private Constructor<?> findConstructorWithMostParameters(Constructor<?>[] constructors) {
        Constructor<?> selected = constructors[0];

        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() > selected.getParameterCount()) {
                selected = constructor;
            }
        }

        return selected;
    }

    /**
     * Resolves the parameters required by a constructor.
     *
     * @param constructor The constructor whose parameters need resolving.
     * @return An array of resolved parameters.
     * @throws Exception If resolving a parameter fails.
     */
    private Object[] resolveConstructorParameters(Constructor<?> constructor) throws Exception {
        final Class<?>[] parameterTypes = constructor.getParameterTypes();
        final Object[] parameters = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            parameters[i] = resolve(parameterTypes[i]);
        }

        return parameters;
    }
}
