package com.refactor.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Same package others class for SynchronizedStatement pos
class SamePackageOthers {
    String field = "init";
}

// Source enum class (public modifier, same package, static nested + member inner class)
public enum SourceEnum {
    ENUM_VALUE1, ENUM_VALUE2;

    // Static field for SynchronizedStatement this.field (value 1)
    private int field = 1;

    // Static nested class (source_class feature)
    public static class SourceStaticNested {
        String nestedData = "static_nested";
    }

    // Member inner class (source_class feature)
    public class SourceMemberInner {
        String innerData = "member_inner";
        
        public String getInnerData() {
            return innerData;
        }
    }

    // Annotation for has_annotation feature
    @SuppressWarnings("unused")
    // Overloading method (final access, returns List<String>, has target param - per_condition)
    public final List<String> refactorMethod(TargetEnum targetParam) {
        List<String> result = new ArrayList<>();
        SourceMemberInner inner = new SourceMemberInner();
        SamePackageOthers syncObj = new SamePackageOthers();

        // Variable call feature
        String varCall = inner.getInnerData();
        result.add(varCall);

        // Assignment with numbers=1, protected modifier feature
        int assignedValue = 1;
        this.field = assignedValue; // Assignment exp with value 1

        // SynchronizedStatement (protected modifier, this.field=1, pos: same_package_others)
        protected synchronized (syncObj) {
            syncObj.field = String.valueOf(this.field); // this.field target_feature with value 1
            result.add(syncObj.field);
        }

        // Requires_try_catch feature
        try {
            // Overriding method (default modifier, 1 + others_class + overriding + method reference, exception handling pos)
            void overridingMethod() {
                Consumer<TargetEnum> consumer = TargetEnum::process; // instanceReference::methodName
                consumer.accept(targetParam);
                if (targetParam.ordinal() != 1) { // Number 1 feature
                    throw new IllegalArgumentException();
                }
            }
            
            overridingMethod();
            // Break statement feature
            for (int i = 0; i < 5; i++) {
                if (i == 1) {
                    break;
                }
                result.add(String.valueOf(i));
            }

            // Super keywords feature
            result.add(super.toString());
        } catch (Exception e) {
            // Exception handling statements position for overriding method
            result.add(e.getMessage());
        }

        // No_new_exception (implicit - no custom throw new)
        return result;
    }

    // Overload method (overloading feature)
    public final List<String> refactorMethod(TargetEnum targetParam, String extra) {
        List<String> result = refactorMethod(targetParam);
        result.add(extra);
        return result;
    }
}

// Target enum class (protected modifier, member inner class target_feature)
protected enum TargetEnum {
    TARGET_VALUE1, TARGET_VALUE2;

    // Member inner class (target_feature)
    public class TargetMemberInner {
        String innerValue = "target_inner";
        
        public String getInnerValue() {
            return innerValue;
        }
    }

    // Method for overriding/ method reference
    public void process() {
        TargetMemberInner inner = new TargetMemberInner();
        System.out.println(inner.getInnerValue());
    }

    // Call method (public modifier, target type, (parameters)->methodBody, property assignment pos, returns TargetClass Type)
    public TargetEnum callMethod() {
        TargetEnum result;
        // Property assignment position
        result = this;
        // (parameters) -> methodBody target_feature
        Consumer<TargetEnum> lambda = (param) -> {
            param.process();
            result = param;
        };
        lambda.accept(this);
        return result;
    }
}