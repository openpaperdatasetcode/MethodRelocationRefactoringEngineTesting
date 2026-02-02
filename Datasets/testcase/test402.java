// Source package (different from target package)
package sourcepkg;

import targetpkg.TargetClass;
import java.util.ArrayList;
import java.util.List;

// Source class: normal, public modifier, different package, member inner + local inner classes
public class SourceClass {
    // Member inner class (source feature)
    public class SourceInnerClass {
        String innerData;

        // Super constructor invocation in inner class
        public SourceInnerClass() {
            super(); // super constructor invocation
        }

        public SourceInnerClass(String data) {
            this(data, "default"); // this(arguments)
        }

        public SourceInnerClass(String data, String suffix) {
            this.innerData = data + "_" + suffix;
        }
    }

    // Instance method to be refactored (all specified features)
    List<String> refactorMethod(TargetClass<String> targetParam) { // per_condition + instance type + List<String> return
        // Raw type usage
        TargetClass rawTarget = targetParam;
        rawTarget.setValue("raw_type_value");

        // Variable call (target class field)
        targetParam.setValue("refactor_value");
        targetParam.counter = 1;
        // Access target static nested class
        TargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested();
        targetParam.setNestedValue(staticNested.nestedValue);

        // EmptyStatement: protected modifier, source pos, super.field + 1
        protected void emptyLogic() {
            ; // EmptyStatement
            targetParam.superField = 1; // super.field + 1 (target_feature)
        }
        emptyLogic();

        // Super constructor invocation (in anonymous inner class)
        Runnable anonymous = new Runnable() {
            {
                super(); // super constructor invocation
            }
            @Override
            public void run() {
                targetParam.counter++;
            }
        };
        anonymous.run();

        // This(arguments) (inner class constructor)
        SourceInnerClass inner = new SourceInnerClass("source", "1"); // 1 for target_feature

        // While statement
        int i = 0;
        List<String> result = new ArrayList<>();
        while (i < 1) { // 1 for target_feature
            result.add(targetParam.getValue());
            result.add(inner.innerData);
            i++;
        }

        // Local inner class (source feature)
        class SourceLocalInner {
            void updateTarget(TargetClass<String> target) {
                target.setValue("local_inner_updated");
                result.add(target.getValue());
            }
        }
        new SourceLocalInner().updateTarget(targetParam);

        // No new exception, return List<String>
        return result;
    }
}

// Target package (different from source package)
package targetpkg;

// Parent class for target's super.field feature
class TargetParentClass {
    protected int superField = 0;
}

// Target class: normal, public modifier, type parameter + static nested class features
public class TargetClass<T> extends TargetParentClass { // type parameter (target_feature)
    private T value;
    int counter;
    int nestedValue;

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        int nestedValue = 1; // For super.field + 1

        public int getNestedValue() {
            return nestedValue;
        }
    }

    // Accessor methods for variable call
    public T getValue() { return value; }
    public void setValue(T value) { this.value = value; }
    public void setNestedValue(int nestedValue) { this.nestedValue = nestedValue; }

    // No new exception guarantee
    public void validate() {
        if (superField != 1) throw new IllegalStateException("Super field must be 1");
    }
}