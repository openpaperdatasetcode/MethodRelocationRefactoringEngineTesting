package com.refactoring.movemethod;

// Abstract super class for abstract method feature
abstract class AbstractSuperClass {
    // Abstract method with ArrayCreation exp, numbers=1, abstract modifier
    protected abstract void abstractArrayCreation(int val);
}

// Source record class (protected modifier, same package, member inner + anonymous inner class)
protected record SourceRecord(String sourceField) extends AbstractSuperClass {
    // Target field reference (per_condition: source contains target field)
    private TargetRecord targetFieldRef;

    // Member inner class (source feature)
    class SourceMemberInner {
        // Source inner record (method_position: source_inner_rec)
        record SourceInnerRec(String data) {
            // Varargs method (default access, TargetClass Type return)
            TargetRecord processTarget(TargetRecord... params) {
                // Variable call (target parameter access)
                if (params == null || params.length == 0) {
                    targetFieldRef = new TargetRecord("default");
                    return targetFieldRef;
                }
                targetFieldRef = params[0];

                // SynchronizedStatement (public modifier, this.field, 3, pos: source)
                publicSynchronizedStatement(targetFieldRef);

                // Array initialization (pos for overloading method)
                TargetRecord[] targetArray = new TargetRecord[2]; // 2 for method_feature
                targetArray[0] = targetFieldRef;
                // Overloading method call
                privateOverloadingMethod(targetArray, 2);

                // Abstract ArrayCreation (numbers=1)
                abstractArrayCreation(1);

                // No_new_exception (empty try-catch)
                try {
                    // return this; (adjusted for record inner rec)
                    return this.returnThis(targetFieldRef);
                } catch (Exception e) {
                    return targetFieldRef;
                }
            }

            // Public SynchronizedStatement method
            public void publicSynchronizedStatement(TargetRecord target) {
                Object lock = new Object();
                synchronized (lock) {
                    // this.field (inner rec's field) + 3
                    String innerField = this.data() + "_" + 3;
                    target.setAuxField(innerField);
                }
            }

            // Private overloading method (array initialization pos, void return)
            private void privateOverloadingMethod(TargetRecord[] targets, int val) {
                // Overloading: define overloaded method
                privateOverloadingMethod(targets);
                // 2: literal value, target feature
                if (val == 2) {
                    for (TargetRecord target : targets) {
                        // new ClassName().method()
                        new TargetRecord.TargetStaticNested().process(target);
                    }
                }
            }

            // Overloaded method (overloading feature)
            private void privateOverloadingMethod(TargetRecord[] targets) {
                // Empty implementation for overloading
            }

            // Helper for return this; feature
            private TargetRecord returnThis(TargetRecord target) {
                return target; // Simulate return this for target type
            }
        }
    }

    // Anonymous inner class (source feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            SourceMemberInner inner = new SourceMemberInner();
            SourceInnerRec rec = inner.new SourceInnerRec("test");
            rec.processTarget(new TargetRecord("target"));
        }
    };

    // Implement abstract method (ArrayCreation exp, numbers=1)
    @Override
    protected void abstractArrayCreation(int val) {
        // ArrayCreation with 1
        String[] array = new String[1];
        array[0] = String.valueOf(val);
    }
}

// Target record class (default modifier, static nested class feature)
record TargetRecord(String targetField) {
    // Static nested class (target_feature)
    static class TargetStaticNested {
        public void process(TargetRecord target) {
            // Empty implementation for new ClassName().method()
        }
    }

    // Aux field for this.field feature
    private String auxField;

    public void setAuxField(String auxField) {
        this.auxField = auxField;
    }

    public String getAuxField() {
        return auxField;
    }
}