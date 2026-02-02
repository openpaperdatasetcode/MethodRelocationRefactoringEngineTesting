import java.util.ArrayList;
import java.util.List;

// Annotation for has_annotation feature
@interface OverrideAnnotation {}

// Source class: normal, default modifier, same package, no additional features
class SourceClass {
    // Satisfy per_condition: source contains target class field
    private TargetClass targetField = new TargetClass() {};

    // Source inner recursive structure (method_position: source_inner_rec)
    public class SourceInnerRec {
        // Overriding method (method type: overriding), base type (int) return, protected access
        @OverrideAnnotation // has_annotation feature (duplicated as per spec)
        @OverrideAnnotation
        @Override
        protected int toString() {
            // Variable call feature
            int localVar = 1;
            String varStr = "innerRec";

            // Constructor invocation feature
            SourceInnerHelper innerHelper = new SourceInnerHelper();

            // Super constructor invocation feature
            class NestedClass extends SourceInnerRec {
                public NestedClass() {
                    super(); // super constructor invocation
                }
            }
            new NestedClass();

            // Depends on inner class feature
            List<String> helperResult = innerHelper.processData(varStr);

            // Overloading feature: protected modifier, 1, inner_class, overloading, super.methodName(), ternary pos, List<String> return
            List<String> overloadResult = (localVar > 0) ? 
                this.overloadingMethod(1, innerHelper) : 
                this.overloadingMethod(1); // ternary operators pos + overloading
            localVar += overloadResult.size();

            // Overload exist feature (overloaded method exists)
            localVar += this.overloadingMethod(1, varStr).size();

            // Call method: inner_class type, protected modifier, constructor, this.methodName(arguments), array initialization pos, String return
            String[] strArray = {this.callMethod("initArg")}; // array initialization pos
            localVar += strArray[0].length();

            // No new exception feature (no 'new Exception()' statements)
            return localVar;
        }

        // Overloading method 1 (matches type/modifier/method_feature/pos/return_type)
        protected List<String> overloadingMethod(int num, SourceInnerHelper inner) { // 1, inner_class, overloading
            return super.toString() != null ? inner.getData() : new ArrayList<>(); // super.methodName()
        }

        // Overloading method 2 (overload variant - overload_exist feature)
        protected List<String> overloadingMethod(int num) {
            return new ArrayList<>();
        }

        // Overloading method 3 (another overload variant - overload_exist feature)
        protected List<String> overloadingMethod(int num, String str) {
            List<String> list = new ArrayList<>();
            list.add(str + "_" + num);
            return list;
        }

        // Call method implementation (inner_class type, protected modifier, constructor, this.methodName(arguments))
        protected String callMethod(String arg) {
            // Constructor feature (call inner class constructor)
            SourceInnerHelper helper = new SourceInnerHelper();
            // this.methodName(arguments)
            return this.processArg(helper, arg);
        }

        private String processArg(SourceInnerHelper helper, String arg) {
            return arg + "_processed_" + helper.getData().size();
        }

        // Inner helper class for depends_on_inner_class feature
        private class SourceInnerHelper {
            public List<String> processData(String input) {
                List<String> list = new ArrayList<>();
                list.add(input + "_helper");
                return list;
            }

            public List<String> getData() {
                return new ArrayList<>();
            }
        }
    }
}

/**
 * TargetClass - abstract modifier, javadoc + extends + anonymous inner class target_feature
 * Extends ParentClass to satisfy "extends" target_feature
 */
abstract class TargetClass extends ParentClass {
    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class execution");
        }
    };

    // Target class for method relocation
    public static class target {
        // Placeholder for moved overriding method
        @OverrideAnnotation
        @Override
        protected int toString() {
            SourceClass source = new SourceClass();
            SourceClass.SourceInnerRec innerRec = source.new SourceInnerRec();
            return innerRec.toString();
        }
    }
}

// Parent class for TargetClass "extends" feature
class ParentClass {}