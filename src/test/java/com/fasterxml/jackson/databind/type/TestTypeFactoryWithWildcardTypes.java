package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.BaseMapTest;
import com.fasterxml.jackson.databind.JavaType;

import java.lang.reflect.Field;

public class TestTypeFactoryWithWildcardTypes
    extends BaseMapTest
{
    static class Generic<T> {}
    static class Foo {}

    static class TestBean {
        public Generic<Foo> fieldNormal;
        public Generic<? extends Foo> fieldExtend;
        public Generic<? super Foo> fieldSuper;
    }

    public void testNormal() throws Exception
    {
        final TypeFactory tf = TypeFactory.defaultInstance();
        Field field = TestBean.class.getDeclaredField("fieldNormal");
        JavaType type = tf.constructType(field.getGenericType());

        assertEquals(Generic.class, type.getRawClass());
        assertEquals(1, type.containedTypeCount());
        assertEquals(tf.constructType(Foo.class), type.containedType(0));
    }

    public void testExtend() throws Exception
    {
        final TypeFactory tf = TypeFactory.defaultInstance();
        Field field = TestBean.class.getDeclaredField("fieldExtend");
        JavaType type = tf.constructType(field.getGenericType());

        assertEquals(Generic.class, type.getRawClass());
        assertEquals(1, type.containedTypeCount());
        assertEquals(tf.constructType(Foo.class), type.containedType(0));
    }

    public void testSuper() throws Exception
    {
        final TypeFactory tf = TypeFactory.defaultInstance();
        Field field = TestBean.class.getDeclaredField("fieldSuper");
        JavaType type = tf.constructType(field.getGenericType());

        assertEquals(Generic.class, type.getRawClass());
        assertEquals(1, type.containedTypeCount());
        assertEquals(tf.constructType(Foo.class), type.containedType(0));
    }
}
