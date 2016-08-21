package com.mauriciotogneri.entitystorage;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import java.util.UUID;

public class StorageTest
{
    protected static final String DEFAULT_NAME = "name";
    protected static final String DEFAULT_INDEX = "index";

    protected EntityStorage storage(String name, String index)
    {
        Context context = InstrumentationRegistry.getContext();

        return new EntityStorage(context, name, index);
    }

    protected static class TestEntity
    {
        public final String id;

        private TestEntity(String id)
        {
            this.id = id;
        }

        public static TestEntity entity()
        {
            return new TestEntity(UUID.randomUUID().toString());
        }
    }
}