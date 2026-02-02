package refactoring.test;

// Source class: private enum, same package as target, with local inner + member inner classes
private enum SourceEnum {
    INSTANCE;

    // Target enum field (per_condition: source contains target's field)
    private TargetEnum targetField = TargetEnum.VALUE1;

    // Member inner class (for source_inner_rec structure)
    class SourceMemberInner {
        // Recursive inner class (method_position: source_inner_rec)
        class SourceRecursiveInner {
            // Method to be refactored: private instance, void return, target=target enum
            private void refactorMethod(TargetEnum keywords) {
                // Variable call
                targetField.setData("test");
                // Uses outer this
                SourceEnum outerThis = SourceEnum.this;
                
                // Local inner class (source class feature)
                class LocalInnerClass {
                    Object process() {
                        // super.methodName() (method feature)
                        return super.toString();
                    }
                }

                // EnhancedForStatement (private modifier, same_package_others pos)
                for (String s : targetField.getDataList()) {
                    // obj.field (EnhancedForStatement target_feature)
                    int fieldVal = targetField.count; // 1 (numeric feature)
                    
                    // Instance method (default modifier, for pos, return Object)
                    default Object instanceMethod() {
                        LocalInnerClass inner = new LocalInnerClass(); // inner_class feature
                        return inner.process(); // 2 (numeric feature)
                    }
                    
                    // Call instance method
                    Object result = instanceMethod();
                    // No new exception
                }
            }
        }
    }

    // Target class: enum, empty modifier, anonymous inner class feature
    enum TargetEnum {
        VALUE1, VALUE2;

        String data;
        int count = 1;
        String[] dataList = {"a", "b", "c"};

        void setData(String data) {
            this.data = data;
        }

        String[] getDataList() {
            // Anonymous inner class (target_feature)
            Runnable anonymous = new Runnable() {
                @Override
                public void run() {
                    count++;
                }
            };
            anonymous.run();
            return dataList;
        }
    }
}