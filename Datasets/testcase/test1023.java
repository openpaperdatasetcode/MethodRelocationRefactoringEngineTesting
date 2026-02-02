package refactoring.test;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.function.Function;

// Other class for method_feature: others_class
class OtherClass {
    int value = 2;

    TargetRecord process(TargetRecord record) {
        return record;
    }
}

// Source class: record, public modifier, same package as target, features: two member inner classes
public record SourceRecord(String id, int value) {
    // Per condition: source contains target class field
    private final TargetRecord targetField = new TargetRecord("target-data", 2);

    // First member inner class (source_class feature)
    public class FirstMemberInner {
        <T extends Number & Comparable<T>> T processValue(T val) {
            return val;
        }
    }

    // Second member inner class (source_class feature)
    public class SecondMemberInner {
        void helperMethod() {}
    }

    // Method to be refactored: varargs, return void, public access, position source
    public void moveMethod(Object... args) throws SQLException {
        // Variable call feature
        int localVar = 2;
        FirstMemberInner firstInner = new FirstMemberInner();
        SecondMemberInner secondInner = new SecondMemberInner();

        // With_bounds feature (type parameter with bounds)
        Integer boundedVal = firstInner.processValue(localVar);

        // Expression statement feature
        String exprStmt = "Processing varargs with count: " + args.length;
        System.out.println(exprStmt);

        // Do-while loop (pos: do-while) with constructor feature
        int counter = 0;
        do {
            // Constructor feature (type: constructor, modifier: private, method_feature: 2, others_class, constructor, instanceReference::methodName)
            OtherClass otherObj = new OtherClass();
            TargetRecord targetInstance = otherObj.process(targetField);
            Function<TargetRecord, TargetRecord> func = otherObj::process; // instanceReference::methodName
            targetInstance = func.apply(targetInstance);

            counter++;
            if (counter == 2) { // method_feature: 2
                continue; // Continue statement feature
            }
        } while (counter < 5);

        // SQLException feature (declares throws, no new exception thrown)
        if (args == null) {
            throw new SQLException("Varargs cannot be null");
        }

        // Used_by_reflection feature
        try {
            Method method = TargetRecord.class.getDeclaredMethod("getLocalInnerData", int.class);
            method.invoke(targetField, localVar);
        } catch (Exception e) {
            // No new exception thrown (no_new_exception feature)
            e.printStackTrace();
        }
    }
}

// Target class: record, default modifier, same package, target_feature: local inner class
record TargetRecord(String data, int num) {
    public Object getLocalInnerData(int value) {
        // Local inner class (target_feature)
        class TargetLocalInner {
            String combineData(int val) {
                return data + "-" + num + "-" + val;
            }
        }
        TargetLocalInner localInner = new TargetLocalInner();
        return localInner.combineData(value);
    }
}