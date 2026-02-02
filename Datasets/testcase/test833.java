package refactoring.test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

// Parent class for target and method_feature (parent_class)
class ParentClass {
    public static int staticField = 1; // ClassName.field + 1 for AssertStatement
    protected String parentData = "parentData";

    public String processData(String... args) {
        return String.join(",", args);
    }
}

// Target class: final modifier, extends ParentClass, static nested class (target_feature)
final class TargetClass extends ParentClass {
    public String targetField = "targetValue";

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public String nestedField = "nestedValue";

        public static TargetClass createTarget(String... args) {
            TargetClass target = new TargetClass();
            target.targetField = String.join("_", args);
            return target;
        }
    }

    public String getTargetField() {
        return targetField;
    }
}

// Source class: default modifier, extends, anonymous inner + member inner class (source_feature)
class SourceClass extends ParentClass {
    // Source contains target field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Member inner class (source_feature)
    class FirstInnerClass {
        // Inner recursive class (source_inner_rec - method_position)
        class SourceInnerRecursive {
            /**
             * Method Javadoc (method feature)
             * Normal method with List<String> return type, private access
             * @return List<String> processed result
             */
            private List<String> refactorMethod() {
                List<String> result = new ArrayList<>();

                // AssertStatement feature: private modifier, ClassName.field, 1, pos=source
                private void assertFeature() {
                    assert ParentClass.staticField == 1 : "Static field must be 1";
                }
                assertFeature();

                // Varargs method feature: public modifier, 3, parent_class, varargs, ClassName::methodName, pos=property assignment, return_type=TargetClass
                public TargetClass varargsHelperMethod(String... args) {
                    // ClassName::methodName (method reference)
                    Function<String[], String> processor = ParentClass::processData;
                    // Property assignment (pos)
                    String processed = processor.apply(Arrays.copyOf(args, 3)); // 3 for method_feature
                    TargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested();
                    nested.nestedField = processed; // Property assignment
                    return TargetClass.TargetStaticNested.createTarget(args);
                }

                // Expression statement feature
                String expr = targetField.getTargetField() + "_processed";
                result.add(expr);

                // ArrayCreation feature: numbers=2, private modifier, exp=ArrayCreation
                private String[] createArray() {
                    return new String[]{"elem1", "elem2"}; // 2 elements
                }
                result.addAll(Arrays.asList(createArray()));

                // Variable call feature
                String varCall = targetField.TargetStaticNested.nestedField;
                result.add(varCall);

                // Call varargs helper method
                TargetClass varargsResult = varargsHelperMethod("arg1", "arg2", "arg3");
                result.add(varargsResult.getTargetField());

                // call_method: inner_class type, default modifier, overriding + OuterClass.InnerClass.methodName(), pos=switch, return_type=void
                class InnerCallClass {
                    public void baseMethod() {
                        System.out.println("Base method");
                    }
                }
                class OverridingInner extends InnerCallClass {
                    @Override // overriding feature
                    public void baseMethod() {
                        System.out.println("Overridden method");
                    }
                }

                switch (result.size()) {
                    case 2:
                        SourceClass.FirstInnerClass.SourceInnerRecursive.InnerCallClass inner = new OverridingInner();
                        // OuterClass.InnerClass.methodName()
                        inner.baseMethod();
                        break;
                    default:
                        new InnerCallClass().baseMethod();
                }

                // No new exception thrown feature
                return result;
            }
        }

        // Anonymous inner class (source_feature)
        private Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                SourceInnerRecursive recursive = new SourceInnerRecursive();
                recursive.refactorMethod();
            }
        };
    }
}