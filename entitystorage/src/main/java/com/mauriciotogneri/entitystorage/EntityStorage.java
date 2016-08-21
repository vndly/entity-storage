package com.mauriciotogneri.entitystorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.mauriciotogneri.entitystorage.exceptions.EntityNotFoundException;
import com.mauriciotogneri.entitystorage.exceptions.InvalidKeyException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class EntityStorage<E>
{
    private final String indexKey;
    private final SharedPreferences preferences;
    private final Converter<E> converter;

    public EntityStorage(Context context, String name, String indexKey, Converter<E> converter)
    {
        this.indexKey = indexKey;
        this.converter = converter;
        this.preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    // ========================================== INDEX ========================================= \\

    public Set<String> index()
    {
        return new TreeSet<>(getStringSet(indexKey));
    }

    public boolean isEmpty()
    {
        return index().isEmpty();
    }

    public boolean exists(String key)
    {
        checkInvalidKey(key);

        Set<String> index = index();

        return index.contains(key);
    }

    private void removeKey(String key)
    {
        Set<String> index = index();
        index.remove(key);
        putStringSet(indexKey, index);
    }

    private void addKey(String key)
    {
        Set<String> index = index();
        index.add(key);
        putStringSet(indexKey, index);
    }

    // ======================================== ENTITIES ======================================== \\

    public E entity(String key)
    {
        checkInvalidKey(key);

        if (!contains(key) || !exists(key))
        {
            throw new EntityNotFoundException(String.format("Entity with key '%s' not found", key));
        }

        return converter.create(key, getString(key, null));
    }

    public List<E> entities()
    {
        List<E> entities = new ArrayList<>();

        for (String key : index())
        {
            entities.add(entity(key));
        }

        return entities;
    }

    public void addEntity(E entity)
    {
        String key = converter.key(entity);
        String content = converter.content(entity);

        checkInvalidKey(key);

        putString(key, content);
        addKey(key);
    }

    public void removeEntity(E entity)
    {
        String key = converter.key(entity);

        checkInvalidKey(key);

        removeKey(key);
        remove(key);
    }

    private void checkInvalidKey(String key)
    {
        if (TextUtils.equals(key, indexKey))
        {
            throw new InvalidKeyException(String.format("The key '%s' cannot be used for an entity", key));
        }
    }

    // ====================================== PREFERENCES ======================================= \\

    private boolean contains(String key)
    {
        return preferences.contains(key);
    }

    private String getString(String key, String defaultValue)
    {
        return preferences.getString(key, defaultValue);
    }

    private void putString(String key, String value)
    {
        preferences.edit().putString(key, value).apply();
    }

    private Set<String> getStringSet(String key)
    {
        return preferences.getStringSet(key, new HashSet<String>());
    }

    private void putStringSet(String key, Set<String> value)
    {
        preferences.edit().putStringSet(key, value).apply();
    }

    private void remove(String key)
    {
        preferences.edit().remove(key).apply();
    }

    public void clear()
    {
        preferences.edit().clear().apply();
    }
}