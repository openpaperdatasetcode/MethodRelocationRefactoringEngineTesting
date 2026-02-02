package com.refactoring.test;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

// Parent class for call_method (parent_class type)
class ParentClass {
    public List<String> callMethod(TargetClass param) {
        return new ArrayList<>();
    }
}

// Source class (public modifier, same package, member inner + static nested class)
public class SourceClass extends ParentClass {
    // Static nested class (source feature)
    static class StaticNestedSource {
        // Member inner class (source feature)
        class MemberInnerClass {
            // Inner recursive class (source_inner_rec)
            class InnerRecursiveClass {
                // Method to be refactored (instance, base type return, default access)
                int moveMethod(TargetClass targetParam) {
                    // Source contains target's field (per_condition)
                    int targetField = targetParam.targetIntField;
                    // Variable call
                    int varCall = targetField;
                    // Expression statement
                    targetParam.targetIntField = varCall * 2;

                    // With bounds (generic with bounds)
                    class BoundedGeneric<T extends Number & Comparable<T>> {
                        T process(T val) {
                            return val;
                        }
                    }
                    BoundedGeneric<Integer> bounded = new BoundedGeneric<>();
                    int boundedVal = bounded.process(targetField);

                    // Requires try-catch & SQLException feature
                    try {
                        if (targetParam == null) {
                            throw new SQLException("Target parameter is null");
                        }
                        // Object initialization with nested instance method (pos: object initialization)
                        TargetClass initializedTarget = new TargetClass() {
                            {
                                innerInstanceMethod(2, SourceClass.this, SourceClass.super.callMethod(this));
                            }
                        };
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    // No new exception outside try-catch, return base type
                    return targetParam.targetIntField;
                }

                // Nested instance method (type:instance, modifier:default, method_feature:2/source/instance/superTypeReference.methodName)
                TargetClass innerInstanceMethod(int num, SourceClass source, List<String> superCall) {
                    TargetClass target = new TargetClass();
                    target.targetIntField = num;
                    // SuperTypeReference.methodName(arguments)
                    ParentClass.super.callMethod(target);
                    return target;
                }
            }
        }
    }

    // Overriding call_method (target_feature: overriding)
    @Override
    public List<String> callMethod(TargetClass param) {
        // Lambda: (parameters) -> methodBody (target_feature)
        return ((TargetClass t) -> {
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(t.targetIntField));
            return list;
        }).apply(param);
    }

    // Constructor with call_method in parameter list (pos: parameter list of constructors)
    public SourceClass() {
        super();
        new SourceClass(this.callMethod(new TargetClass()));
    }

    private SourceClass(List<String> dummy) {}
}

// Target class (public modifier, anonymous inner class feature)
public class TargetClass {
    int targetIntField;

    // Anonymous inner class (target_feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class: " + targetIntField);
        }
    };
}