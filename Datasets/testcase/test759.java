package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

// Source class: generic, abstract, same package, implements interface, contains two anonymous inner classes
public abstract class SourceClass<T> implements DataProcessor<T> {
    protected int x = 6097; // Field for OuterClass.this.x reference

    // Inner class for depends_on_inner_class feature
    protected class SourceInnerClass {
        String innerField = "sourceInnerValue";
    }

    // Parent method for overriding
    @Override
    public TargetClass<T>.TargetInnerClass methodToMove(TargetClass<T>.TargetInnerClass targetParam) throws SQLException {
        // per_condition: method contains target inner class parameter
        if (targetParam == null) {
            throw new SQLException("Target inner class parameter is null");
        }

        // OuterClass.this.x feature
        int outerField = SourceClass.this.x;
        targetParam.innerData = outerField;

        // Variable call feature
        SourceInnerClass innerObj = new SourceInnerClass();
        String varCall = innerObj.innerField;
        targetParam.processedData = varCall;

        // Depends_on_inner_class feature
        targetParam.innerClassRef = innerObj;

        // Used_by_reflection feature
        try {
            Method method = SourceClass.class.getDeclaredMethod("methodToMove", TargetClass.TargetInnerClass.class);
            method.setAccessible(true);
        } catch (NoSuchMethodException e) {
            // No_new_exception feature (no explicit new Exception instantiation)
            e.printStackTrace();
        }

        // No_new_exception (only standard SQLException, no custom exception creation)
        if (targetParam.processedData.isEmpty()) {
            throw new SQLException("Processed data is empty");
        }

        // Return TargetClass Type (target inner class instance)
        return targetParam;
    }

    // First anonymous inner class (required feature)
    DataProcessor<T> anonymousInner1 = new DataProcessor<T>() {
        @Override
        public TargetClass<T>.TargetInnerClass methodToMove(TargetClass<T>.TargetInnerClass targetParam) throws SQLException {
            return targetParam;
        }
    };

    // Second anonymous inner class (required feature)
    Consumer<T> anonymousInner2 = new Consumer<T>() {
        @Override
        public void accept(T t) {
            System.out.println(t);
        }
    };
}

// Interface implemented by source class
interface DataProcessor<T> {
    TargetClass<T>.TargetInnerClass methodToMove(TargetClass<T>.TargetInnerClass targetParam) throws SQLException;
}

// Target class: generic, public, contains member inner class (target_feature)
public class TargetClass<T> {
    // Member inner class (target_inner)
    public class TargetInnerClass {
        int innerData;
        String processedData;
        SourceClass<T>.SourceInnerClass innerClassRef;

        // strictfp method (call_method: type=target, modifier=strictfp, overriding, lambda in collection ops)
        @Override
        public strictfp Object processCollection(Collection<T> collection) {
            // (parameters) -> methodBody feature (collection operations position)
            collection.forEach((T item) -> {
                this.processedData = item.toString();
                this.innerData = item.hashCode();
            });
            return this.processedData;
        }
    }

    // Collection operation using the strictfp method
    public void executeCollectionOps() {
        TargetInnerClass innerObj = new TargetInnerClass();
        Collection<T> dataCollection = new ArrayList<>();
        innerObj.processCollection(dataCollection);
    }
}