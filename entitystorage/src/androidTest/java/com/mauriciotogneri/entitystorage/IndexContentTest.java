package com.mauriciotogneri.entitystorage;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class IndexContentTest extends StorageTest
{
    @Test
    public void emptyIndex()
    {
        EntityStorage storage = storage(DEFAULT_NAME, DEFAULT_INDEX);


        assertEquals(storage.index().size(), 0);
        assertTrue(storage.isEmpty());
    }
}