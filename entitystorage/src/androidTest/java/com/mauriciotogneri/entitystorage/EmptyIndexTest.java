package com.mauriciotogneri.entitystorage;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class EmptyIndexTest extends StorageTest
{
    @Test
    public void emptyIndex()
    {
        EntityStorage storage = storage(DEFAULT_NAME, DEFAULT_INDEX);

        assertEquals(storage.index().size(), 0);
        assertTrue(storage.isEmpty());
    }

    @Test
    public void emptyIndexAfterRemovingAllEntities()
    {
        EntityStorage storage = storage(DEFAULT_NAME, DEFAULT_INDEX);

        List<TestEntity> entities = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            TestEntity testEntity = TestEntity.entity();
            entities.add(testEntity);
            storage.addEntity(testEntity.id, testEntity.toString());
        }

        for (int i = 0; i < 10; i++)
        {
            TestEntity testEntity = entities.get(i);
            storage.removeEntity(testEntity.id);
        }

        assertEquals(storage.index().size(), 0);
        assertTrue(storage.isEmpty());
    }

    @Test
    public void emptyIndexAfterClear()
    {
        EntityStorage storage = storage(DEFAULT_NAME, DEFAULT_INDEX);

        for (int i = 0; i < 10; i++)
        {
            TestEntity testEntity = TestEntity.entity();
            storage.addEntity(testEntity.id, testEntity.toString());
        }

        storage.clear();

        assertEquals(storage.index().size(), 0);
        assertTrue(storage.isEmpty());
    }
}