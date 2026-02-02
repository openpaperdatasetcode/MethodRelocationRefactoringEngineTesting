// Source class package (different from target)
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.util.Objects;

// Functional interface for implements feature
interface DataHandler {
    void handle(String data);
}

// Parent class for extends feature
class SourceParent {
    protected String parentField = "source_parent_6141";
}

// Source class: normal, default modifier, different package, extends + implements + local inner + anonymous inner classes
class SourceClass extends SourceParent implements DataHandler {
    // per_condition: source contains field of target
    private TargetClass targetField = new TargetClass();

    /**
     * Method Javadoc (method javadoc feature)
     * Processes variable arguments and returns TargetClass instance
     * @param args Variable arguments for processing
     * @return TargetClass instance (TargetClass Type)
     * @throws IllegalArgumentException if args are null
     */
    // Method to be refactored: varargs, TargetClass Type return, protected access, source position
    protected TargetClass methodToMove(String... args) {
        // super constructor invocation (implicit in extends context, explicit call)
        super();

        // raw_type feature (raw TargetClass usage)
        TargetClass rawTarget = new TargetClass(); // raw_type

        // try statement feature
        try {
            // throw statement feature (validation)
            if (args == null) {
                throw new IllegalArgumentException("Arguments cannot be null (ID:6141)");
            }

            // variable call feature
            String parentVal = this.parentField;
            String processedData = parentVal;

            // for loop with continue statement
            for (int i = 0; i < args.length; i++) {
                String arg = args[i];
                if (arg == null || arg.isEmpty()) {
                    continue; // continue statement feature
                }
                processedData += "_" + arg;
            }

            // expression statement feature
            targetField.setData(processedData); // expression statement

            // local inner class (source feature)
            class SourceLocalInner {
                private void validateTarget(TargetClass target) {
                    Objects.requireNonNull(target, "Target cannot be null");
                }
            }
            new SourceLocalInner().validateTarget(targetField);

            // anonymous inner class (source feature)
            DataHandler anonymousHandler = new DataHandler() {
                @Override
                public void handle(String data) {
                    targetField.appendData(data);
                }
            };
            anonymousHandler.handle(processedData);

            // return statement feature (return TargetClass Type)
            return targetField;

        } catch (IllegalArgumentException e) {
            // no_new_exception (no explicit new Exception instantiation beyond throw statement requirement)
            e.printStackTrace();
            // return fallback target instance
            return rawTarget;
        }
    }

    // implements feature method implementation
    @Override
    public void handle(String data) {
        methodToMove(data);
    }
}
// Target class package (different from source)
package com.refactoring.target;

// Target class: normal, public modifier, local inner class (target_feature)
public class TargetClass {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void appendData(String data) {
        // local inner class (target_feature)
        class TargetLocalInner {
            private String processAppend(String base, String append) {
                return base == null ? append : base + "_appended_" + append;
            }
        }
        this.data = new TargetLocalInner().processAppend(this.data, data);
    }
}
