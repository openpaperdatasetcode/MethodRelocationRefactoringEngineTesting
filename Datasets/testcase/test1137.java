import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Super interface for source_class implements feature
interface RecordProcessable {
    void process(AbstractTargetRecord param);
}

// Others class for generic method_feature: others_class
class OthersClass<U> {
    protected List<String> helper(U val, int num) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(val.toString() + "_others_" + i);
        }
        return list;
    }
}

// Source record class (protected modifier, same package, implements, static nested class, anonymous inner class)
protected record SourceRecord(String data) implements RecordProcessable {
    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass {
        public static String format(String s) {
            return s.toUpperCase();
        }
    }

    // Anonymous inner class (source_class feature)
    private final Consumer<String> anonymousInner = new Consumer<String>() {
        @Override
        public void accept(String s) {
            System.out.println("Anonymous inner: " + s);
        }
    };

    // Inner class for call_method (inner_class type)
    public class InnerCallClass extends OthersClass<String> {
        // Call method (instance, superTypeReference.methodName(arguments), return void)
        public void callMethod(AbstractTargetRecord param) {
            // superTypeReference.methodName(arguments)
            List<String> superResult = super.helper(param.value(), 2);
            anonymousInner.accept(superResult.toString());
        }
    }

    // Method to be refactored (instance, void return, private access, position: source)
    @Override
    private void process(AbstractTargetRecord param) { // per_condition: target parameter
        // Type declaration statement
        String targetFieldVal;
        List rawList; // raw_type
        int num;

        // VariableDeclarationStatement (private modifier, obj.field, 2, pos: source)
        private void varDeclBlock() {
            targetFieldVal = param.value(); // obj.field
            num = 2; // target_feature: 2
            rawList = new ArrayList(); // raw_type
            rawList.add(targetFieldVal + "_" + num);
        }
        varDeclBlock();

        // Expression statement
        targetFieldVal = StaticNestedSourceClass.format(targetFieldVal); // expression statement

        // Generic method call in object initialization (pos: object initialization, 2, others_class, generic, super.methodName(arguments))
        InnerCallClass innerObj = new InnerCallClass(); // object initialization
        List<String> genericResult = innerObj.<String>helper(targetFieldVal, 2); // 2, others_class, generic

        // Synchronized statement
        synchronized (this) {
            // Variable call
            param.updateValue(targetFieldVal + "_sync");
            // Call method in expression (pos: expression)
            innerObj.callMethod(param); // inner_class, public, instance, superTypeReference.methodName(arguments)
        }

        // No new exception
        System.out.println(genericResult + " | " + rawList);
    }
}

// Target abstract record class (abstract modifier, local inner class target_feature)
abstract sealed class AbstractTargetRecord permits ConcreteTargetRecord { // abstract record (Java 16+)
    public abstract String value();
    public abstract void updateValue(String newValue);

    // Local inner class (target_feature)
    public void processValue() {
        class TargetLocalInnerClass {
            String format(String s) {
                return s + "_local_inner";
            }
        }
        updateValue(new TargetLocalInnerClass().format(value()));
    }
}

// Concrete implementation of abstract target record
final class ConcreteTargetRecord extends AbstractTargetRecord {
    private String value;

    public ConcreteTargetRecord(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return this.value;
    }

    @Override
    public void updateValue(String newValue) {
        this.value = newValue;
    }
}