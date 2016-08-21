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
        EntityStorage<TestEntity> storage = storage(DEFAULT_NAME, DEFAULT_INDEX);

        assertEquals(0, storage.size());
        assertTrue(storage.isEmpty());
    }

    @Test
    public void emptyIndexAfterRemovingAllEntities()
    {
        EntityStorage<TestEntity> storage = storage(DEFAULT_NAME, DEFAULT_INDEX);

        List<TestEntity> entities = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            TestEntity testEntity = TestEntity.withKey(String.valueOf(i));
            entities.add(testEntity);
            storage.addEntity(testEntity);
        }

        for (int i = 0; i < 10; i++)
        {
            TestEntity testEntity = entities.get(i);
            storage.removeEntity(testEntity);
        }

        assertEquals(0, storage.size());
        assertTrue(storage.isEmpty());
    }

    @Test
    public void emptyIndexAfterClearingIndex()
    {
        EntityStorage<TestEntity> storage = storage(DEFAULT_NAME, DEFAULT_INDEX);

        for (int i = 0; i < 10; i++)
        {
            storage.addEntity(TestEntity.withKey(String.valueOf(i)));
        }

        storage.clear();

        assertEquals(0, storage.size());
        assertTrue(storage.isEmpty());
    }

    @Test
    public void emptyIndexAfterClear()
    {
        EntityStorage<TestEntity> storage = storage(DEFAULT_NAME, DEFAULT_INDEX);

        for (int i = 0; i < 10; i++)
        {
            TestEntity testEntity = TestEntity.withKey(String.valueOf(i));
            storage.addEntity(testEntity);
        }

        storage.clear();

        assertEquals(0, storage.size());
        assertTrue(storage.isEmpty());
    }
}