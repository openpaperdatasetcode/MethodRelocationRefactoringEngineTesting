import java.io.IOException;

// Same package (implicit, no package declaration)
protected class SourceClass {
    // Method to refactor: instance, void return, public access, source position
    public void methodToRefactor(TargetClass targetParam) throws IOException {
        // Satisfy per_condition: method contains target class parameter (targetParam)
        
        // Variable call feature
        String localVar = "testVar";
        localVar = targetParam.innerField + "";

        // Type declaration statement feature
        int switchVar;
        TargetClass targetInstance;

        // Expression statement feature
        switchVar = 3;
        localVar += switchVar;

        // Constructor invocation feature
        targetInstance = new TargetClass();

        // Switch statement feature
        switch (switchVar) {
            case 1:
                localVar += "_case1";
                break;
            case 3:
                localVar += "_case3";
                break;
            default:
                localVar += "_default";
        }

        // Super keywords feature
        super.toString();

        // ReturnStatement: protected modifier, obj.field, 3, pos: diff_package_others
        protectedReturnStatement(targetParam);

        // Overriding feature (exception handling statements position)
        try {
            TargetSubClass subClass = new TargetSubClass();
            // this.methodName(arguments), 1, target, overriding
            TargetClass result = subClass.overriddenMethod(1, targetParam);
        } catch (IOException e) {
            // Exception handling position for overriding
            throw new IOException(e.getMessage()); // No new exception (rethrow existing)
        }

        // No new exception feature (no 'new Exception()' other than rethrow existing IOException)
    }

    // ReturnStatement implementation (diff_package_others position)
    protected void protectedReturnStatement(TargetClass targetObj) {
        targetObj.innerField = 3; // obj.field, 3
        return;
    }
}

// Target class: normal, protected modifier, static nested class target_feature
protected class TargetClass {
    int innerField;

    // Static nested class (target_feature)
    protected static class target_inner {
        // Placeholder for moved method
        public void methodToRefactor(TargetClass targetParam) throws IOException {
            SourceClass source = new SourceClass();
            source.methodToRefactor(targetParam);
        }
    }

    // Overridden method for overriding feature
    protected TargetClass overriddenMethod(int num, TargetClass target) {
        // this.methodName(arguments)
        this.helperMethod(num);
        return target;
    }

    private void helperMethod(int num) {}
}

// Subclass for overriding feature
class TargetSubClass extends TargetClass {
    @Override
    protected TargetClass overriddenMethod(int num, TargetClass target) {
        // Overriding implementation
        return super.overriddenMethod(num, target);
    }
}