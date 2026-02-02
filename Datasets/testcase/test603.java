package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Parent class for call_method (parent_class type)
class ParentClass {
    // call_method: default modifier, instance, superTypeReference.methodName(), pos=switch, return String
    String callMethod(SourceClass.InnerClass.RecursiveInnerClass innerObj, TargetClass targetParam) {
        String result = "";
        int switchCase = 2; // numbers=2 feature alignment

        // pos: switch statement
        switch (switchCase) {
            case 1:
                // superTypeReference.methodName(arguments) feature
                Object methodResult = ((SourceClass.InnerClass.RecursiveInnerClass) innerObj).methodToRefactor(targetParam);
                result = methodResult.toString();
                break;
            case 2:
                result = innerObj.methodToRefactor(targetParam).toString() + "_case2";
                break;
            default:
                result = "default";
        }
        return result;
    }
}

// Source class: private, same package as target, two anonymous inner classes
private class SourceClass {
    // Instance field for access_instance_field feature
    private String instanceField = "sourceInstanceField";

    // Member inner class
    class InnerClass {
        // Recursive inner class (source_inner_rec - method position)
        class RecursiveInnerClass extends ParentClass {
            // Abstract helper for numbers=2, modifier=abstract, exp=Name feature
            abstract static class AbstractNameHelper {
                protected String name = "Feature2"; // numbers=2, exp=Name
                abstract String getName();
            }

            // Method to refactor: instance, Object return, protected access, in source_inner_rec
            // Per_condition: contains target class parameter
            protected Object methodToRefactor(TargetClass targetParam) {
                // Variable call (target field's static nested class)
                String targetValue = targetParam.StaticNestedClass.nestedStaticField;
                
                // Access instance field
                targetValue += SourceClass.this.instanceField;

                // Accessor method feature: default modifier, 1, target, accessor, super.methodName(), pos=ternary operators, return List<String>
                default List<String> accessorHelperMethod(TargetClass param) {
                    List<String> list = new ArrayList<>();
                    int count = 1; // method_feature: 1
                    // pos: ternary operators
                    String value = (count > 0) ? param.getNestedValue() : "default";
                    // super.methodName(arguments) (call parent class method)
                    super.callMethod(this, param);
                    // accessor feature (getter call)
                    list.add(param.getNestedValue());
                    list.add(value);
                    return list;
                }
                List<String> accessorResult = accessorHelperMethod(targetParam);

                // numbers=2, modifier=abstract, exp=Name feature implementation
                AbstractNameHelper nameHelper = new AbstractNameHelper() {
                    @Override
                    String getName() {
                        return this.name + "_" + accessorResult.size();
                    }
                };
                targetValue += nameHelper.getName();

                // First anonymous inner class (source class feature)
                Runnable anonymous1 = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Anonymous 1: " + targetValue);
                    }
                };
                anonymous1.run();

                // Second anonymous inner class (source class feature)
                Runnable anonymous2 = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Anonymous 2: " + accessorResult);
                    }
                };
                anonymous2.run();

                // No new exception feature
                try {
                    Integer.parseInt(targetValue);
                } catch (NumberFormatException e) {
                    // No throw new exception, only handle
                    targetValue = "formatted_" + targetValue;
                }

                return targetValue; // Object return
            }
        }
    }
}

// Target class: public, same package as source, static nested class feature
public class TargetClass {
    // Static nested class (target_feature)
    public static class StaticNestedClass {
        public static String nestedStaticField = "targetStaticField";
    }

    // Accessor method for accessor feature
    public String getNestedValue() {
        return StaticNestedClass.nestedStaticField;
    }
}