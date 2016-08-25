package com.mauriciotogneri.entitystorage.test;

import android.support.test.runner.AndroidJUnit4;

import com.mauriciotogneri.entitystorage.EntityStorage;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class IndexTest extends StorageTest
{
    @After
    public void setUp()
    {
        EntityStorage<TestEntity> storage = storage(DEFAULT_NAME, DEFAULT_INDEX);
        storage.clear();
    }

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

    @Test
    public void indexContentWithOneEntity()
    {
        TestEntity testEntity = TestEntity.withKey("ABC");

        EntityStorage<TestEntity> storage = storage(DEFAULT_NAME, DEFAULT_INDEX);
        storage.addEntity(testEntity);

        assertEquals(1, storage.size());
        assertTrue(storage.exists("ABC"));
    }

    @Test
    public void indexContentWithMultipleEntity()
    {
        EntityStorage<TestEntity> storage = storage(DEFAULT_NAME, DEFAULT_INDEX);

        List<TestEntity> entities = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            TestEntity testEntity = TestEntity.withKey(String.valueOf(i));
            entities.add(testEntity);
            storage.addEntity(testEntity);
        }

        assertEquals(storage.size(), entities.size());

        Set<String> index = storage.index();
        int counter = 0;

        for (String key : index)
        {
            TestEntity testEntity = storage.entity(key);

            int position = counter++;
            assertEquals(testEntity.key, entities.get(position).key);
            assertEquals(testEntity.content, entities.get(position).content);
        }
    }
}