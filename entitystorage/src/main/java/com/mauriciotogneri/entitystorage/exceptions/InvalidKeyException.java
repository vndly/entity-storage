package com.mauriciotogneri.entitystorage.exceptions;

public class InvalidKeyException extends RuntimeException
{
    public InvalidKeyException(String message)
    {
        super(message);
    }
}