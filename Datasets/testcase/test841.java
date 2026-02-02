package refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Super class for target extends feature
class TargetSuperClass {
    protected int superField = 1; // super.field + 1 for VariableDeclarationStatement
}

// Target class: public modifier, extends super class, static nested class (target_feature)
public class TargetClass extends TargetSuperClass {
    public int targetField = 2; // method_feature: 2

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public static List<String> staticMethod(List<String> args) {
            return new ArrayList<>(args);
        }
    }

    public int getTargetField() {
        return targetField;
    }
}

// Subclass of TargetClass for method_feature: sub_class
public class TargetSubClass extends TargetClass {
    public static List<String> helperMethod(List<String> data) {
        data.add("subClass_" + 2);
        return data;
    }
}

// Source class: public modifier, member inner + anonymous inner class (source_feature)
public class SourceClass {
    // Source contains target field (per_condition)
    private TargetClass targetField = new TargetClass();
    
    // Protected outer field for access_outer_protected feature
    protected int outerProtectedField = 1;

    // Member inner class (source_feature)
    public class SourceMemberInner {
        public int getCombinedValue() {
            return targetField.targetField + outerProtectedField;
        }
    }

    // Anonymous inner class (source_feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class: " + targetField.superField);
        }
    };

    // VariableDeclarationStatement feature: static modifier, super.field, 1, pos=same_package_target
    public static void variableDeclarationFeature() {
        // Variable declaration with super.field (1)
        int staticVar = new TargetClass().superField; // same_package_target pos
    }

    // Static method feature: synchronized modifier, 2, sub_class, static, this.methodName(), pos=ternary operators, return_type=List<String>
    public static synchronized List<String> staticHelperMethod(TargetSubClass subClass) {
        List<String> baseList = new ArrayList<>();
        baseList.add("static_1");
        baseList.add("static_2"); // 2 for method_feature
        
        // Ternary operators (pos) + this.methodName(arguments)
        List<String> result = subClass.helperMethod(baseList) != null ? 
            subClass.helperMethod(baseList) : new ArrayList<>();
        return result;
    }

    // Instance method: public access, base return type (int), method_position=source
    public int refactorMethod() {
        // Execute VariableDeclarationStatement feature
        variableDeclarationFeature();

        // Variable call feature
        int varCall = targetField.getTargetField();

        // Access outer protected feature
        int outerProtectedVal = this.outerProtectedField;

        // Call static helper method (sub_class)
        TargetSubClass subClass = new TargetSubClass();
        List<String> staticResult = staticHelperMethod(subClass);

        // Use member inner class
        SourceMemberInner inner = new SourceMemberInner();
        int innerVal = inner.getCombinedValue();

        // Execute anonymous inner class
        anonymousInner.run();

        // No new exception thrown feature
        return varCall + outerProtectedVal + innerVal + staticResult.size();
    }
}