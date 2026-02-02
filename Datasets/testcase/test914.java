package refactoring.test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

// Target inner record (target_inner_rec - target class of method)
public record TargetInnerRec(String id, int value) {}

// Target class: public modifier, empty target_feature
public class TargetClass<T> {
    public static int staticField = 1; // ClassName.field + 1 for VariableDeclarationStatement
    public T targetData;

    public TargetClass(T targetData) {
        this.targetData = targetData;
    }

    public TargetInnerRec createInnerRec() {
        return new TargetInnerRec("REC_" + targetData, (Integer) targetData);
    }

    public List<String> helperMethod(int val) {
        List<String> list = new ArrayList<>();
        list.add("helper_" + val);
        return list;
    }
}

// Others class for constructor method_feature
class OthersClass {
    private List<String> dataList;

    public OthersClass(int initVal) {
        this.dataList = new ArrayList<>();
        this.dataList.add("init_" + initVal); // method_feature: 1
    }

    public List<String> getList() {
        return this.dataList;
    }
}

// Source class: default modifier, type parameter, member inner + static nested class (source_feature)
class SourceClass<T> {
    // Protected outer field for access_outer_protected feature
    protected int outerProtectedField = 5;

    // Static nested class (source_feature)
    public static class SourceStaticNested<U> {
        public static int staticHelper(U data) {
            return (Integer) data;
        }
    }

    // Member inner class (source_feature)
    public class SourceMemberInner<T> {
        public T innerData;

        public SourceMemberInner(T innerData) {
            this.innerData = innerData;
        }
    }

    // VariableDeclarationStatement feature: private modifier, ClassName.field, 1, pos=source
    private void variableDeclarationFeature() {
        int localVar = TargetClass.staticField; // ClassName.field + 1, pos=source
        System.out.println("VariableDeclaration: " + localVar);
    }

    // Constructor feature: default modifier, 1, others_class, constructor, this.methodName(), pos=if/else, return List<String>
    private List<String> constructorFeature(TargetClass<T> target) {
        OthersClass othersObj;
        // pos=if/else conditions
        if (target.staticField == 1) {
            othersObj = new OthersClass(1); // method_feature: 1 (others_class constructor)
        } else {
            othersObj = new OthersClass(0);
        }
        return this.processConstructorResult(othersObj.getList()); // this.methodName(arguments)
    }

    private List<String> processConstructorResult(List<String> input) {
        input.add("processed");
        return input;
    }

    // Instance method: protected access, base return type (int), target parameter (per_condition)
    protected int refactorMethod(TargetClass<T> targetParam) {
        // Variable call feature
        T varCall = targetParam.targetData;
        TargetInnerRec innerRec = targetParam.createInnerRec();

        // Access outer protected feature
        int outerProtected = this.outerProtectedField;

        // Execute VariableDeclarationStatement feature
        variableDeclarationFeature();

        // Break statement feature
        int breakSum = 0;
        for (int i = 0; i < 10; i++) {
            breakSum += i;
            if (i == TargetClass.staticField) { // Break when i == 1
                break; // Break statement
            }
        }

        // Used by reflection feature
        try {
            Method createRecMethod = TargetClass.class.getMethod("createInnerRec");
            TargetInnerRec reflectRec = (TargetInnerRec) createRecMethod.invoke(targetParam);
            System.out.println("Reflection: " + reflectRec.id());
        } catch (Exception e) {
            // No new exception thrown feature
            System.err.println("Reflection handled: " + e.getMessage());
        }

        // Execute constructor feature
        List<String> constructorResult = constructorFeature(targetParam);

        // No new exception thrown feature
        return (Integer) varCall + breakSum + outerProtected + constructorResult.size();
    }
}