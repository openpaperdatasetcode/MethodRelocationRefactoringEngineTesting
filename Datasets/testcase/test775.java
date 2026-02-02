package com.refactoring.movemethod;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Supplier;

// Others class for constructor method_feature: others_class
class OthersClass {
    private String data;
    public OthersClass(String data) {
        this.data = data; // method_feature: "1" (single param)
    }
    public String getData() {
        return this.data;
    }
}

// Source class: normal, protected modifier, same package, type parameter + two static nested classes
protected abstract class SourceClass<T> {
    // Static field for depends_on_static_field feature
    private static final String STATIC_FIELD = "static_6123";

    // First static nested class (source feature)
    protected static class FirstStaticNested {
        private String nestedField1 = "nested1_6123";
    }

    // Second static nested class (source feature)
    protected static class SecondStaticNested {
        private int nestedField2 = 1; // method_feature: "1"
    }

    // Abstract method to be refactored: abstract type, Object return, protected access, source position
    protected abstract Object methodToMove(TargetClass<T>.TargetInnerRecursive targetParam) throws IOException;

    // Concrete implementation holder (for constructor & lambda features)
    protected class SourceConcrete extends SourceClass<T> {
        // uses_outer_this feature
        private final SourceClass<T> outerThis = SourceClass.this;

        @Override
        protected Object methodToMove(TargetClass<T>.TargetInnerRecursive targetParam) throws IOException {
            // per_condition: method contains target inner recursive class parameter
            if (targetParam == null) {
                throw new IOException("Target inner recursive parameter is null"); // IOException feature
            }

            // super constructor invocation
            super();

            // super keywords feature
            String superStr = super.toString();

            // type declaration statement feature
            FirstStaticNested staticNested = new FirstStaticNested();
            List<String> strList = new ArrayList<>();

            // variable call feature
            String varCall = staticNested.nestedField1 + STATIC_FIELD; // depends_on_static_field

            // used_by_reflection feature (twice as per spec)
            try {
                Method method = SourceConcrete.class.getDeclaredMethod("methodToMove", TargetClass.TargetInnerRecursive.class);
                method.setAccessible(true);
                Constructor<OthersClass> constructor = OthersClass.class.getConstructor(String.class);
                constructor.newInstance(varCall);
            } catch (ReflectiveOperationException e) {
                // no_new_exception (no explicit new Exception instantiation)
                e.printStackTrace();
            }

            // Constructor feature (type: constructor, modifier: public, pos: Lambda expressions, return List<String>)
            public List<String> constructorLogic() {
                // Lambda expressions position
                Supplier<OthersClass> supplier = () -> {
                    // method_feature: instanceReference.methodName(arguments)
                    String data = targetParam.innerInstanceMethod(1); // method_feature: "1"
                    // method_feature: others_class + constructor
                    return new OthersClass(data);
                };
                OthersClass others = supplier.get();
                strList.add(others.getData());
                return strList; // return_type: List<String>
            }
            constructorLogic();

            return varCall + superStr + targetParam.innerField + outerThis.STATIC_FIELD;
        }
    }
}

// Functional interface for target class implements feature
interface TargetInterface<T> {
    T process(T data);
}

// Target class: normal, public modifier, implements + member inner class (target_features)
public class TargetClass<T> implements TargetInterface<T> {
    // Member inner recursive class (target_inner_rec)
    public class TargetInnerRecursive {
        public String innerField = "target_inner_rec_6123";

        public String innerInstanceMethod(int val) {
            return innerField + "_" + val;
        }
    }

    @Override
    public T process(T data) {
        return data;
    }
}