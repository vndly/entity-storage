package com.mauriciotogneri.entitystorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EntityStorage
{
    private final String indexKey;
    private final SharedPreferences preferences;

    public EntityStorage(Context context, String name, String indexKey)
    {
        this.indexKey = indexKey;
        this.preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    // =============== INDEX =============== \\

    public Set<String> index()
    {
        return getStringSet(indexKey);
    }

    public void clearIndex()
    {
        for (String key : index())
        {
            removeEntity(key);
        }

        putStringSet(indexKey, new HashSet<String>());
    }

    // =============== ENTITIES =============== \\

    public String entity(String key)
    {
        checkInvalidKey(key);

        return getString(key, "");
    }

    public List<String> entities()
    {
        List<String> entities = new ArrayList<>();

        for (String key : index())
        {
            entities.add(entity(key));
        }

        return entities;
    }

    public void entity(String key, String entity)
    {
        checkInvalidKey(key);

        putString(key, entity);
    }

    public void removeEntity(String key)
    {
        checkInvalidKey(key);

        remove(key);

        Set<String> index = index();
        index.remove(key);
        putStringSet(indexKey, index);
    }

    private void checkInvalidKey(String key)
    {
        if (TextUtils.equals(key, indexKey))
        {
            throw new RuntimeException(String.format("The key '%s' cannot be used for an entity", key));
        }
    }

    // =============== PREFERENCES =============== \\

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