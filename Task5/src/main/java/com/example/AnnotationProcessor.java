
package com.example;

import java.lang.reflect.Method;

public class AnnotationProcessor {

    public static void printStatistics(Object obj) {

        for(Method m : obj.getClass().getMethods()) {

            if(m.isAnnotationPresent(Statistic.class)) {

                Statistic stat = m.getAnnotation(Statistic.class);

                System.out.println("Statistic method: " + stat.name());
            }
        }
    }
}
