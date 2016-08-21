package com.mauriciotogneri.entitystorage;

import android.support.test.runner.AndroidJUnit4;

import com.mauriciotogneri.entitystorage.exceptions.EntityNotFoundException;
import com.mauriciotogneri.entitystorage.exceptions.InvalidKeyException;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class EntityTest extends StorageTest
{
    @After
    public void setUp()
    {
        EntityStorage<TestEntity> storage = storage(DEFAULT_NAME, DEFAULT_INDEX);
        storage.clear();
    }

    @Test
    public void retrieveSingleEntity()
    {
        TestEntity initialEntity = TestEntity.withKey("123");

        EntityStorage<TestEntity> storage = storage(DEFAULT_NAME, DEFAULT_INDEX);
        storage.addEntity(initialEntity);

        TestEntity retrievedEntity = storage.entity("123");

        assertEquals(initialEntity.content, retrievedEntity.content);
        assertEquals(1, storage.size());
        assertFalse(storage.isEmpty());
    }

    @Test
    public void retrieveSingleEntityAsList()
    {
        TestEntity initialEntity = TestEntity.withKey("123");

        EntityStorage<TestEntity> storage = storage(DEFAULT_NAME, DEFAULT_INDEX);
        storage.addEntity(initialEntity);

        assertEquals(1, storage.size());
        assertFalse(storage.isEmpty());

        List<TestEntity> retrievedEntities = storage.entities();

        TestEntity retrievedEntity = retrievedEntities.get(0);
        assertEquals(initialEntity.content, retrievedEntity.content);
    }

    @Test
    public void retrieveMultipleEntities()
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
            assertEquals(storage.entity(String.valueOf(i)).key, entities.get(i).key);
            assertEquals(storage.entity(String.valueOf(i)).content, entities.get(i).content);
        }
    }

    @Test
    public void retrieveMultipleEntitiesAsList()
    {
        EntityStorage<TestEntity> storage = storage(DEFAULT_NAME, DEFAULT_INDEX);

        List<TestEntity> initialEntities = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            TestEntity testEntity = TestEntity.withKey(String.valueOf(i));
            initialEntities.add(testEntity);
            storage.addEntity(testEntity);
        }

        List<TestEntity> retrievedEntities = storage.entities();

        assertEquals(initialEntities.size(), retrievedEntities.size());
        assertEquals(initialEntities.size(), storage.size());

        for (int i = 0; i < 10; i++)
        {
            assertEquals(storage.entity(String.valueOf(i)).key, initialEntities.get(i).key);
            assertEquals(storage.entity(String.valueOf(i)).content, initialEntities.get(i).content);
        }
    }

    @Test(expected = InvalidKeyException.class)
    public void invalidEntityKey()
    {
        EntityStorage<TestEntity> storage = storage(DEFAULT_NAME, DEFAULT_INDEX);
        storage.addEntity(TestEntity.withKey(DEFAULT_INDEX));
    }

    @Test(expected = InvalidKeyException.class)
    public void invalidEntityNullKey()
    {
        EntityStorage<TestEntity> storage = storage(DEFAULT_NAME, DEFAULT_INDEX);
        storage.addEntity(TestEntity.withKey(null));
    }

    @Test(expected = EntityNotFoundException.class)
    public void entityNotFound()
    {
        TestEntity initialEntity = TestEntity.withKey("123");

        EntityStorage<TestEntity> storage = storage(DEFAULT_NAME, DEFAULT_INDEX);
        storage.addEntity(initialEntity);

        storage.entity("345");
    }
}