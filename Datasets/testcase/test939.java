package refactoring.test;

import java.io.IOException;

// Source class: record, default modifier, same package as target, features: member inner class, local inner class
record SourceRecord(String id, int value) {
    // Member inner class (source_class feature)
    class SourceMemberInner {
        int innerMethod(int num) {
            return num * 10;
        }
    }

    // Method to be refactored: instance, return base type (int), strictfp access, position source
    // Per condition: method contains target class parameter
    strictfp int moveMethod(TargetRecord targetParam) throws IOException {
        // Variable call feature
        int localVar = 1;
        
        // SwitchExpression feature (numbers: 1, modifier: public, exp: SwitchExpression)
        int switchResult = switch (localVar) {
            case 1 -> 10;
            default -> 0;
        };

        // Local inner class (source_class feature)
        class SourceLocalInner {
            int calculate(int val) {
                return val + switchResult;
            }
        }
        SourceLocalInner localInner = new SourceLocalInner();
        localVar = localInner.calculate(localVar);

        // Requires_throws feature (declares throws IOException)
        if (targetParam == null) {
            throw new IOException("Target parameter cannot be null");
        }

        // Array initialization with call_method (pos: array initialization)
        int[] arr = { SourceRecord.this.new SourceMemberInner().innerMethod(localVar) };
        // Call private source method (call_method: type source, modifier private, return_type int)
        localVar += privateSourceMethod(arr[0]);

        // Use target parameter's inner class
        localVar += targetParam.innerClass().process(localVar);
        return localVar;
    }

    // call_method: type source, modifier private, target_feature: instance, OuterClass.InnerClass.methodName()
    private int privateSourceMethod(int num) {
        SourceMemberInner inner = new SourceMemberInner();
        return inner.innerMethod(num);
    }
}

// Target class: record, protected modifier, same package, target_feature: member inner class
protected record TargetRecord(String data, long timestamp) {
    // Member inner class (target_feature)
    class TargetMemberInner {
        int process(int value) {
            return value * 2;
        }
    }

    public TargetMemberInner innerClass() {
        return new TargetMemberInner();
    }
}