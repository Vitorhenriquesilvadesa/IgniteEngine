package org.ignite.core.app;

import org.ignite.annotations.Define;
import org.ignite.system.meta.Meta;
import test.Sandbox;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.ignite.system.log.Logger;

/**
 * Class for call all "init" methods in application package to initialize all
 * tools for run engine
 */
@Define("IGNITE_API")
public class EntryPoint {

    public static Logger ClientLog = new Logger("ClientLog", 0);

    public static void main(String[] args) {
        Application.init();

        //new EntryPoint();
        Application.setInstance(Sandbox.class);
        Application.getInstance().run();
    }

    public EntryPoint() {

        List<Class<?>> classes;
        try {
            classes = getClassesInPackage("org.ignite.core.app");

            for (Class<?> clazz : classes) {
                if (Meta.check(clazz.getName().replace("org.", ""), "Define",
                        "IGNITE_API")) {
                    try {
                        Object instance = clazz.getDeclaredConstructor().newInstance();
                        Method initMethod = clazz.getDeclaredMethod("init");
                        initMethod.invoke(instance);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }


    private List<Class<?>> getClassesInPackage(String packageName) throws
            ClassNotFoundException, IOException {
        String packagePath = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        List<Class<?>> classes = new ArrayList<>();

        File packageDirectory = new
                File(classLoader.getResource(packagePath).getFile());

        if (packageDirectory.exists() && packageDirectory.isDirectory()) {
            File[] files = packageDirectory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".class")) {
                        String className = packageName + '.'
                                + file.getName().substring(0, file.getName().lastIndexOf('.'));

                        Class<?> clazz = Class.forName(className);

                        classes.add(clazz);
                    }
                }
            }
        }

        return classes;
    }
}
