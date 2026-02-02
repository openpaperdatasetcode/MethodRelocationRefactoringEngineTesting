import java.util.ArrayList;
import java.util.List;

protected class SourceClass {
    // Field referencing target class to satisfy per_condition
    private TargetClass targetField = new TargetClass();

    // Static method to be moved, meeting all method features
    private static List<String> methodToMove() throws IllegalArgumentException {
        SourceClass outerThis = SourceClass.this; // uses_outer_this
        List<String> result = new ArrayList<>();
        String variable = "test"; // variable call
        
        for (int i = 0; i < 5; i++) { // for statement
            if (variable == null) {
                throw new IllegalArgumentException("Variable is null"); // requires_throws
            }
            result.add(variable + i);
        }
        return result;
    }

    // First local inner class
    class LocalInnerClass1 {
        public void doSomething() {
            SourceClass.this.methodToMove();
        }
    }

    // Second local inner class
    class LocalInnerClass2 {
        public List<String> callMethod() throws IllegalArgumentException {
            return SourceClass.methodToMove();
        }
    }
}

public class TargetClass {
    // Anonymous inner class (target_feature)
    public void targetMethod() {
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class execution");
            }
        };
        anonymousInner.run();
    }

    // Target inner recursive structure for method relocation
    public static class target_inner_rec {
        // Placeholder for moved method
        private static List<String> methodToMove() throws IllegalArgumentException {
            return SourceClass.methodToMove();
        }
    }
}