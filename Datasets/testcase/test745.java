package refactoring.test;

import java.io.IOException;

// Source class: normal (abstract), abstract modifier, same package as target, features: two static nested classes
abstract class SourceClass {
    // First static nested class (source_class feature)
    static class SourceStaticNested1 {
        static int staticField = 1; // For WhileStatement target_feature: ClassName.field, 1
    }

    // Second static nested class (source_class feature)
    static class SourceStaticNested2 {
        String data;

        public SourceStaticNested2(String data) {
            this.data = data;
        }
    }

    // Inner class for call_method feature (type: inner_class, modifier: protected)
    protected class SourceInnerClass {
        public Object innerMethod(int num) {
            return num * 2;
        }
    }

    // Subclass for overriding feature (call_method target_feature: overriding)
    protected class SourceInnerSubClass extends SourceInnerClass {
        @Override
        public Object innerMethod(int num) {
            // super.methodName(arguments) feature (call_method target_feature)
            return super.innerMethod(num) + "-overridden";
        }
    }

    // Static code block (call_method pos: Static code blocks)
    static {
        SourceClass source = new SourceClass() {};
        SourceInnerSubClass innerSub = source.new SourceInnerSubClass();
        Object result = innerSub.innerMethod(1); // Call overridden inner class method
    }

    // Method to be refactored: instance, return TargetClass type, public access, position source
    // Per condition: method contains target class parameter
    public TargetClass moveMethod(TargetClass targetParam) throws IOException {
        // Variable call feature
        int localVar = 1;
        // Type declaration statement feature
        SourceStaticNested2 nestedObj;
        
        // Constructor invocation feature
        nestedObj = new SourceStaticNested2("test-data");
        // Expression statement feature
        String exprStmt = nestedObj.data + "-" + localVar;
        
        // Uses_outer_this feature
        SourceInnerClass innerThis = SourceClass.this.new SourceInnerClass();
        
        // WhileStatement feature (type: WhileStatement, modifier: private, target_feature: ClassName.field, 1, pos: inner class)
        class InnerWhileClass {
            private void process() throws IOException {
                while (SourceStaticNested1.staticField == 1) {
                    localVar++;
                    if (localVar > 5) {
                        SourceStaticNested1.staticField = 0;
                    }
                    // IOException feature (declares throws, no new exception thrown)
                    if (targetParam == null) {
                        throw new IOException("Target parameter cannot be null");
                    }
                }
            }
        }
        
        InnerWhileClass whileClass = new InnerWhileClass();
        whileClass.process();
        
        // No new exception feature (no throw new custom exception)
        targetParam.setData(exprStmt);
        return targetParam;
    }
}

// Target class: normal, private modifier, same package, no target features
private class TargetClass {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}