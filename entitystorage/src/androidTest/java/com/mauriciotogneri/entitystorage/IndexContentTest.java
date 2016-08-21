package com.mauriciotogneri.entitystorage;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class IndexContentTest extends StorageTest
{
    @Test
    public void indexContentWithOneEntity()
    {
        EntityStorage<TestEntity> storage = storage(DEFAULT_NAME, DEFAULT_INDEX);
        storage.addEntity(TestEntity.randomEntity());

        assertEquals(1, storage.index().size());
    }

    @Test
    public void indexContentWithSeveralEntity()
    {
        EntityStorage<TestEntity> storage = storage(DEFAULT_NAME, DEFAULT_INDEX);

        List<TestEntity> entities = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            TestEntity testEntity = TestEntity.withKey(String.valueOf(i));
            entities.add(testEntity);
            storage.addEntity(testEntity);
        }

        assertEquals(storage.index().size(), entities.size());

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