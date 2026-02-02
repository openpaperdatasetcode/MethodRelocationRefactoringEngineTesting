// Different package for source class
package com.source;

import com.target.TargetClass;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public abstract class SourceClass<T> {
    public void outerMethod() {
        // Local inner class (source feature)
        class SourceInnerClass {
            // Varargs, strictfp, instance, return Object, source_inner_rec position
            strictfp Object moveCandidateMethod(TargetClass<T>... targetParams) {
                // Abstract method feature (Lambda expressions pos, superTypeReference.methodName)
                Runnable abstractRunnable = () -> {
                    TargetClass.superTypeMethod(1); // 1, target, abstract, superTypeReference
                };

                // Do statement
                int count = 0;
                do {
                    // Variable call
                    for (TargetClass<T> target : targetParams) {
                        target.process(count);
                    }
                    count++;
                } while (count < 5);

                // Requires try-catch
                try {
                    abstractRunnable.run();
                    if (targetParams.length == 0) throw new IOException("No target params");
                } catch (IOException e) {
                    return null;
                }

                return targetParams[0];
            }
        }

        // Anonymous inner class (source feature)
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                new SourceInnerClass().moveCandidateMethod(new TargetClass<>());
            }
        };
        anonymousInner.run();
    }

    // Abstract method (matches abstract type in method features)
    public abstract void abstractMethod();
}

// Subclass in source package for call_method
package com.source;

import com.target.TargetClass;
import java.util.List;
import java.util.ArrayList;

class SourceSubClass extends SourceClass<String> {
    // Final, sub_class type, static, return List<String>, ternary operators pos
    public final static List<String> callMethod(boolean flag) {
        // Ternary operator + (parameters) -> methodBody
        return flag ? 
            ((param) -> {
                SourceClass<String> source = new SourceSubClass();
                source.outerMethod();
                List<String> list = new ArrayList<>();
                list.add(param);
                return list;
            }).apply("success") : 
            new ArrayList<>();
    }

    @Override
    public void abstractMethod() {}
}

// Target class package (different from source)
package com.target;

import java.util.List;

// Abstract class, default modifier, type parameter, local inner class
abstract class TargetClass<U> {
    // Super type reference method
    public static void superTypeMethod(int num) {}

    public void process(int val) {
        // Local inner class (target feature)
        class TargetLocalInner {
            U processValue(U input) {
                return input;
            }
        }
        new TargetLocalInner();
    }
}