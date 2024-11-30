package com.bcpa.app.utils;

import jakarta.annotation.Nullable;

/**
 * A helper class that represents the result of an operation that can be a success or a fail.
 * 
 * The class is used with:
 *   return Result.Ok(T);        // for a success
 * 
 * And:
 *   return Result.Err(String);  // for a fail
 * 
 * This method was adopted to serve as a wrapper for a method return value. Rather than returning
 * the data type exclusively, additional information surrounding the operations that took place is 
 * returned making the code more readable and expressive.
 * 
 * This class should not be inherited from.
 * 
 * @param <T> The type of the value to be returned from the result class.
 */
public final class Result<T> 
{
    public final boolean isSuccess;

    @Nullable public final T value;
    @Nullable public final String error;

    /**
     * The constructor is private so that the user of the class 
     * is forced to call the Ok and Err methods directly.
     */
    private Result(T value, boolean isSuccess, String error) 
    {
        this.isSuccess = isSuccess;
        this.value = value;
        this.error = error;
    }
    
    public static <T> Result<T> Ok(T t) 
    {
        return new Result<T>(t, true, null);
    }

    public static <T> Result<T> Err(String error) 
    {
        return new Result<T>(null, false, error);
    }
}
