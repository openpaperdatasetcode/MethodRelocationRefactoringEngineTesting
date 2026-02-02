package com.refactor;

import java.util.ArrayList;
import java.util.List;

// Source abstract class: abstract modifier, same package as target, local inner + member inner class
abstract class SourceClass {
    // Static field for depends_on_static_field feature
    private static String staticField = "static_value";

    // Member inner class feature
    protected class MemberInnerClass {
        public String processInner(TargetClass.TargetInnerRec inner) {
            return inner.getValue() + "_processed";
        }
    }

    // Method: instance, return List<String>, private access, in source class
    private List<String> processTarget(TargetClass.TargetInnerRec targetParam) {
        // Type declaration statement
        MemberInnerClass innerClass;
        List<String> result = new ArrayList<>();
        
        // Variable call (target parameter)
        TargetClass.TargetInnerRec innerRec = targetParam;
        
        // Uses_outer_this feature
        innerRec.setValue(SourceClass.this.toString() + staticField);
        
        // Depends on static field
        result.add(staticField);
        
        // Try statement + no_new_exception
        try {
            if (innerRec.getValue() == null) {
                throw new IllegalArgumentException();
            }
            // Call member inner class method
            innerClass = new MemberInnerClass();
            result.add(innerClass.processInner(innerRec));
            
            // Local inner class feature (in method)
            class LocalInnerClass {
                public void addValue(List<String> list) {
                    list.add(innerRec.getValue());
                }
            }
            new LocalInnerClass().addValue(result);
            
            // Return statement
            return result;
        } catch (Exception e) {
            // No new exception - wrap existing
            throw new RuntimeException(e);
        }
    }

    // Abstract method for sub_class overriding
    protected abstract String callMethod();
}

// Sub class for call_method (sub_class type)
class SubClass extends SourceClass {
    // Static code block (pos for call_method)
    static {
        SubClass sub = new SubClass();
        TargetClass.TargetInnerRec inner = new TargetClass().new TargetInnerRec();
        // call_method pos: Static code blocks + OuterClass.InnerClass.methodName()
        String value = sub.new MemberInnerClass().processInner(inner);
        sub.callMethod();
    }

    // call_method: protected modifier, overriding + OuterClass.InnerClass.methodName(), pos: Static code blocks, returns String
    @Override
    protected String callMethod() {
        TargetClass.TargetInnerRec inner = new TargetClass().new TargetInnerRec();
        // OuterClass.InnerClass.methodName() feature
        String result = new SourceClass.MemberInnerClass().processInner(inner);
        super.processTarget(inner);
        return result;
    }
}

// Target abstract class: private modifier, static nested class feature
private abstract class TargetClass {
    // Static nested class (target_inner_rec)
    public static class TargetInnerRec {
        private String value;
        
        public String getValue() {
            return value;
        }
        
        public void setValue(String value) {
            this.value = value;
        }
    }

    // Abstract method for target class
    public abstract void targetAbstractMethod();
}