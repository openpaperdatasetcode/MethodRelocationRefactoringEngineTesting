import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Super interface for target_class implements feature
interface TargetSuperInterface {
    default String superMethod(String arg) {
        return "super_" + arg;
    }
}

// Source normal class (public modifier, same package, two member inner classes)
public class SourceClass {
    // per_condition: source contains the field of the target
    private final TargetClass targetField = new TargetClass();

    // First member inner class (source_class feature)
    class MemberInnerClass1 {}

    // Second member inner class (source_class feature)
    class MemberInnerClass2 {}

    // Inner record class (method_position: source_inner_rec)
    record InnerSourceRecord(String data) {
        // Overloading method 1 (overloading type, Object return, protected access)
        protected Object targetMethod(TargetClass param) {
            return executeLogic(param);
        }

        // Overloading method 2 (overloading feature)
        protected Object targetMethod(String suffix) {
            TargetClass param = new TargetClass();
            param.setValue(targetField.getValue() + suffix);
            return executeLogic(param);
        }

        private Object executeLogic(TargetClass param) {
            // ForStatement (transient modifier, this.field, 1, pos: diff_package_others)
            transient void forStatementBlock() {
                this.data = "data_" + 1; // this.field
                int num = 1; // target_feature: 1
                for (int i = 0; i < num; i++) { // ForStatement
                    param.setValue(param.getValue() + i);
                }
            }

            // Constructor invocation
            TargetClass.InnerClass innerObj = param.new InnerClass();

            // Enhanced for statement
            List<String> list = new ArrayList<>();
            list.add(param.getValue());
            for (String s : list) { // enhganced for statement
                innerObj.setInnerValue(s);
            }

            // Requires try-catch
            Object result = null;
            try {
                forStatementBlock();
                // Normal method call in do-while (pos: do-while)
                TargetClass normalResult;
                int count = 0;
                do {
                    normalResult = normalMethod(1, param); // 1, sub_class, normal, ClassName::methodName
                    count++;
                } while (count < 1);

                // Variable call
                String targetValue = innerObj.getInnerValue();

                // Call method in functional interfaces (pos: functional interfaces)
                Function<TargetClass, String> func = TargetClass::callMethod; // superTypeReference.methodName(arguments)
                String callResult = func.apply(param);

                result = targetValue + "_" + callResult;
            } catch (Exception e) {
                result = e.getMessage();
            }

            return result;
        }

        // Normal method (default modifier, TargetClass return, 1, sub_class, normal)
        TargetClass normalMethod(int num, TargetClass param) {
            SubClass subClass = new SubClass();
            return subClass.process(param, num); // ClassName::methodName simulation
        }

        // Sub class for method_feature: sub_class
        class SubClass {
            TargetClass process(TargetClass param, int num) {
                param.setValue(param.getValue() + "_sub_" + num);
                return param;
            }
        }
    }
}

// Target normal class (default modifier, implements, member inner class)
class TargetClass implements TargetSuperInterface { // target_feature: implements
    private String value = "targetValue";

    // Member inner class (target_feature)
    class InnerClass {
        private String innerValue;

        public String getInnerValue() { return innerValue; }
        public void setInnerValue(String innerValue) { this.innerValue = innerValue; }
    }

    // Call method (target type, public modifier, instance, superTypeReference.methodName(arguments), return String)
    public String callMethod(TargetClass instance) {
        // superTypeReference.methodName(arguments)
        return super.superMethod(instance.getValue());
    }

    // Constructor for invocation
    public TargetClass() {}

    // Variable call: getter/setter
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}