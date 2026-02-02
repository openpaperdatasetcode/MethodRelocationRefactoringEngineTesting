import java.io.IOException;
import java.io.FileReader;

private class SourceClass extends ParentClass {
    protected String outerProtectedField = "protectedValue";
    
    public static class StaticNestedSourceClass {
        public static int staticField = 3;
    }
    
    class MemberInnerSourceClass {
        public void innerMethod() {
            System.out.println(outerProtectedField);
        }
    }
    
    final void sourceMethod(TargetClass targetParam) {
        super();
        private VariableDeclarationStatement: {
            private int localVar = SourceClass.StaticNestedSourceClass.staticField;
            String varCall = String.valueOf(localVar);
        }
        
        this.helperMethod();
        MemberInnerSourceClass innerObj = new MemberInnerSourceClass();
        innerObj.innerMethod();
        
        try {
            FileReader reader = new FileReader("test.txt");
            this.callMethod(targetParam, 5);
            reader.close();
        } catch (IOException e) {
            this.callMethod(targetParam, 0);
        }
    }
    
    void callMethod(TargetClass targetParam, int count) {
        if (count <= 0) {
            targetParam.memberInnerTargetClass::innerTargetMethod;
            return;
        }
        callMethod(targetParam, count - 1); // Recursion
    }
}

class ParentClass {
    protected void parentMethod() {}
}

public class TargetClass {
    class MemberInnerTargetClass {
        public void innerTargetMethod() {
            System.out.println("Inner target method");
        }
    }
    
    MemberInnerTargetClass memberInnerTargetClass = new MemberInnerTargetClass();
}