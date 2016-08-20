package com.mauriciotogneri.entitystorage;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class StorageTest
{
    private static final String STORAGE_PERSON = "person";
    private static final String DEFAULT_INDEX = "index";

    @Test
    public void emptyIndex() throws Exception
    {
        EntityStorage storage = storage(STORAGE_PERSON, DEFAULT_INDEX);

        assertEquals(storage.index().size(), 0);
    }

    private EntityStorage storage(String name, String index)
    {
        Context context = InstrumentationRegistry.getContext();

        return new EntityStorage(context, name, index);
    }
}