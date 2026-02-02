import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {
    String value() default "moveMethod";
}

final class SourceClass {
    @RefactorAnnotation("targetMethod")
    public final List<String> moveCandidateMethod(TargetClass targetParam) {
        // Variable call
        String targetField = targetParam.outerField;
        // Raw type usage
        List rawList = new ArrayList();
        rawList.add(targetField);
        
        // Override violation: attempt to override final method (simulated)
        TargetClass.MemberInnerClass innerInstance = targetParam.new MemberInnerClass() {
            @Override
            public final String getValue() { // Compile error (override violation) - intentional for test
                return super.getValue() + "_modified";
            }
        };
        
        List<String> result = new ArrayList<>();
        result.addAll(rawList);
        result.add(innerInstance.getValue());
        
        // No new exception thrown
        try {
            targetParam.innerMethod();
        } catch (RuntimeException e) {
            // Silent handling, no new exception
        }
        
        return result;
    }
}

private class TargetClass {
    String outerField = "targetValue";

    // Member inner class
    class MemberInnerClass {
        public final String getValue() {
            return outerField;
        }
    }

    void innerMethod() {
        // Dummy method for variable call/exception handling
    }
}