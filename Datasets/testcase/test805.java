package com.refactoring.movemethod;

// Parent class for source extends feature
class SourceParent {
    protected String parentField = "source_parent_6143";
}

// Parent class for target extends feature
class TargetParent {
    protected String targetParentField = "target_parent_6143";
}

// Source abstract class: strictfp modifier, same package, extends + static nested + local inner classes
strictfp abstract class SourceClass extends SourceParent {
    // Static field for depends_on_static_field feature
    private static final String STATIC_FIELD = "static_6143";

    // Static nested class (source feature)
    private static class SourceStaticNested {
        private String nestedField = "static_nested_6143";
    }

    // Overload exist feature (overloaded method)
    private TargetClass methodToMove(TargetClass targetParam) {
        return methodToMove(targetParam, "default_arg");
    }

    // Method to be refactored: varargs, TargetClass Type return, private access, source position
    private TargetClass methodToMove(TargetClass targetParam, String... varargs) {
        // per_condition: method contains target class parameter
        if (targetParam == null) {
            targetParam = new TargetClassImpl();
        }

        // type declaration statement feature
        SourceStaticNested staticNested = new SourceStaticNested();
        TargetClass.TargetStaticNested targetStatic = targetParam.new TargetStaticNested();

        // variable call feature
        String varCall = staticNested.nestedField + STATIC_FIELD; // depends_on_static_field

        // WhileStatement feature (type: WhileStatement, modifier: private, pos: source)
        private void whileStatementLogic() {
            int count = 1; // target_feature: "1"
            // target_feature: this.field (inner context field)
            String loopField = varCall;
            while (count < 3) { // WhileStatement
                loopField += "_loop_" + count;
                count++;
            }
            targetStatic.nestedVal = loopField;
        }
        whileStatementLogic();

        // do statement feature
        int doCount = 1; // target_feature: "1"
        do {
            varCall += "_do_" + doCount;
            doCount++;
        } while (doCount <= 1); // target_feature: "1"

        // local inner class (source feature)
        class SourceLocalInner {
            private void processTarget(TargetClass target) {
                target.setData(varCall);
            }
        }
        new SourceLocalInner().processTarget(targetParam);

        // no_new_exception (no explicit new Exception instantiation)
        return targetParam;
    }

    // Call method: source type, public modifier, generic, lambda, pos=exception handling, returns int
    public <T> int callMethod(TargetClass targetParam) {
        try {
            // pos: exception handling statements
            // target_feature: generic + (parameters) -> methodBody (lambda)
            java.util.function.Function<TargetClass, Integer> processor = (t) -> { // lambda
                TargetClass result = methodToMove(t, STATIC_FIELD); // varargs call
                return result.getData().length();
            };
            return processor.apply(targetParam);
        } catch (NullPointerException e) {
            return -1;
        }
    }
}

// Target abstract class: protected modifier, extends + static nested class (target_features)
protected abstract class TargetClass extends TargetParent {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public String nestedVal;
    }

    public abstract String getData();
    public abstract void setData(String data);
    public abstract TargetStaticNested new TargetStaticNested();
}

// Concrete implementation of abstract target class
class TargetClassImpl extends TargetClass {
    private String data;

    @Override
    public String getData() {
        return data + "_" + this.targetParentField;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public TargetStaticNested new TargetStaticNested() {
        return new TargetStaticNested();
    }
}