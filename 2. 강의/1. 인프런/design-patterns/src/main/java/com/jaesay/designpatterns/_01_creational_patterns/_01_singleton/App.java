package com.jaesay.designpatterns._01_creational_patterns._01_singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 싱글톤 패턴 구현을 깨뜨리는 방법
 */
public class App {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        reflection();
        deserialize();
    }

    private static void reflection() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Settings4 instance = Settings4.getInstance();
        Constructor<Settings4> constructor = Settings4.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Settings4 instance1 = constructor.newInstance();

        System.out.println(instance == instance1);
    }

    private static void deserialize() throws IOException, ClassNotFoundException {
//        Settings5 instance = Settings5.INSTANCE;
        Settings4 instance = Settings4.getInstance();

//        Settings5 instance1 = null;
        Settings4 instance1 = null;
        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("settings.obj"))) {
            out.writeObject(instance);
        }

        try (ObjectInput in = new ObjectInputStream(new FileInputStream("settings.obj"))) {
            instance1 = (Settings4) in.readObject();
//            instance1 = (Settings5) in.readObject();
        }

        System.out.println(instance == instance1);
    }
}
