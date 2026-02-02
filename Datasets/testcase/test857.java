package refactoring.test;

import com.others.OtherPackageClass;

// Source class: normal, protected modifier, same package as target, features: extends, local inner class, anonymous inner class
protected class SourceClass extends BaseSourceClass {
    // Static method to be refactored: static, base type return (int), protected access, position source
    // Per condition: method contains target class parameter
    protected static int moveMethod(TargetClass<String> targetParam) throws IllegalArgumentException {
        // Variable call feature
        int localVar = 3;
        OtherPackageClass otherObj = new OtherPackageClass();

        // SwitchStatement feature (type: SwitchStatement, modifier: public, target_feature: obj.field, 3, pos: diff_package_others)
        switch (otherObj.field) {
            case 3:
                localVar += targetParam.innerClass().processData("switch-case-3");
                break;
            default:
                localVar = 0;
                break;
        }

        // Assert statement feature
        assert localVar > 0 : "Local variable must be greater than 0";

        // Expression statement feature
        String exprStmt = "Processed value: " + localVar;
        System.out.println(exprStmt);

        // Throw statement & requires_throws feature
        if (targetParam == null) {
            throw new IllegalArgumentException("Target parameter cannot be null");
        }

        // Local inner class (source_class feature)
        class SourceLocalInner {
            int calculate(int val) {
                return val * 2;
            }
        }
        SourceLocalInner localInner = new SourceLocalInner();
        localVar = localInner.calculate(localVar);

        // Anonymous inner class (source_class feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class execution");
            }
        };
        anonymousRunnable.run();

        // Override violation (method with same signature as parent but incompatible return type)
        // Parent class has non-static int moveMethod(TargetClass), this static method causes override violation
        return localVar;
    }
}

// Base class for source class extends feature
class BaseSourceClass {
    public int moveMethod(TargetClass<String> targetParam) {
        return 0;
    }
}

// Target class: normal, public modifier, same package, target_feature: type parameter, member inner class
public class TargetClass<T> {
    // Member inner class (target_feature)
    class TargetMemberInnerClass {
        String processData(String data) {
            return data + "-processed";
        }
    }

    public TargetMemberInnerClass innerClass() {
        return new TargetMemberInnerClass();
    }
}

// Different package class for SwitchStatement pos: diff_package_others
package com.others;

public class OtherPackageClass {
    public int field = 3; // target_feature: obj.field, 3
}