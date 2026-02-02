package refactoring.test;

import java.util.ArrayList;
import java.util.List;

// Source class: normal, protected modifier, same package as target, no additional features
protected class SourceClass {
    // Per condition: source contains target class field
    TargetClass targetField = new TargetClass();

    // Method to be refactored: normal, return TargetClass type, private access, position source
    private TargetClass moveMethod() {
        // Variable call feature
        int localVar = 2;
        // With_bounds feature (type parameter with bounds)
        class BoundedClass<T extends Number & Comparable<T>> {
            T process(T value) {
                return value;
            }
        }
        BoundedClass<Integer> boundedObj = new BoundedClass<>();
        localVar = boundedObj.process(localVar);

        // Switch case feature
        switch (localVar) {
            case 2:
                // ConditionalExpression feature (numbers: 2, modifier: private, exp: ConditionalExpression)
                int condVal = (localVar > 0) ? 2 : 0;
                localVar += condVal;
                break;
            default:
                continue; // Continue statement feature (within loop context)
        }

        // Loop with continue statement
        for (int i = 0; i < 5; i++) {
            if (i == localVar) {
                continue;
            }
            localVar += i;
        }

        // Access instance method feature
        String instanceData = targetField.getInstanceData();
        // Collection operations with inner class method (call_method pos)
        List<String> list = new ArrayList<>();
        list.forEach((parameters) -> list.add(instanceData)); // (parameters) -> methodBody feature

        // No new exception thrown (no_new_exception feature)
        targetField.setData(instanceData + "-processed-" + localVar);
        return targetField;
    }

    // Overload method (overload_exist feature)
    private TargetClass moveMethod(String input) {
        targetField.setData(input);
        return targetField;
    }

    // Inner class for call_method feature (type: inner_class, modifier: protected)
    protected class SourceInnerClass {
        // Static method (call_method target_feature: static)
        protected static Object innerStaticMethod(String data) {
            return data.toUpperCase();
        }
    }
}

// Target class: normal, public modifier, same package, target_feature: extends, anonymous inner class
public class TargetClass extends BaseTargetClass {
    private String data;

    // Anonymous inner class (target_feature)
    Runnable anonymousRunnable = new Runnable() {
        @Override
        public void run() {
            System.out.println(data);
        }
    };

    // Instance method for access_instance_method feature
    public String getInstanceData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

// Base class for target class extends feature
class BaseTargetClass {}