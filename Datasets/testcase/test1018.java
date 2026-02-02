package refactoring.test;

// Custom annotation for has_annotation feature
@interface MethodMarker {}

// Source class: normal, final modifier, same package as target, features: anonymous inner class, static nested class
final class SourceClass {
    // Static nested class (source_class feature)
    static class SourceStaticNested {
        int nestedValue = 1;
    }

    // Member inner class (source_inner_rec - method position host)
    class SourceInnerRecClass {
        /**
         * Method to be refactored: normal, return void, final access, position source_inner_rec
         * Per condition: contains target class parameter
         */
        @MethodMarker // has_annotation feature
        final void moveMethod(TargetClass targetParam) {
            // Variable call feature
            int localVar = 0;
            SourceStaticNested staticNested = new SourceStaticNested();

            // VariableDeclarationExpression feature (numbers: 1, modifier: default, exp: VariableDeclarationExpression)
            var varExpr = 1; // var declaration with value 1 (default modifier)
            localVar += varExpr;

            // Expression statement feature
            String exprStmt = "Initial value: " + localVar;
            System.out.println(exprStmt);

            // Empty statement feature
            ;

            // Switch case feature
            switch (localVar) {
                case 1:
                    localVar += staticNested.nestedValue;
                    break;
                default:
                    localVar = 1;
                    break;
            }

            // Anonymous inner class (source_class feature)
            Runnable anonymousRunnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Anonymous inner class - localVar: " + localVar);
                }
            };
            anonymousRunnable.run();

            // Use target parameter's member inner class
            targetParam.getInnerClass().processData(localVar);

            // No new exception thrown (no_new_exception feature)
        }

        // Overload method (overload_exist feature)
        final void moveMethod(TargetClass targetParam, String extra) {
            targetParam.getInnerClass().processData(extra);
        }
    }
}

// Target class: normal, protected modifier, same package, target_feature: member inner class
protected class TargetClass {
    // Member inner class (target_feature)
    class TargetMemberInner {
        void processData(Object data) {
            System.out.println("Target processed data: " + data);
        }
    }

    public TargetMemberInner getInnerClass() {
        return new TargetMemberInner();
    }
}