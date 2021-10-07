package acar.utils;

import sun.reflect.ReflectionFactory;

import java.lang.reflect.Constructor;

import java.lang.reflect.Field;

import java.lang.reflect.Modifier;



public class ReflectionUtils {



    public static <T> T n(Class<T> clazz) {
        return create(clazz, Object.class); // replace Object.class with immediate non-serializable parent with no-args ctor
    }


    public static <T> T create(Class<T> clazz, Class<? super T> parent) {
        try {
            ReflectionFactory rf = ReflectionFactory.getReflectionFactory();
            Constructor objDef = parent.getDeclaredConstructor();
            Constructor intConstr = rf.newConstructorForSerialization(clazz, objDef);
            return clazz.cast(intConstr.newInstance());
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Cannot create object", e);
        }

    }


    public static void setFieldValue(Object obj, Object fieldVal, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        if (obj == null)
            return;
        String className = obj.getClass().toString();
        if (className.contains("java.lang"))
            return;
        if (obj instanceof java.lang.Class)
            return;
        Field field = getField(obj.getClass(), fieldName);
        if (field == null) {
            throw new NoSuchFieldException(obj.getClass() + ": "+fieldName);
        }

        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL & ~Modifier.TRANSIENT);
        modifiersField.setAccessible(true);

        field.set(obj, fieldVal);
    }

    public static Object getFieldValue(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        if (obj == null)
            return null;
        String className = obj.getClass().toString();
        if (className.contains("java.lang"))
            return null;
        if (obj instanceof java.lang.Class)
            return null;
        Field field = getField(obj.getClass(), fieldName);
        if (field == null) {
            throw new NoSuchFieldException(obj.getClass() + ": "+fieldName);
        }

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        field.setAccessible(true);
        modifiersField.setAccessible(true);

        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL & ~Modifier.TRANSIENT & ~Modifier.PRIVATE);

       return  field.get(obj);
    }


    public static Field getField(Class clazz, String fieldName) {
        while (clazz != Object.class) {
            try {
                Field f =  clazz.getDeclaredField(fieldName);
                return f;
            } catch (NoSuchFieldException e) {
                // keep looking
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }





}
