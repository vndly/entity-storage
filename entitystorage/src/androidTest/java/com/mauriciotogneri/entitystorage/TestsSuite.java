package com.mauriciotogneri.entitystorage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        EmptyIndexTest.class,
        IndexContentTest.class,
        RetrieveEntitiesTest.class
})
public class TestsSuite
{
}