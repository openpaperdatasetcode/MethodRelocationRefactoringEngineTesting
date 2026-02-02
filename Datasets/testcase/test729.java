import java.util.Objects;

// Same package (implicit as no package declaration specified, same package for both classes)
public class SourceClass {
    // Protected field for access_outer_protected feature
    protected String outerProtectedField = "outerProtectedValue";

    // Source inner class (method_position: source_inner)
    public class SourceInnerClass {
        // Method to be refactored: normal type, final access, return TargetClass type, source_inner position
        public final TargetClass methodToRefactor(TargetClass targetParam) throws IllegalArgumentException {
            // Satisfy per_condition: method contains target class parameter (targetParam)
            
            // Variable call feature
            String localVar = "localVariable";
            int switchVar = 1;
            
            // Access outer protected field (access_outer_protected feature)
            localVar = SourceClass.this.outerProtectedField;

            // Labeled statement feature
            label:
            for (int i = 0; i < 3; i++) {
                if (i == 2) break label;
            }

            // Switch case feature
            switch (switchVar) {
                case 1:
                    localVar += "_case1";
                    break;
                case 2:
                    localVar += "_case2";
                    break;
                default:
                    localVar += "_default";
            }

            // Super keywords feature
            super.toString();

            // DoStatement: private modifier, obj.field, 1, pos: same_package_target
            privateDoStatement(targetParam);

            // Requires_throws feature
            if (Objects.isNull(targetParam)) {
                throw new IllegalArgumentException("TargetClass parameter cannot be null");
            }

            return targetParam;
        }

        // Private DoStatement (matches DoStatement type/modifier/target_feature/pos)
        private void privateDoStatement(TargetClass targetObj) {
            int count = 0;
            do {
                // obj.field + 1 feature
                targetObj.targetField = 1;
                count++;
            } while (count < 1);
        }
    }
}

// Target class: normal class, protected modifier, implements interface + static nested class
protected class TargetClass implements Runnable {
    // Field for obj.field feature
    int targetField;

    // Static nested class (target_feature)
    protected static class target {
        // Placeholder for moved method
        public final TargetClass methodToRefactor(TargetClass targetParam) throws IllegalArgumentException {
            SourceClass source = new SourceClass();
            return source.new SourceInnerClass().methodToRefactor(targetParam);
        }
    }

    // Implements Runnable (required abstract method)
    @Override
    public void run() {
        // Empty implementation for interface compliance
    }
}