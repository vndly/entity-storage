package com.mauriciotogneri.entitystorage.exceptions;

public class InvalidContentException extends RuntimeException
{
    public InvalidContentException(String message)
    {
        super(message);
    }
}