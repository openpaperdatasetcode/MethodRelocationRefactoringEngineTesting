package com.refactor;

import java.io.IOException;

// Super class for source class extends feature
class SuperSourceClass {
    protected String superField = "super_value";
    public void superMethod() {}
}

// Functional interface for target class implements feature
interface TargetInterface {
    String process(String input);
}

// Source class: public normal class, same package as target, extends + local inner + static nested class
public class SourceClass extends SuperSourceClass {
    private String outerPrivateField = "private_2"; // access_outer_private + numbers=2

    // Static nested class feature
    public static class StaticNestedClass {
        public void nestedMethod(TargetClass.TargetInnerRec inner) {
            inner.setValue(outerPrivateField);
        }
    }

    // Local inner class feature (source_inner_rec hierarchy)
    public void sourceMethod() {
        class FirstLocalInner {
            // Inner recursive class for source_inner_rec method position
            class InnerRecursiveClass {
                // Method: varargs, return Object, final access, source_inner_rec position
                public final Object processTarget(TargetClass.TargetInnerRec... targetParams) throws IOException {
                    // Variable call (target parameter)
                    TargetClass.TargetInnerRec innerRec = targetParams.length > 0 ? targetParams[0] : null;
                    
                    // NullLiteral: numbers=2, protected modifier, exp=NullLiteral
                    protected Object nullLiteral = (innerRec == null) ? 2 : innerRec;
                    
                    // Labeled statement
                    label:
                    for (int i = 0; i < 2; i++) { // numbers=2
                        if (innerRec == null) {
                            // Throw statement + requires_throws
                            throw new IOException("Inner rec is null (2)");
                        }
                        // Super keywords
                        innerRec.setValue(super.superField + "_" + super.superMethod());
                        
                        // Access outer private field
                        innerRec.setValue(SourceClass.this.outerPrivateField);
                        break label;
                    }
                    
                    // call_method: target type, public modifier, normal + new ClassName().method(), pos: object initialization, returns String
                    String result = new TargetClass().targetMethod(innerRec);
                    return result;
                }
            }
            
            void callInnerRecursive(TargetClass.TargetInnerRec inner) throws IOException {
                InnerRecursiveClass innerRec = new InnerRecursiveClass();
                innerRec.processTarget(inner);
            }
        }
        
        FirstLocalInner localInner = new FirstLocalInner();
        localInner.callInnerRecursive(new TargetClass().new TargetInnerRec());
    }
}

// Target class: protected normal class, implements + member inner class features
protected class TargetClass implements TargetInterface {
    // Member inner class (target feature)
    public class TargetInnerRec {
        private String value;
        
        public String getValue() {
            return value;
        }
        
        public void setValue(String value) {
            this.value = value;
        }
    }

    // call_method: public modifier, normal + new ClassName().method(), pos: object initialization, returns String
    public String targetMethod(TargetInnerRec inner) {
        // Object initialization position
        TargetInnerRec newInner = new TargetInnerRec();
        newInner.setValue(inner.getValue() + "_processed");
        return newInner.getValue();
    }

    // Implements feature (TargetInterface)
    @Override
    public String process(String input) {
        return input;
    }
}