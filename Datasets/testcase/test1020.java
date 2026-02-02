package refactoring.test;

import java.util.ArrayList;
import java.util.List;

// Sealed parent class for permits feature
sealed class SealedParent permits SourceClass.SourceMemberInner, SourceClass.LocalInnerPermit {}

// Source class: normal, public modifier, same package as target, features: permits, member inner class, local inner class
public class SourceClass {
    // Member inner class (source_class feature, permitted by SealedParent)
    final class SourceMemberInner extends SealedParent {
        String processData(String data) {
            return data.toUpperCase();
        }
    }

    // Local inner class for permits feature
    class LocalInnerPermit extends SealedParent {}

    // Method to be refactored: instance, return List<String>, public access, position source
    // Per condition: method contains target class parameter
    public List<String> moveMethod(TargetClass targetParam) {
        // Variable call feature
        int localVar = 10;
        List<String> resultList = new ArrayList<>();

        // Super constructor invocation (in local inner class)
        class SourceLocalInner {
            SourceLocalInner() {
                super(); // Super constructor invocation feature
            }

            int calculate(int val) {
                return val * 2;
            }
        }

        // Local inner class (source_class feature)
        SourceLocalInner localInner = new SourceLocalInner();
        // Member inner class (source_class feature)
        SourceMemberInner memberInner = new SourceMemberInner();

        // Depends on inner class feature
        localVar = localInner.calculate(localVar);
        resultList.add(memberInner.processData("value-" + localVar));

        // Uses_outer_this feature (access outer this in inner class context)
        Runnable outerThisRunnable = new Runnable() {
            @Override
            public void run() {
                resultList.add(SourceClass.this.toString() + "-outer-this");
            }
        };
        outerThisRunnable.run();

        // Assert statement feature
        assert !resultList.isEmpty() : "Result list cannot be empty";

        // Use target parameter's anonymous inner class functionality
        resultList.addAll(targetParam.getAnonymousInnerData(localVar));

        // No new exception thrown (no_new_exception feature)
        return resultList;
    }
}

// Target class: normal, default modifier, same package, target_feature: anonymous inner class
class TargetClass {
    public List<String> getAnonymousInnerData(int value) {
        // Anonymous inner class (target_feature)
        List<String> dataList = new ArrayList<>() {{
            add("target-data-1");
            add("target-value-" + value);
        }};
        return dataList;
    }
}