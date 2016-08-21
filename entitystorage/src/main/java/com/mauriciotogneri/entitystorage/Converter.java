package com.mauriciotogneri.entitystorage;

public interface Converter<E>
{
    String key(E entity);

    String content(E entity);

    E create(String key, String content);
}