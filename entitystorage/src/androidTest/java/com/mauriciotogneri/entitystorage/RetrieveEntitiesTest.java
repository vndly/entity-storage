package com.mauriciotogneri.entitystorage;

import android.support.test.runner.AndroidJUnit4;

import com.mauriciotogneri.entitystorage.exceptions.EntityNotFoundException;
import com.mauriciotogneri.entitystorage.exceptions.InvalidKeyException;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class RetrieveEntitiesTest extends StorageTest
{
    @Test
    public void retrieveSingleEntity()
    {
        TestEntity initialEntity = TestEntity.withKey("123");

        EntityStorage<TestEntity> storage = storage(DEFAULT_NAME, DEFAULT_INDEX);
        storage.addEntity(initialEntity);

        TestEntity retrievedEntity = storage.entity("123");

        assertEquals(initialEntity.content, retrievedEntity.content);
        assertEquals(1, storage.index().size());
        assertFalse(storage.isEmpty());
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

    @Test(expected = InvalidKeyException.class)
    public void invalidEntityKey()
    {
        EntityStorage<TestEntity> storage = storage(DEFAULT_NAME, DEFAULT_INDEX);
        storage.addEntity(TestEntity.withKey(DEFAULT_INDEX));
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