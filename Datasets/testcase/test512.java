import java.util.ArrayList;
import java.util.List;

public abstract class SourceClass {
    // Static nested class
    public static class StaticNestedClass {}
    
    // Member inner class
    public class MemberInnerClass {
        // Private static method to be moved (source_inner position)
        private static <T> List<String> methodToMove(TargetClass targetParam) {
            List<String> result = new ArrayList<>();
            int count = 0;
            // Do statement
            do {
                // Type declaration statement
                String temp = SourceClass.this.toString();
                // 3 abstract infix expressions (using placeholder abstract method calls)
                boolean exp1 = isAbstractExp(targetParam) && count < 5;
                boolean exp2 = isAnotherAbstractExp(targetParam) || count > 0;
                boolean exp3 = isThirdAbstractExp(targetParam) ^ (count == 2);
                // Super keyword (in context of inner class)
                result.add(super.toString() + temp);
                // Variable call
                count++;
            } while (count < 3);
            return result;
        }
        
        // Abstract infix expression helper methods (3 required)
        protected abstract boolean isAbstractExp(TargetClass param);
        protected abstract boolean isAnotherAbstractExp(TargetClass param);
        protected abstract boolean isThirdAbstractExp(TargetClass param);
    }
}

protected abstract class TargetClass<T> {
    /**
     * Javadoc for target abstract class with type parameter
     * @param <T> Generic type parameter
     */
    public TargetClass() {
        // Anonymous inner class
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class in target class");
            }
        };
        anonymous.run();
    }
}