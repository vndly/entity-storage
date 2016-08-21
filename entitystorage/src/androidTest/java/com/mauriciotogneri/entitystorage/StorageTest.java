package com.mauriciotogneri.entitystorage;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import java.util.UUID;

public class StorageTest
{
    protected static final String DEFAULT_NAME = "name";
    protected static final String DEFAULT_INDEX = "index";

    protected EntityStorage<TestEntity> storage(String name, String index)
    {
        Context context = InstrumentationRegistry.getContext();

        return new EntityStorage<>(context, name, index, new TestEntityConverter());
    }

    private class TestEntityConverter implements Converter<TestEntity>
    {

        @Override
        public String key(TestEntity entity)
        {
            return entity.key;
        }

        @Override
        public String content(TestEntity entity)
        {
            return entity.content;
        }

        @Override
        public TestEntity create(String key, String content)
        {
            return new TestEntity(key, content);
        }
    }

    protected static class TestEntity
    {
        public final String key;
        public final String content;

        private TestEntity(String key, String content)
        {
            this.key = key;
            this.content = content;
        }

        public static TestEntity withKey(String key)
        {
            return new TestEntity(key, UUID.randomUUID().toString());
        }
    }
}