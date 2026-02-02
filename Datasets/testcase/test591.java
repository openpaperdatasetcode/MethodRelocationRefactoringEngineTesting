package refactoring.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Target interface for implements feature
interface TargetOperation {
    void execute();
}

// Source class: abstract normal class, same package, static nested + local inner classes
abstract class SourceClass {
    // Static field for depends_on_static_field feature
    private static int staticCounter = 0;

    // Static nested class (source feature)
    static class SourceStaticNested {
        int nestedValue = 0;
    }

    // Member inner class (method_position: source_inner)
    class SourceInnerClass {
        // Method to be refactored: static, List<String> return, final access, source_inner
        public final static List<String> refactorMethod(TargetClass targetParam) { // per_condition: target parameter
            List<String> result = new ArrayList<>();
            
            // Variable call (target class field)
            targetParam.data = "refactor_test";
            // Depends on static field
            staticCounter += targetParam.counter;
            
            // OuterClass.this.x feature
            int outerField = SourceClass.this.getOuterField();

            // Local inner class (source feature)
            class LocalInnerClass {
                void processTarget() throws SQLException {
                    // SQLException feature
                    if (targetParam.data == null) {
                        throw new SQLException("Data is null");
                    }
                    result.add(targetParam.data + ":" + staticCounter);
                }
            }

            // Exception handling for SQLException (no_new_exception)
            try {
                new LocalInnerClass().processTarget();
            } catch (SQLException e) {
                // No new exception instantiation
                result.add("Error: " + e.getMessage());
            }

            // Anonymous inner class usage (target_feature)
            targetParam.operation = new TargetOperation() {
                @Override
                public void execute() {
                    result.add("anonymous_executed");
                }
            };
            targetParam.operation.execute();

            // No new exception, return List<String>
            return result;
        }
    }

    // Helper method for OuterClass.this.x
    private int getOuterField() {
        return 10;
    }
}

// Target class: normal, protected modifier, implements + anonymous inner class features
protected class TargetClass implements TargetOperation {
    String data;
    int counter = 1;
    TargetOperation operation;

    @Override
    public void execute() {
        // Anonymous inner class (target_feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                counter++;
            }
        };
        anonymousRunnable.run();
    }
}