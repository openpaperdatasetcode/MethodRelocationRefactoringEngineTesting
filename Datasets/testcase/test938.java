package refactoring.test;

import java.util.ArrayList;
import java.util.List;

// Parent class for method_feature: parent_class
class ParentClass {
    protected static List<String> parentVarargsMethod(Integer... args) {
        List<String> list = new ArrayList<>();
        for (Integer arg : args) {
            list.add(String.valueOf(arg));
        }
        return list;
    }
}

// Source class: normal, strictfp modifier, same package as target, features: anonymous inner class, static nested class
strictfp class SourceClass extends ParentClass {
    // Static nested class (source_class feature)
    static class SourceStaticNested<T> {
        T nestedData;
    }

    // Method to be refactored: overloading, return TargetClass type, final access, position source
    // Per condition: method contains target class parameter
    // method types parameter is:generic (TargetClass<T> as parameter)
    final <T> TargetClass<T> moveMethod(TargetClass<T> targetParam, T... genericArgs) {
        // Variable call feature
        int localVar = 2;
        // Type declaration statement feature
        SourceStaticNested<T> nestedObj;
        nestedObj = new SourceStaticNested<>();
        nestedObj.nestedData = genericArgs.length > 0 ? genericArgs[0] : null;

        // Expression statement feature
        String exprStmt = "Generic data: " + nestedObj.nestedData;
        System.out.println(exprStmt);

        // NullPointerException feature
        if (targetParam == null) {
            throw new NullPointerException("Target parameter cannot be null");
        }

        // Exception handling statements with varargs method (pos: exception handling statements)
        List<String> varargsResult = null;
        try {
            // Varargs method call (type: varargs, modifier: final, method_feature: 2, parent_class, varargs, (parameters) -> methodBody)
            varargsResult = finalVarargsMethod(2, 4, 6);
            varargsResult.forEach((parameters) -> varargsResult.add(String.valueOf(parameters.length()))); // (parameters) -> methodBody
        } catch (Exception e) {
            // No new exception thrown (no_new_exception feature)
            varargsResult = new ArrayList<>();
        }

        targetParam.setData(nestedObj.nestedData);
        targetParam.setStaticNestedData(varargsResult);
        return targetParam;
    }

    // Overload method (overload_exist feature)
    final TargetClass<String> moveMethod(TargetClass<String> targetParam, String singleArg) {
        targetParam.setData(singleArg);
        return targetParam;
    }

    // Varargs method (type: varargs, modifier: final, return_type: List<String>)
    final List<String> finalVarargsMethod(Integer... args) {
        // method_feature: 2 (use value 2)
        List<String> result = parentVarargsMethod(2, args); // method_feature: parent_class, varargs
        return result;
    }

    // Anonymous inner class (source_class feature)
    Runnable anonymousRunnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class execution");
        }
    };
}

// Target class: normal, strictfp modifier, same package, target_feature: static nested class
strictfp class TargetClass<T> {
    private T data;
    private List<String> staticNestedData;

    // Static nested class (target_feature)
    static class TargetStaticNested {
        static <T> void validate(T data) {
            if (data == null) throw new NullPointerException();
        }
    }

    public void setData(T data) {
        TargetStaticNested.validate(data);
        this.data = data;
    }

    public void setStaticNestedData(List<String> data) {
        this.staticNestedData = data;
    }

    public T getData() {
        return data;
    }
}