// Target class package (different from source)
package com.refactoring.target;

import java.util.List;
import java.util.ArrayList;

// Parent class for target class extends feature
class TargetParentClass {
    protected int parentField = 3; // For DoStatement obj.field + 3
}

// Target class (normal class, public modifier, extends + member inner class)
public class TargetClass extends TargetParentClass {
    String targetField; // For per_condition and variable call
    int value = 3; // For lambda () -> System.out.println(this.value)

    // Member inner class (target_feature)
    public class TargetMemberInner {
        List<String> processField(String input) {
            List<String> result = new ArrayList<>();
            result.add(input + "_processed");
            return result;
        }
    }

    // Method to be overridden (for overriding feature)
    protected List<String> overrideMethod(TargetClass target) {
        List<String> result = new ArrayList<>();
        result.add(target.targetField);
        return result;
    }
}

// Subclass for overriding method_feature "sub_class"
package com.refactoring.target;

import java.util.List;
import java.util.ArrayList;

public class TargetSubClass extends TargetClass {
    @Override
    protected List<String> overrideMethod(TargetClass target) {
        List<String> result = new ArrayList<>();
        result.add(target.targetField + "_subclass_" + 3); // 3 from method_feature
        return result;
    }
}

// Source class package
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import com.refactoring.target.TargetSubClass;
import java.util.List;
import java.util.ArrayList;

// Interface for source class implements feature
interface SourceInterface {
    void interfaceMethod();
}

// Source class (normal class, final modifier, different package, implements + member inner + static nested class)
public final class SourceClass implements SourceInterface {
    // Static nested class (source_feature)
    static class SourceStaticNested {
        static List<String> helperMethod(String input) {
            List<String> result = new ArrayList<>();
            result.add(input + "_static");
            return result;
        }
    }

    // Member inner class (source_feature)
    class SourceMemberInner {
        // Inner recursive class (source_inner_rec)
        class SourceInnerRecursive {
            // DoStatement (public modifier, obj.field, 3, pos=source)
            public void doStatement(TargetClass target) {
                int count = 0;
                do {
                    target.parentField = 3; // obj.field (target.parentField) + 3 from target_feature
                    target.targetField = "value_" + count + "_" + target.parentField;
                    count++;
                } while (count < 3);
            }

            // Overriding method (protected modifier, method_feature:3/sub_class/overriding/this.methodName(arguments), pos:object initialization, return List<String>)
            @Override
            protected List<String> overrideMethod(TargetClass target) {
                // Object initialization position
                TargetSubClass subTarget = new TargetSubClass();
                return this.overrideMethod(subTarget); // this.methodName(arguments) + sub_class feature
            }

            // Method to be refactored (instance, List<String> return, protected access, source_inner_rec)
            protected List<String> moveMethod(TargetClass targetParam) {
                // Per_condition: contains target parameter
                if (targetParam == null) {
                    return new ArrayList<>();
                }

                // DoStatement invocation (pos=source)
                doStatement(targetParam);

                // Type declaration statement
                TargetClass.TargetMemberInner targetInner = targetParam.new TargetMemberInner();
                SourceStaticNested staticNested = new SourceStaticNested();

                // While statement
                int i = 0;
                while (i < 3) {
                    targetParam.targetField += "_while_" + i;
                    i++;
                }

                // Expression statement
                targetParam.targetField = targetParam.targetField.toUpperCase();

                // Lambda expression: () -> System.out.println(this.value)
                Runnable lambda = () -> System.out.println(targetParam.value);
                lambda.run();

                // Variable call
                String varCall = targetParam.targetField; // Access target field (per_condition)

                // Overriding method call (pos=object initialization)
                List<String> overrideResult = overrideMethod(targetParam);

                // Prepare result
                List<String> result = new ArrayList<>();
                result.add(varCall);
                result.addAll(overrideResult);
                result.addAll(targetInner.processField(varCall));
                result.addAll(SourceStaticNested.helperMethod(varCall));

                // No new exception
                return result;
            }
        }
    }

    // Implements interface method (required for implements feature)
    @Override
    public void interfaceMethod() {}
}