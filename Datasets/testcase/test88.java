package com.refactoring.movemethod;

import java.util.Arrays;
import java.util.List;

// Others class for call_method feature
class OthersClass {
    // Call_method: protected modifier, accessor, ClassName::methodName, return void
    protected static void accessorMethod(String param) {
        System.out.println(param);
    }
}

// Super class for source record extends feature (records can extend interfaces, use abstract class for compatibility)
abstract class SuperRecordClass {
    protected String superField = "superValue";
}

// Source class: record class, private modifier, same package, extends, static nested class, member inner class
private record SourceRecord(String sourceField, TargetRecord targetField) extends SuperRecordClass { // per_condition: contains target field
    // Static field for depends_on_static_field feature
    private static String staticSourceField = "staticInit";

    // Static nested class (source feature)
    static class StaticNestedSource {
        static int staticVal = 1;
    }

    // Member inner class (source feature)
    class MemberInnerSource {
        String innerField = SourceRecord.this.sourceField; // uses_outer_this
    }

    // Compact constructor using this(arguments) feature
    public SourceRecord {
        // this(arguments) - validate and initialize
        if (sourceField == null) {
            this.sourceField = "defaultSource";
        }
        if (targetField == null) {
            this.targetField = new TargetRecord("defaultTarget");
        }
    }

    // Method: varargs, return TargetRecord Type, default access, source position
    TargetRecord moveableVarargsMethod(String... args) {
        // Variable call feature
        String localVar = targetField.targetData();
        localVar = SourceRecord.this.sourceField; // uses_outer_this feature

        // depends_on_static_field feature
        staticSourceField = localVar + "_updatedStatic";
        localVar = staticSourceField;

        // assert statement feature
        assert args.length <= 3 : "Too many arguments";
        assert targetField != null : "Target field is null";

        // expression statement feature
        localVar = localVar.concat(Arrays.toString(args));
        staticSourceField = localVar.toUpperCase();

        // requires_try_catch feature
        try {
            int parsed = Integer.parseInt(args.length > 0 ? args[0] : "0");
            localVar = String.valueOf(parsed);
        } catch (NumberFormatException e) {
            localVar = "0";
        }

        // override_violation feature (attempt to override final method)
        class OverrideViolationClass extends TargetRecord {
            // Compiler error: final method cannot be overridden
            @Override
            public final String targetData() { return "override"; }
        }

        // call_method: others_class, pos=for, accessor, ClassName::methodName, return void
        List<String> callList = Arrays.asList(args);
        for (String s : callList) { // pos=for
            OthersClass::accessorMethod; // ClassName::methodName (accessor feature)
            OthersClass.accessorMethod(s);
        }

        // Return updated target record (TargetClass Type)
        return new TargetRecord(localVar);
    }
}

// Target class: record class, private modifier, same package, target_feature: anonymous inner class
private record TargetRecord(String targetData) {
    // Anonymous inner class (target_feature)
    void useAnonymousClass() {
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(targetData);
            }
        };
        anonymousRunnable.run();
    }

    // Final method for override_violation feature
    @Override
    public final String targetData() {
        return targetData;
    }
}