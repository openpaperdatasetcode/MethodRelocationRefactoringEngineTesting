package refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorStaticAnnotation {}

// Target inner record (target_inner_rec - target class of method)
public record TargetInnerRec(String enumName, int value, boolean flag) {}

// Target enum class: default modifier, local inner class (target_feature)
enum TargetEnum {
    TARGET_1(1), TARGET_2(2);

    public final int value;
    // Source contains this field (per_condition)
    public static TargetEnum sourceTargetField = TARGET_1;

    TargetEnum(int value) {
        this.value = value;
    }

    // Local inner class (target_feature)
    public TargetInnerRec createInnerRec(boolean flag) {
        class LocalInnerClass {
            private TargetInnerRec buildRec() {
                return new TargetInnerRec(name(), value, flag);
            }
        }
        return new LocalInnerClass().buildRec();
    }

    // Method for override_violation (invalid override signature)
    public void process(boolean flag) throws NullPointerException {}
}

// Super class for super keywords feature
class EnumSuperClass {
    protected static boolean superFlag = true;
}

// Source enum class: public modifier, anonymous inner + member inner class (source_feature)
public enum SourceEnum extends EnumSuperClass {
    SOURCE_1, SOURCE_2;

    // Anonymous inner class (source_feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Source anonymous inner class: " + TargetEnum.sourceTargetField);
        }
    };

    // Member inner class (source_feature)
    public class SourceMemberInner {
        public TargetInnerRec modifyRec(TargetInnerRec rec) {
            return new TargetInnerRec(rec.enumName() + "_modified", rec.value(), rec.flag());
        }
    }

    // Static method: public access, TargetInnerRec return type (TargetClas Type), method_position=source
    @RefactorStaticAnnotation // has_annotation feature
    public static TargetInnerRec refactorMethod() {
        // BooleanLiteral feature: numbers=1, public modifier, exp=BooleanLiteral
        public boolean booleanLiteral = true; // BooleanLiteral (1 represents true/false literal)

        // Super keywords feature
        boolean superFlag = EnumSuperClass.superFlag;

        // Variable call feature (source contains target field)
        TargetEnum targetVar = TargetEnum.sourceTargetField;
        int varCall = targetVar.value;

        // NullPointerException feature (handled to ensure no_new_exception)
        TargetInnerRec innerRec = null;
        try {
            innerRec = targetVar.createInnerRec(booleanLiteral);
            if (innerRec == null) {
                throw new NullPointerException("TargetInnerRec is null");
            }
        } catch (NullPointerException e) {
            // No new exception thrown feature
            innerRec = new TargetInnerRec("default", 0, false);
        }

        // Used_by_reflection feature
        try {
            Method createRecMethod = TargetEnum.class.getMethod("createInnerRec", boolean.class);
            TargetInnerRec reflectRec = (TargetInnerRec) createRecMethod.invoke(targetVar, booleanLiteral);
            System.out.println("Reflection result: " + reflectRec.enumName());
        } catch (Exception e) {
            // No new exception thrown feature
            System.err.println("Reflection handled: " + e.getMessage());
        }

        // Override_violation feature (invalid override signature - missing throws)
        TargetEnum invalidOverride = new TargetEnum(0) { // Note: Enum can't be instantiated directly, simulate violation
            @Override
            public void process(boolean flag) { // override_violation (missing throws NullPointerException)
                superFlag = flag;
            }
        };

        // Execute anonymous inner class
        SourceEnum.SOURCE_1.anonymousInner.run();

        // Use member inner class
        SourceMemberInner inner = SourceEnum.SOURCE_1.new SourceMemberInner();
        TargetInnerRec modifiedRec = inner.modifyRec(innerRec);

        // No new exception thrown feature
        return modifiedRec;
    }
}