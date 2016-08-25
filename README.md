[![Build Status](https://travis-ci.org/mauriciotogneri/entity-storage.svg?branch=master)](https://travis-ci.org/mauriciotogneri/entity-storage)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/mauriciotogneri/entity-storage/blob/master/LICENSE.txt)
[![Download](https://api.bintray.com/packages/mauriciotogneri/maven/entitystorage/images/download.svg)](https://bintray.com/mauriciotogneri/maven/entitystorage/_latestVersion)

# Entity Storage
**Entity Storage** is an index based Android library that stores entities in shared preferences. The library requires at minimum Android 4.0.

## Example

Given the following entity and converter:

```java
public static class TestEntity
{
    public final String key;
    public final String content;

    public TestEntity(String key, String content)
    {
        this.key = key;
        this.content = content;
    }

    public static TestEntity withKey(String key)
    {
        return new TestEntity(key, UUID.randomUUID().toString());
    }
}

public class TestEntityConverter implements Converter<TestEntity>
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
```

We can perform the following operations with the storage:

```java
EntityStorage<TestEntity> storage = new EntityStorage<>(context, "storage_name", "index_name", new TestEntityConverter());

storage.addEntity(new TestEntity("123", "content 1"));
storage.addEntity(new TestEntity("456", "content 2"));
storage.addEntity(new TestEntity("789", "content 3"));

TestEntity testEntity = storage.entity("123");
storage.removeEntity(testEntity);

List<TestEntity> testEntities = storage.entities();

Set<String> index = storage.index();

int count = storage.size();

if (storage.isEmpty())
{
    // code
}
else if (storage.exists("456"))
{
    // code
}

storage.clear();
```

## Installation
Add the following dependency to your `build.gradle` file:

```groovy
compile 'com.mauriciotogneri:entitystorage:1.0.0'
```