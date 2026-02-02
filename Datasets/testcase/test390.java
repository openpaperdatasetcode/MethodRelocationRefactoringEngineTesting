package refactoring.test;

import diffpkg.OthersClass;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

// Parent class for source class extends feature
class SourceParentClass {
    protected <T> TargetClass<T> superMethod(TargetClass<T> target) {
        return target;
    }
}

// Source class: public normal class, type parameter + extends + double member inner classes
public class SourceClass<T extends CharSequence> extends SourceParentClass { // type parameter + extends
    // First member inner class
    public class SourceInnerOne {
        // Recursive inner class (method_position: source_inner_rec)
        public class SourceInnerRecursive {
            // Normal method to be refactored (all specified features)
            private Object refactorMethod(TargetClass<String> targetParam, List<String> listParam) { // per_condition + method types parameter is:List
                // Variable call (target class field)
                targetParam.value = "source_value";
                targetParam.counter = 1;
                // Access target inner class
                TargetClass<String>.TargetInner inner = targetParam.new TargetInner();
                inner.innerValue = "inner_1";

                // BreakStatement: public modifier, diff_package_others pos, ClassName.field + 1
                public void breakLogic() {
                    OthersClass others = new OthersClass();
                    for (int i = 0; i < 5; i++) {
                        if (i == 1) {
                            OthersClass.staticField = 1; // ClassName.field + 1 (target_feature)
                            break; // BreakStatement
                        }
                        listParam.add("break_" + i);
                    }
                }
                breakLogic(); // diff_package_others pos

                // Continue statement
                for (int j = 0; j < 3; j++) {
                    if (j == 1) {
                        continue; // Continue statement
                    }
                    targetParam.counter += j;
                }

                // Instance method: protected, if/else pos, 1/target/instance/super.methodName()
                protected TargetClass<String> instanceMethod(TargetClass<String> target) {
                    if (target.counter == 1) { // if/else conditions pos
                        target.value = "if_branch";
                        // super.methodName() (method_feature)
                        return super.superMethod(target);
                    } else {
                        target.value = "else_branch";
                        return target;
                    }
                }
                TargetClass<String> instanceResult = instanceMethod(targetParam); // 1/target/instance

                // Used by reflection
                try {
                    Method targetMethod = TargetClass.class.getMethod("getInnerValue");
                    Object reflectResult = targetMethod.invoke(inner);
                    listParam.add(reflectResult.toString());
                } catch (Exception e) {
                    // No new exception
                    return e;
                }

                // No new exception, return Object
                return instanceResult;
            }
        }
    }

    // Second member inner class (duplicate feature)
    public class SourceInnerTwo {
        String data;
    }
}

// Different package class for diff_package_others pos
package diffpkg;
import refactoring.test.TargetClass;

public class OthersClass {
    public static int staticField = 1; // For ClassName.field target_feature
}

// Back to test package
package refactoring.test;

// Parent class for target class extends feature
strictfp class TargetParentClass<U> {
    protected U parentValue;
}

// Target class: strictfp normal class, type parameter + extends + member inner class
strictfp class TargetClass<V extends CharSequence> extends TargetParentClass<V> { // type parameter + extends
    V value;
    int counter;

    // Member inner class (target_feature)
    public class TargetInner {
        String innerValue;

        public String getInnerValue() {
            return innerValue;
        }
    }

    // Constructor
    public TargetClass(V value) {
        this.value = value;
        this.parentValue = value; // Use parent class field
    }
}