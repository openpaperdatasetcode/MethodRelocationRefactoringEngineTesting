// Target class package (different from source)
package com.target;

import java.util.List;
import java.util.ArrayList;

// Target generic class: default modifier, static nested class (target_feature)
class TargetClass<T> {
    public T data;

    // Static nested class (target_feature)
    public static class TargetStaticNested<T> {
        public T nestedData;

        public TargetStaticNested(T nestedData) {
            this.nestedData = nestedData;
        }
    }

    public TargetClass(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}

// Source class package (different from target)
package com.source;

import com.target.TargetClass;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

// Sealed interface with permits for source_class feature
sealed interface GenericSealedInterface<T> permits SourceClass<T> {}

// Source generic class: default modifier, permits, anonymous inner + member inner class (source_feature)
final class SourceClass<T> implements GenericSealedInterface<T> {
    // Anonymous inner class (source_feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Source anonymous inner class");
        }
    };

    // Member inner class (source_feature)
    public class SourceMemberInner<T> {
        // Inner recursive class (source_inner_rec - method_position)
        public class SourceInnerRecursive {
            /**
             * Method Javadoc (method feature)
             * Instance method with TargetClass return type, protected access
             * @param targetParam Target class parameter (per_condition)
             * @return TargetClass<T> instance
             */
            protected TargetClass<T> refactorMethod(TargetClass<T> targetParam) {
                // Type declaration statement (method feature)
                List rawList; // Raw type feature
                rawList = new ArrayList(); // Raw type initialization

                // Variable call feature
                T varCall = targetParam.getData();

                // For statement (method feature)
                for (int i = 0; i < 3; i++) {
                    rawList.add(varCall + "_" + i);
                }

                // Used by reflection (method feature) + requires_try_catch feature
                try {
                    Method getDataMethod = TargetClass.class.getMethod("getData");
                    T reflectResult = (T) getDataMethod.invoke(targetParam);
                    rawList.add(reflectResult);
                } catch (Exception e) {
                    // Requires try-catch handling
                    rawList.add("reflection_error");
                }

                // Execute anonymous inner class
                anonymousInner.run();

                // Return TargetClass Type
                return new TargetClass<>(varCall);
            }
        }
    }
}