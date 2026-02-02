package com.refactoring.test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

// Source record class (default modifier, same package, static nested + local inner class)
record SourceRecord(String recordField) {
    // Static nested class (source feature)
    static class SourceStaticNested {
        // Inner recursive class (source_inner_rec)
        class SourceInnerRecursive {
            // Method to be refactored (varargs, Object return, private access)
            private Object moveMethod(TargetRecord... targetParams) {
                // Per_condition: contains target parameter (targetParams)
                if (targetParams == null || targetParams.length == 0) {
                    return new ArrayList<String>();
                }

                // ThrowStatement (private modifier, this.field, 1, pos=source)
                private void throwStatement(TargetRecord target) {
                    if (target.data().length() < 1) { // 1 from target_feature
                        throw new IllegalArgumentException("Field length < 1: " + target.data()); // this.field (target.data())
                    }
                }

                // For statement
                List<String> resultList = new ArrayList<>();
                for (int i = 0; i < targetParams.length; i++) {
                    TargetRecord target = targetParams[i];
                    // Variable call
                    String varCall = target.data();
                    resultList.add(varCall + "_" + i);

                    // Invoke ThrowStatement
                    throwStatement(target);

                    // Used by reflection
                    try {
                        Method innerMethod = TargetRecord.TargetMemberInner.class.getMethod("process", String.class);
                        TargetRecord.TargetMemberInner inner = target.new TargetMemberInner();
                        String reflectedVal = (String) innerMethod.invoke(inner, varCall);
                        targetParams[i] = new TargetRecord(reflectedVal);
                    } catch (Exception e) {
                        // No new exception (handle reflection exceptions internally)
                    }

                    // Object initialization with call_method (pos:object initialization)
                    TargetSubRecord subRecord = new TargetSubRecord(target.data(), 
                        TargetSubRecord.callMethod(target, 1)); // new ClassName().method()
                    resultList.addAll(subRecord.callMethod(target, "ref_" + varCall)); // overloading
                }

                // Local inner class (source feature)
                class LocalInnerProcessor {
                    Object aggregateResults(List<String> list) {
                        return String.join(",", list);
                    }
                }

                // No new exception
                return new LocalInnerProcessor().aggregateResults(resultList);
            }
        }
    }
}

// Target record class (private modifier, member inner class feature)
private record TargetRecord(String data) {
    // Member inner class (target_feature)
    public class TargetMemberInner {
        public String process(String input) {
            return input + "_processed_by_inner";
        }
    }
}

// Subclass for call_method (sub_class type)
class TargetSubRecord extends TargetRecord {
    public TargetSubRecord(String data) {
        super(data);
    }

    // Overloaded constructor for object initialization
    public TargetSubRecord(String data, List<String> dummy) {
        super(data);
    }

    // call_method (synchronized modifier, overloading + new ClassName().method())
    synchronized static List<String> callMethod(TargetRecord target, int num) {
        // new ClassName().method()
        TargetRecord.TargetMemberInner inner = new TargetRecord(target.data()).new TargetMemberInner();
        List<String> list = new ArrayList<>();
        list.add(inner.process(target.data()) + "_" + num);
        return list;
    }

    // Overloading method (target_feature:overloading)
    synchronized List<String> callMethod(TargetRecord target, String str) {
        TargetRecord.TargetMemberInner inner = target.new TargetMemberInner();
        List<String> list = new ArrayList<>();
        list.add(inner.process(str));
        return list;
    }
}