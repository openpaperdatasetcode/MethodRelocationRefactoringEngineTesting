protected class SourceClass {
    protected String outerProtectedField = "protectedValue";
    
    // First member inner class
    class MemberInnerClass1 {
        // Second member inner class (nested to satisfy feature requirement)
        class MemberInnerClass2 {
            private void sourceInnerMethod(TargetClass targetParam) {
                // Variable call
                String varCall = SourceClass.this.outerProtectedField;
                
                // Expression statement
                varCall = varCall + targetParam.toString();
                
                // NullPointerException
                if (targetParam == null) {
                    throw new NullPointerException("Target parameter is null");
                }
                
                // Access outer protected field
                SourceClass.this.outerProtectedField = varCall;
                
                try {
                    // Override violation (invalid override attempt in inner scope)
                    new Object() {
                        @Override
                        public String toString() {
                            return SourceClass.this.outerProtectedField;
                        }
                    }.toString();
                } catch (Exception e) {
                    // No new exception thrown
                }
            }
            
            // Trigger method to invoke inner method with target parameter
            public void triggerMethod(TargetClass targetParam) {
                sourceInnerMethod(targetParam);
            }
        }
    }
    
    // Override violation (invalid return type vs Object's toString)
    @Override
    public int toString() {
        return 0;
    }
    
    // Constructor to initialize inner classes
    public SourceClass() {
        new MemberInnerClass1().new MemberInnerClass2().triggerMethod(new TargetClass());
    }
}

private class TargetClass {
    // No target features as per specification
}