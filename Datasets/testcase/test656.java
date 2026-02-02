public class SourceClass {
    private String outerPrivateField = "privateValue";
    
    public static class StaticNestedSourceClass {
        public String nestedField = "nestedValue";
    }

    final TargetClass sourceMethod(TargetClass targetParam) {
        super();
        String varCall = this.outerPrivateField;
        
        // Local inner class (source_class feature)
        class LocalInnerSourceClass {
            String processTarget(TargetClass param) {
                return param.staticNestedTargetClass.targetField + varCall;
            }
        }
        
        try {
            // Access outer private field & target instance field
            targetParam.staticNestedTargetClass.targetField = varCall;
            varCall = new LocalInnerSourceClass().processTarget(targetParam);
            
            // Return TargetClass type (method return_type)
            return targetParam;
        } catch (Exception e) {
            // No new exception thrown
            return targetParam;
        }
    }
}

abstract class TargetClass {
    public static class StaticNestedTargetClass {
        public String targetField = "targetValue";
    }
    
    // Target inner static nested class instance (target_inner)
    public StaticNestedTargetClass staticNestedTargetClass = new StaticNestedTargetClass();
}