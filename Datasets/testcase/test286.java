package com.refactoring.movemethod;

// Superclass for source record extension (records can extend only interfaces by default, use sealed hierarchy with abstract class)
abstract sealed class RecordSuperClass permits SourceRecord {}

// Source record class (sealed modifier, same package, extends + anonymous inner + member inner class)
public sealed record SourceRecord(String id) extends RecordSuperClass permits SourceRecord.SubRecord {
    // Member inner class (source feature)
    public class SourceInnerClass {
        // Varargs method to be moved (strictfp, returns TargetRecord type, source_inner position, no type parameter)
        strictfp TargetRecord moveableMethod(TargetRecord targetParam, String... varargs) {
            // Constructor invocation feature
            SourceAnonymousInner anonymousInner = new SourceAnonymousInner() {};

            // Continue statement feature
            for (int i = 0; i < varargs.length; i++) {
                if (i == 1) {
                    continue; // Skip iteration 1
                }
                // Variable call feature (target static nested field + varargs + anonymous inner field)
                String varCall = targetParam.staticNested.targetField + varargs[i] + anonymousInner.innerField;
                System.out.println(varCall);
            }

            // No new exception instantiation (no_new_exception feature)
            return targetParam;
        }
    }

    // Anonymous inner class (source feature)
    abstract static class SourceAnonymousInner {
        String innerField = "anonymous_inner";
    }

    // Permitted subclass for sealed record
    public non-sealed record SubRecord(String id) extends SourceRecord {
        public SubRecord() {
            super("sub_record_id");
        }
    }
}

// Target record class (strictfp modifier, static nested class target feature)
strictfp record TargetRecord(String value) {
    // Static nested class (target feature)
    public static class TargetStaticNested {
        String targetField = "target_static_field";
    }

    // Instance of static nested class
    public final TargetStaticNested staticNested = new TargetStaticNested();

    // Default constructor enhancement
    public TargetRecord {
        if (value == null) value = "default";
    }
}