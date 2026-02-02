package com.refactoring.movemethod;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

// Diff package class for ReturnStatement pos: diff_package_others
package com.refactoring.others;
public class ReturnHelper {
    private int count = 1; // ReturnStatement target_feature: "1"
    private String helperField = "helper_field"; // ReturnStatement target_feature: "this.field"
    
    public private String getHelperField() {
        return this.helperField;
    }
    
    public private int getCount() {
        return this.count;
    }
}

package com.refactoring.movemethod;
import com.refactoring.others.ReturnHelper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Parent class for target class extends feature
class TargetParent<T> {
    protected T parentField;
    
    public TargetParent(T parentField) {
        this.parentField = parentField;
    }
    
    protected int baseMethod() {
        return 0;
    }
}

// Source class: generic, public, same package, local inner + member inner classes
public class SourceClass<T> {
    // Instance field for access_instance_field feature
    private T instanceField;

    // Member inner class (source feature)
    public class SourceInnerRecursive {
        private String innerField = "inner_rec_field"; // For variable call & depends_on_inner_class
        
        // Local inner class (source feature) - declared in inner recursive class method
        public void localInnerClassHolder() {
            class SourceLocalInner {
                private T localInnerField;
                
                public T getLocalInnerField() {
                    return localInnerField;
                }
            }
        }

        // Method to be refactored: normal, Object return, protected access, source_inner_rec
        @RefactorAnnotation // has_annotation feature
        protected Object methodToMove(TargetClass<T> targetParam) throws IOException {
            // per_condition: method contains target class parameter
            if (targetParam == null) {
                throw new IOException("Target parameter is null"); // IOException feature
            }

            // access_instance_field feature
            instanceField = targetParam.getGenericValue();
            T instanceFieldVal = this.instanceField;

            // variable call feature
            String varCall = this.innerField;

            // depends_on_inner_class feature
            SourceLocalInner localInner = new SourceLocalInner();
            localInner.localInnerField = instanceFieldVal;

            // super keywords feature (via outer class super if exists, or inner class context)
            super.toString();

            // type declaration statement feature
            List<T> dataList = new ArrayList<>();
            dataList.add(instanceFieldVal);

            // enhganced for statement feature
            for (T item : dataList) {
                varCall += item.toString();
            }

            // ReturnStatement feature (type: ReturnStatement, modifier: private, pos: diff_package_others)
            ReturnHelper returnHelper = new ReturnHelper();
            private Object returnStatementLogic() {
                if (returnHelper.getCount() == 1) { // target_feature: "1"
                    return returnHelper.getHelperField(); // target_feature: "this.field"
                }
                return null;
            }
            Object returnVal = returnStatementLogic();

            // no_new_exception (only standard IOException, no custom new Exception())
            if (varCall.isEmpty()) {
                return null;
            }

            return returnVal;
        }
    }
}

// Target class: generic, private, extends (target_feature)
private class TargetClass<T> extends TargetParent<T> {
    private T genericValue;

    public TargetClass(T genericValue) {
        super(genericValue); // extends feature - call parent constructor
        this.genericValue = genericValue;
    }

    public T getGenericValue() {
        return this.genericValue;
    }

    // Call method: target type, protected modifier, overriding, OuterClass.InnerClass.methodName(), pos=while, returns int
    @Override
    protected int baseMethod() {
        int counter = 0;
        while (counter < 5) { // pos: while
            // OuterClass.InnerClass.methodName() target_feature
            SourceClass<T> source = new SourceClass<>();
            SourceClass<T>.SourceInnerRecursive innerRec = source.new SourceInnerRecursive();
            try {
                innerRec.methodToMove(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            counter++;
        }
        return counter;
    }
}