package refactoring.test;

// Sealed interface with permits for source_class feature
sealed interface SealedInterface permits SourceClass {}

// Target class: default modifier, member inner class (target_feature)
class TargetClass {
    // Target inner record (target_inner_rec - target class of method)
    public record TargetInnerRec(String id, int value) {}

    // Member inner class (target_feature)
    public class TargetInner {
        private TargetInnerRec innerRec;

        public TargetInner(TargetInnerRec innerRec) {
            this.innerRec = innerRec;
        }

        public TargetInnerRec getInnerRec() {
            return innerRec;
        }
    }
}

// Source abstract class: abstract modifier, permits, static nested + anonymous inner class (source_feature)
public abstract class SourceClass implements SealedInterface {
    // Source contains target field (per_condition)
    private TargetClass.TargetInnerRec targetInnerRecField = new TargetClass.TargetInnerRec("REC_001", 1);
    
    // Private outer field for access_outer_private feature
    private String outerPrivateField = "outerPrivateValue";
    
    // Static field for depends_on_static_field feature
    private static int staticField = 2;

    // Static nested class (source_feature)
    public static class SourceStaticNested {
        public static TargetClass.TargetInnerRec createInnerRec(String id, int value) {
            return new TargetClass.TargetInnerRec(id, value + staticField);
        }
    }

    // Anonymous inner class (source_feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Source anonymous inner class: " + outerPrivateField);
        }
    };

    // Instance method: strictfp access, Object return type, method_position=source
    public strictfp Object refactorMethod() {
        // Super constructor invocation (implicit for Object, explicit for inner class)
        TargetClass target = new TargetClass();
        TargetClass.TargetInner inner = target.new TargetInner(targetInnerRecField);

        // Variable call feature
        TargetClass.TargetInnerRec varCall = inner.getInnerRec();

        // Access outer private feature
        String outerPrivate = this.outerPrivateField;

        // Depends on static field feature
        TargetClass.TargetInnerRec staticDepRec = SourceStaticNested.createInnerRec(varCall.id(), varCall.value());

        // Execute anonymous inner class
        anonymousInner.run();

        // No new exception thrown feature
        return new Object() {
            @Override
            public String toString() {
                return varCall.id() + "_" + varCall.value() + "_" + outerPrivate + "_" + staticDepRec.value();
            }
        };
    }
}