import java.util.function.Function;

// Source record class: default modifier, same package, anonymous inner/member inner class
record SourceRecord(String sourceField) {
    // Member inner class (fulfills source_class feature)
    class SourceMemberInner {
        String getInnerValue() {
            return sourceField + "_inner";
        }
    }

    // Anonymous inner class (fulfills source_class feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class: " + sourceField);
        }
    };

    /**
     * Method Javadoc (fulfills method javadoc feature)
     * Processes target inner recursive class parameter and returns target class type
     * @param param Target inner recursive class instance
     * @return TargetRecord type instance
     */
    private TargetRecord processData(TargetRecord.TargetInnerClass.TargetInnerRecClass param) {
        // Variable call to target parameter (fulfills per_condition)
        String targetVar = param.getRecValue();
        
        // Requires_try_catch feature
        TargetRecord result = null;
        try {
            // Generic method (protected modifier, 2=numbers, target=type, super.methodName(arguments) in ternary)
            // Ternary operator position (pos: ternary operators)
            int genericResult = (targetVar.length() > 5) 
                ? this.<Integer>genericMethod(2, param) // super.methodName(arguments), numbers:2
                : this.<Integer>genericMethod(1, param);
            
            // Local inner class in target is accessed (target_feature)
            param.callTargetLocalInner();
            
            // Create target class instance as return value (TargetClas Type)
            result = new TargetRecord(targetVar + "_processed_" + genericResult);
        } catch (NullPointerException e) {
            // Requires_try_catch (handle potential NPE)
            result = new TargetRecord("default_value");
        }
        
        return result; // TargetClas Type return
    }

    /**
     * Generic method (fulfills generic method feature)
     * @param <V> Generic type parameter
     * @param val Numeric value (numbers:2)
     * @param param Target class parameter (target type)
     * @return Base type (Integer)
     */
    protected <V extends Number> int genericMethod(V val, TargetRecord.TargetInnerClass.TargetInnerRecClass param) {
        // super.methodName(arguments) simulation (call parent method of target inner class)
        return val.intValue() * param.superMethod(1);
    }
}

// Target record class: non-sealed modifier, local inner class (target_feature)
non-sealed record TargetRecord(String targetField) {
    // Member inner class (first level)
    public class TargetInnerClass {
        // Target inner recursive class (target_inner_rec)
        public class TargetInnerRecClass {
            private String recValue = targetField + "_rec";

            // Super method for super.methodName(arguments) call
            public int superMethod(int multiplier) {
                return recValue.length() * multiplier;
            }

            public String getRecValue() {
                return recValue;
            }

            // Method to trigger target's local inner class (target_feature)
            public void callTargetLocalInner() {
                // Local inner class (fulfills target_feature)
                class TargetLocalInner {
                    String processValue(String val) {
                        return val.toUpperCase();
                    }
                }
                TargetLocalInner localInner = new TargetLocalInner();
                this.recValue = localInner.processValue(this.recValue);
            }
        }
    }
}