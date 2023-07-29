/**
 * Ignite Engine - A powerful game engine in Java.
 *
 * @license MIT License
 *
 * @author Creator: Lord Vtko
 * @version 1.0.0
 * @since 2023-07-28
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

package org.ignite.system.meta;

import java.lang.annotation.Annotation;

/**
 * The `Meta` class provides a utility method to check for the presence of a
 * specific annotation with a given parameter value in a class.
 */
public final class Meta {

    /**
     * Checks if a specific annotation with a given parameter value exists in a
     * class.
     *
     * @param className  The name of the class to check for the annotation.
     * @param annotation The name of the annotation to look for.
     * @param param      The parameter value to compare with the annotation's
     *                   parameter value.
     * @return True if the annotation with the specified parameter value exists in
     *         the class; otherwise, false.
     */
    public static boolean check(String className, String annotation, String param) {

        try {
            Class<?> checker = Class.forName("com.vtko." + className);

            Annotation[] annotations = checker.getDeclaredAnnotations();

            for (Annotation a : annotations) {

                String annotationName = a.toString().replace("@com.vtko.meta.", "");

                if (annotationName.startsWith(annotation)) {

                    String paramValue = (String) a.annotationType().getMethod("value").invoke(a);

                    if (paramValue.equals(param)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            // Print stack trace for any exceptions that occur during the process.
            e.printStackTrace();
        }

        return false;
    }
}
