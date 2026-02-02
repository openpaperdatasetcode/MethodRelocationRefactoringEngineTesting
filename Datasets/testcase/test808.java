package com.refactoring.movemethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface Refactor6149Annotation {}

// Parent class for source extends feature
class SourceParent {
    protected int parentField = 1; // method_feature: "1"
    protected String parentMethod() {
        return "source_parent_6149";
    }
}

// Source abstract class: protected modifier, same package, extends + member inner + static nested classes
protected abstract class SourceClass<T extends Number> extends SourceParent {
    // per_condition: source contains field of target
    protected TargetClass targetField = new TargetClassImpl();

    // Static nested class (source feature)
    private static class SourceStaticNested {
        private String nestedField = "static_nested_6149";
        
        // Overloaded method for method_feature: overloading
        public Object innerMethod(int val) { // method_feature: "1" (val param)
            return val * 6149;
        }
        
        public Object innerMethod(String val) { // overloading feature
            return val + "_processed";
        }
    }

    // Member inner class (source feature)
    protected class SourceMemberInner {
        private String innerField = "member_inner_6149";
        
        public int processInt(T val) { // with_bounds feature (T extends Number)
            return val.intValue() + SourceClass.this.parentField;
        }
    }

    // Method to be refactored: instance, base type (int) return, default access, source position
    @Refactor6149Annotation // has_annotation feature
    int methodToMove() {
        // super constructor invocation (implicit in extends, explicit call for clarity)
        super();

        // super keywords feature
        String superStr = super.parentMethod();

        // variable call feature
        SourceStaticNested staticNested = new SourceStaticNested();
        SourceMemberInner memberInner = new SourceMemberInner();
        String varCall = staticNested.nestedField + memberInner.innerField + superStr;

        // with_bounds feature (type bound constraint)
        T numVal = (T) Integer.valueOf(1); // method_feature: "1"
        int boundedVal = memberInner.processInt(numVal);

        // Overloading method (type: overloading, modifier: public, pos: constructor parameter list, return Object)
        public Object overloadingMethod() {
            // pos: the parameter list of constructors
            class ConstructorParamClass {
                public ConstructorParamClass() {
                    // method_feature: OuterClass.InnerClass.methodName()
                    Object intResult = SourceStaticNested.class.newInstance().innerMethod(1); // method_feature: "1" + inner_class
                    // method_feature: overloading
                    Object strResult = SourceStaticNested.class.newInstance().innerMethod(varCall);
                }
            }
            new ConstructorParamClass();
            return boundedVal; // return_type: Object
        }
        overloadingMethod();

        // this.methodName(arguments) feature
        int thisCallResult = this.innerHelperMethod(varCall.length());

        // Access target inner class (target_inner)
        TargetClass.TargetInner targetInner = targetField.new TargetInner();
        targetInner.setData(varCall);

        // no_new_exception (no explicit new Exception instantiation)
        return boundedVal + thisCallResult + targetInner.getData().length();
    }

    // Helper method for this.methodName(arguments) feature
    private int innerHelperMethod(int val) {
        return val * parentField; // method_feature: "1"
    }
}

// Target abstract class: public modifier, anonymous inner class (target_feature)
public abstract class TargetClass {
    // Target inner class (target_inner)
    public class TargetInner {
        private String data;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            // anonymous inner class (target_feature)
            Runnable anonymousProcessor = new Runnable() {
                @Override
                public void run() {
                    TargetInner.this.data = data + "_target_6149";
                }
            };
            anonymousProcessor.run();
        }
    }

    public abstract TargetInner new TargetInner();
}

// Concrete implementation of abstract target class
class TargetClassImpl extends TargetClass {
    @Override
    public TargetInner new TargetInner() {
        return new TargetInner();
    }
}