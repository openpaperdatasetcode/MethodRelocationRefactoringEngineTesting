package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

// Super class for super keywords usage
class SuperSourceClass {
    protected String superField = "superValue6433";
}

// Source class: final, same package as target, local inner class, anonymous inner class
final class SourceClass extends SuperSourceClass {
    // Per_condition: source contains target class field
    private TargetClass.TargetInner targetField = new TargetClass().new TargetInner();

    // Member inner class
    class SourceInnerClass {
        // Recursive inner class (source_inner_rec - method position)
        class InnerRecursiveClass {
            // Method to refactor: varargs, List<String> return, private access, in source_inner_rec
            private List<String> methodToRefactor(Object... args) {
                List<String> result = new ArrayList<>();
                
                // Variable call (target inner class field)
                String targetValue = targetField.innerData;
                
                // Super keywords (access super class field)
                targetValue += SuperSourceClass.super.superField;
                
                // Expression statement
                result.add(targetValue);
                
                // Continue statement (within loop)
                for (int i = 0; i < 5; i++) {
                    if (i == 2) {
                        continue; // continue statement
                    }
                    result.add("loop_" + i);
                }

                // With_bounds feature (generic with multiple bounds)
                class BoundedGeneric<T extends Number & Comparable<T>> {
                    T value = (T) Integer.valueOf(1); // numbers=1 alignment
                }
                BoundedGeneric<Integer> boundedObj = new BoundedGeneric<>();
                result.add(boundedObj.value.toString());

                // Feature: numbers=1, modifier=default, exp=CreationReference
                default Supplier<TargetClass.TargetInner> creationReference() {
                    // CreationReference (constructor reference) - exp=CreationReference, numbers=1
                    Supplier<TargetClass.TargetInner> supplier = TargetClass.TargetInner::new; // 构造引用
                    result.add("creation_ref_" + 1); // numbers=1
                    return supplier;
                }
                creationReference();

                // Used by reflection feature
                try {
                    Method getMethod = TargetClass.TargetInner.class.getDeclaredMethod("getInnerData");
                    getMethod.setAccessible(true);
                    String reflectionValue = (String) getMethod.invoke(targetField);
                    result.add("reflection_" + reflectionValue);
                } catch (Exception e) {
                    // No new exception feature (no throw new exception)
                    result.add("reflection_error");
                }

                // Local inner class (source class feature)
                class LocalInnerClass {
                    void processResult() {
                        result.add("local_inner_processed");
                    }
                }
                new LocalInnerClass().processResult();

                // Anonymous inner class (source class feature)
                Runnable anonymousRunnable = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(result);
                    }
                };
                anonymousRunnable.run();

                // No new exception feature
                try {
                    Integer.parseInt(targetValue);
                } catch (NumberFormatException e) {
                    // No throw new exception, only handle
                    result.add("parse_error_" + targetValue);
                }

                return result;
            }
        }
    }
}

// Target class: public, same package as source, local inner class feature
public class TargetClass {
    // Target_inner (target inner class)
    public class TargetInner {
        String innerData = "targetInnerData1";

        public String getInnerData() {
            // Local inner class (target_feature)
            class TargetLocalInnerClass {
                String formatData(String input) {
                    return input + "_local_formatted";
                }
            }
            return new TargetLocalInnerClass().formatData(innerData);
        }
    }
}