package refactoring.test;

// Same package other class for VariableDeclarationStatement pos: same_package_others
class SamePackageOtherClass {
    int field = 3; // target_feature: obj.field, 3
}

// Source class: normal, protected modifier, same package as target, features: member inner class, local inner class
protected class SourceClass {
    // Member inner class (source_class feature)
    class SourceMemberInner {
        int calculate(int val) {
            return val * 3;
        }
    }

    /**
     * Javadoc for overloading method (method javadoc feature)
     * @param targetParam target class parameter (per condition)
     * @return TargetClass instance
     */
    // Method to be refactored: overloading, return TargetClass type, public access, position source
    public TargetClass moveMethod(TargetClass targetParam) {
        // Variable call feature
        int localVar = 0;
        SourceMemberInner memberInner = new SourceMemberInner();

        // VariableDeclarationStatement feature (type: VariableDeclarationStatement, modifier: static, target_feature: obj.field, 3, pos: same_package_others)
        static SamePackageOtherClass staticObj = new SamePackageOtherClass();
        localVar = staticObj.field;

        // Local inner class (source_class feature)
        class SourceLocalInner {
            int increment(int val) {
                return val + 1;
            }
        }
        SourceLocalInner localInner = new SourceLocalInner();

        // Do statement feature
        int doCounter = 0;
        do {
            localVar = localInner.increment(localVar);
            doCounter++;
            if (doCounter % 2 == 0) {
                continue; // Continue statement feature
            }
        } while (doCounter < 5);

        // For statement feature
        for (int i = 0; i < 3; i++) {
            localVar = memberInner.calculate(localVar);
        }

        // Depends on inner class feature (use member + local inner classes)
        localVar = memberInner.calculate(localInner.increment(localVar));

        // No new exception thrown (no_new_exception feature)
        targetParam.getInnerClass().setData("processed-" + localVar);
        return targetParam;
    }

    // Overload method (overload_exist feature)
    public TargetClass moveMethod(TargetClass targetParam, String extraData) {
        targetParam.getInnerClass().setData(extraData);
        return targetParam;
    }
}

// Target class: normal, private modifier, same package, target_feature: member inner class
private class TargetClass {
    // Member inner class (target_feature)
    class TargetMemberInner {
        private String data;

        public void setData(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }

    public TargetMemberInner getInnerClass() {
        return new TargetMemberInner();
    }
}