import java.io.IOException;

final class SourceClass {
    protected String outerProtectedField = "protectedValue";

    // Static nested class (source feature)
    static class StaticNestedClass {
        static void helperMethod(SourceClass source, TargetClass target) {
            System.out.println(source.outerProtectedField + target.targetField);
        }
    }

    // Generic method, strictfp, instance, return void, source position
    strictfp <T> void moveCandidateMethod(TargetClass targetParam) {
        // Local inner class (source feature)
        class LocalInnerClass {
            void processTarget(TargetClass target) {
                // Access outer protected field
                target.targetField = SourceClass.this.outerProtectedField;
            }
        }

        LocalInnerClass inner = new LocalInnerClass();
        // Variable call
        inner.processTarget(targetParam);
        
        // IOException handling
        try {
            if (targetParam.targetField == null) {
                throw new IOException("Target field is null");
            }
            // Super keywords (simulated for normal class, use Object super)
            super.toString();
        } catch (IOException e) {
            // No new exception thrown, handle existing exception
            targetParam.handleException(e);
        }
    }
}

public class TargetClass {
    String targetField;

    void handleException(IOException e) {
        // Local inner class (target feature)
        class TargetLocalInner {
            void logException(IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        new TargetLocalInner().logException(e);
    }
}