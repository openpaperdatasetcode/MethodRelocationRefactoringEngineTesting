package refactoring.test;

/**
 * Javadoc for TargetClass (target_feature: javadoc)
 * Target class for move method refactoring
 */
// Target class: normal, default modifier, same package, target_feature: javadoc
class TargetClass {
    // Inner class (target_inner - target class for method)
    class TargetInner {
        private String data;
        private int value;

        public void setData(String data) {
            this.data = data;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getData() {
            return data;
        }

        public int getValue() {
            return value;
        }
    }

    public TargetInner getInnerClass() {
        return new TargetInner();
    }
}

// Source class: normal, strictfp modifier, same package as target, no additional features
strictfp class SourceClass {
    // Instance field for access_instance_field feature
    private int instanceField = 10;

    /**
     * Javadoc for normal method (method javadoc feature)
     * @param targetParam target class parameter (per condition)
     * @return TargetClass instance with processed inner class
     */
    // Method to be refactored: normal, return TargetClass type, default access, position source
    TargetClass moveMethod(TargetClass targetParam) {
        // Variable call feature
        int localVar = 5;
        // Access instance field feature
        localVar += this.instanceField;

        // With_bounds feature (type parameter with bounds)
        class BoundedClass<T extends Number & Comparable<T>> {
            T process(T val) {
                return val.compareTo((T) Integer.valueOf(10)) > 0 ? val : (T) Integer.valueOf(10);
            }
        }
        BoundedClass<Integer> boundedObj = new BoundedClass<>();
        localVar = boundedObj.process(localVar);

        // Expression statement feature
        String exprStmt = "Processed value: " + localVar;
        System.out.println(exprStmt);

        // Empty statement feature
        ;

        // For statement feature
        for (int i = 0; i < 3; i++) {
            localVar += i;
            // Empty statement inside loop
            ;
        }

        // Requires_try_catch feature
        try {
            TargetClass.TargetInner inner = targetParam.getInnerClass();
            inner.setData(exprStmt);
            inner.setValue(localVar);
        } catch (NullPointerException e) {
            // Handle NPE without throwing new exceptions
            System.err.println("Target parameter inner class is null: " + e.getMessage());
        }

        return targetParam;
    }
}