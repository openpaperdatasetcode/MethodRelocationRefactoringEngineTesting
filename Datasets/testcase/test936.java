package refactoring.test;

import java.util.ArrayList;
import java.util.List;

// Source class: generic, private modifier, same package as target, features: two static nested classes
private class SourceClass<T> {
    // Per condition: source contains target class field
    TargetClass<String> targetField = new TargetClass<>();

    // First static nested class (source_class feature)
    static class SourceStaticNested1 {
        int value = 1;

        SourceStaticNested2 getNested2() {
            return new SourceStaticNested2();
        }
    }

    // Second static nested class (source_class feature)
    static class SourceStaticNested2 {
        SourceStaticNested3 getNested3() {
            return new SourceStaticNested3();
        }
    }

    static class SourceStaticNested3 {
        String getData() {
            return "chained-data";
        }
    }

    // Inner class for call_method feature (type: inner_class, modifier: final)
    final class SourceInnerClass {
        // call_method target_feature: instance
        void innerInstanceMethod(String data) {
            System.out.println("Processed: " + data);
        }
    }

    // Abstract method to be refactored: abstract, return Object, public access, position source
    public abstract Object moveMethod();

    // Instance method (type: instance, modifier: public, return_type: List<String>, pos: switch)
    public List<String> instanceHelperMethod() {
        // method_feature: 1
        int num = 1;
        // method_feature: source (access source class field)
        TargetClass<String> sourceTarget = SourceClass.this.targetField;
        // method_feature: instance (instance method call)
        sourceTarget.innerClass().process(num);
        // method_feature: obj.m1().m2().m3()
        String chained = new SourceStaticNested1().getNested2().getNested3().getData();

        List<String> list = new ArrayList<>();
        // Switch statement (pos: switch)
        switch (num) {
            case 1:
                list.add(chained);
                break;
            default:
                list.add("default");
        }
        return list;
    }

    // Concrete implementation holder for abstract method features
    public static class SourceConcrete<T> extends SourceClass<T> {
        @Override
        public Object moveMethod() {
            // Variable call feature
            int localVar = 0;
            // Constructor invocation feature
            SourceInnerClass inner = new SourceInnerClass();
            
            // If statement feature
            if (localVar < 1) {
                localVar = 1;
                // Expression statement feature
                String exprStmt = "local: " + localVar;
                list.add(exprStmt);
            }

            // Collection operations with call_method (pos: collection operations)
            List<String> list = instanceHelperMethod();
            list.forEach(s -> {
                // call_method target_feature: new ClassName().method()
                new SourceInnerClass().innerInstanceMethod(s);
            });

            // Depends on inner class feature
            localVar += targetField.innerClass().calculate(localVar);
            // No new exception thrown (no_new_exception feature)
            return list;
        }
    }
}

// Target class: generic, strictfp modifier, same package, target_feature: member inner class
strictfp class TargetClass<T> {
    // Member inner class (target_feature)
    class TargetMemberInner {
        int calculate(int val) {
            return val * 2;
        }

        void process(int num) {
            // Empty implementation for instance method call
        }
    }

    public TargetMemberInner innerClass() {
        return new TargetMemberInner();
    }
}