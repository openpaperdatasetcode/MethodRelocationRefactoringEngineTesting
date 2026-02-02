import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Source generic class: default modifier, same package, anonymous inner/member inner class
class SourceClass<T> {
    // Instance field for access_instance_field feature
    private T instanceField;

    // Member inner class (fulfills source_class feature)
    class SourceMemberInner {
        // Super field for VariableDeclarationStatement (simulate super.field)
        protected int superField = 1;

        // Instance method for ClassName::methodName feature (return void)
        private void innerInstanceMethod(TargetClass<String> param) {
            param.setTargetValue(param.getTargetValue() + "_processed_1"); // numbers:1
        }
    }

    // Anonymous inner class (fulfills source_class feature)
    private final Consumer<T> anonymousInner = new Consumer<>() {
        @Override
        public void accept(T val) {
            SourceClass.this.instanceField = val; // uses_outer_this
        }
    };

    // Target method: varargs, base type return, private access, in source class
    private int processData(TargetClass<T>... params) {
        // Fulfill per_condition: method contains target class parameter (varargs)
        if (params == null || params.length == 0) {
            return 0; // no_new_exception
        }

        // VariableDeclarationStatement (private modifier, super.field=1, pos:diff_package_others)
        // Simulate diff_package_others via cross-inner-class field reference
        private int superFieldVal = new SourceMemberInner().superField; // target_feature: super.field, numbers:1

        // Constructor invocation
        TargetClass<T> newTargetInstance = new TargetClass<>((T) "default_value");

        // Do statement
        int total = 0;
        int count = 0;
        do {
            // Variable call to target parameter
            for (TargetClass<T> param : params) {
                T targetVar = param.getTargetValue();
                total += targetVar.toString().length();

                // Access_instance_field
                this.instanceField = targetVar; // access_instance_field
                total += this.instanceField.toString().length();

                // Raw_type feature (generic list without type parameter)
                List rawList = new ArrayList(); // raw_type
                rawList.add(targetVar);

                // uses_outer_this (access outer class instance in anonymous inner)
                anonymousInner.accept(targetVar);
            }
            count++;
        } while (count < superFieldVal);

        // Instance method feature (private modifier, 1/inner_class/instance/ClassName::methodName in property assignment)
        SourceMemberInner inner = new SourceMemberInner();
        // pos: property assignment, ClassName::methodName
        Consumer<TargetClass<String>> methodRef = inner::innerInstanceMethod; // ClassName::methodName
        // Execute method reference (numbers:1, inner_class, instance)
        if (params[0] instanceof TargetClass<String>) {
            methodRef.accept((TargetClass<String>) params[0]);
        }

        // Return base type (int)
        return total + superFieldVal; // no_new_exception
    }
}

// Subclass for call_method (type: sub_class)
class SourceSubClass<T> extends SourceClass<T> {
    // Call method: default modifier, normal, new ClassName().method() in Lambda expressions
    TargetClass<T> invokeSourceMethod(TargetClass<T>... params) {
        // pos: Lambda expressions
        Consumer<TargetClass<T>[]> lambda = (p) -> {
            // new ClassName().method() (target_feature: normal + constructor call + method invoke)
            new SourceClass<T>().processData(p); // normal method call
        };

        // Execute lambda
        lambda.accept(params);

        // Return TargetClass Type
        return params.length > 0 ? params[0] : new TargetClass<>((T) "empty_result");
    }
}

// Target generic class: default modifier, local inner class (target_feature)
class TargetClass<U> {
    private U targetValue;

    public TargetClass(U targetValue) {
        this.targetValue = targetValue;
    }

    // Local inner class (fulfills target_feature)
    public void outerMethod() {
        class TargetLocalInner {
            U getProcessedValue() {
                return (U) (targetValue.toString() + "_local_inner");
            }
        }
        TargetLocalInner localInner = new TargetLocalInner();
        this.targetValue = localInner.getProcessedValue();
    }

    // Getter/Setter for variable call
    public U getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(U targetValue) {
        this.targetValue = targetValue;
    }
}