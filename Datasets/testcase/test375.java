package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Different package for IfStatement pos (diff_package_target)
package com.refactoring.target.other;
public class IfHelper {
    public static int field = 1; // target_feature: ClassName.field, 1
}

// Back to main package
package com.refactoring.movemethod;
import com.refactoring.target.other.IfHelper;

// Super class for source class (extends feature)
class SourceSuperClass {
    protected int superValue = 5;
}

// Source normal class (strictfp modifier, same package, extends + static nested + anonymous inner class)
strictfp class SourceClass extends SourceSuperClass {
    // Field of target class (satisfies per_condition: source contains target field)
    private TargetClass targetField;

    // Static nested class (source class feature)
    public static class SourceStaticNested {
        public int nestedValue = 10;

        public int getNestedValue() {
            return nestedValue;
        }
    }

    // Instance method to refactor (private, return List<String>, source position)
    private List<String> refactorMethod(TargetClass.TargetInner targetParam) {
        List<String> result = new ArrayList<>();
        
        // Variable call feature
        SourceStaticNested staticNested = new SourceStaticNested();
        int localVar = staticNested.getNestedValue() + superValue;
        
        // Expression statement feature
        localVar += 1; // target_feature: 1
        result.add(String.valueOf(localVar));
        
        // IfStatement (public modifier, diff_package_target pos, ClassName.field + 1)
        if (IfHelper.field == 1) { // ClassName.field, target_feature 1 (public modifier IfStatement)
            result.add("if_condition_met");
        }

        // Throw statement feature
        try {
            if (targetParam == null) {
                throw new IllegalArgumentException("Target parameter cannot be null"); // throw statement
            }
        } catch (IllegalArgumentException e) {
            // No new exception feature (no throw new Exception)
            result.add("error_handled: " + e.getMessage());
            return result;
        }

        // Anonymous inner class (source class feature)
        Runnable anonRunnable = new Runnable() {
            @Override
            public void run() {
                result.add(targetParam.getInnerData());
            }
        };
        anonRunnable.run();

        // Process target parameter (per_condition: method has target parameter)
        this.targetField = new TargetClass();
        result.addAll(targetParam.processData(localVar));
        
        return result;
    }

    // Constructor to initialize target field
    public SourceClass() {
        this.targetField = new TargetClass();
    }
}

// Target normal class (protected modifier, local inner class feature)
protected class TargetClass {
    // Target inner class (target_inner)
    public class TargetInner {
        private String innerData = "target_inner_data";

        public List<String> processData(int input) {
            List<String> dataList = new ArrayList<>();
            
            // Local inner class (target_feature)
            class TargetLocalInner {
                public String formatData(String data, int num) {
                    return data + "_" + num;
                }
            }
            TargetLocalInner localInner = new TargetLocalInner();
            dataList.add(localInner.formatData(innerData, input));
            
            return dataList;
        }

        public String getInnerData() {
            return innerData;
        }
    }

    // Constructor to initialize inner class
    public TargetClass() {
        TargetInner inner = new TargetInner();
    }
}