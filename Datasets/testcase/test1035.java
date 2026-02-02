import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

// Functional interface for target_class implements feature
interface TargetProcessable<T> {
    T process(T value);
}

// Super class for target_class extends feature
class TargetSuperClass<T> {
    protected T superField = (T) "TARGET_SUPER_FIELD_587X"; // super.field for TypeDeclarationStatement
}

// Annotation for call_method position (attribute values of annotations)
@Retention(RetentionPolicy.RUNTIME)
@interface CallMethodAnno {
    String value();
}

// Source non-sealed generic class (same package, type parameter, local inner class, static nested class)
non-sealed abstract class SourceClass<T extends CharSequence> {
    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass<U> {
        static String format(U val) {
            return val.toString().toUpperCase() + "_static_source";
        }
    }

    // Inner class (method_position: source_inner)
    public class InnerSourceClass<U> {
        // Base method for call_method overriding feature
        public String baseCallMethod(String val) {
            return val + "_base_call";
        }

        // Overriding method for call_method feature
        public String overriddenCallMethod(String val) {
            return super.baseCallMethod(val) + "_overridden";
        }

        // Constructor method (public modifier, 1, inner_class, constructor, instanceReference.methodName(arguments))
        public List<String> constructorMethod(PrivateTargetClass<T>.InnerRecord param) {
            int num = 1; // method_feature: 1
            // instanceReference.methodName(arguments)
            String instanceVal = this.baseCallMethod(param.getValue().toString() + "_" + num);
            
            // Constructor invocation (inner record)
            PrivateTargetClass<T>.InnerRecord newRec = new PrivateTargetClass.InnerRecord<>(
                (T) (param.getValue() + "_constructor_" + num)
            );
            
            List<String> result = new ArrayList<>();
            result.add(instanceVal);
            result.add(newRec.getValue().toString());
            return result;
        }

        // Abstract method to be refactored (abstract, base type return, public access)
        public abstract int processTarget(PrivateTargetClass<T>.InnerRecord param); // per_condition: target parameter
    }

    // Concrete implementation of abstract inner class
    public static <T extends CharSequence> InnerSourceClass<T> getInnerSourceInstance() {
        return new SourceClass<T>() {
            @Override
            public InnerSourceClass<T> createInnerClass() {
                return new InnerSourceClass<T>() {
                    @Override
                    public int processTarget(PrivateTargetClass<T>.InnerRecord param) {
                        // TypeDeclarationStatement (private modifier, super.field, 1, pos: diff_package_others)
                        private void typeDeclBlock() {
                            // diff_package_others position (logical separation)
                            class DiffPackageSimulator<U> {
                                void execute() {
                                    U superFieldVal = (U) param.getSuperField(); // super.field
                                    int num = 1; // target_feature: 1
                                    
                                    // Type declaration statement
                                    T typeVar = (T) (param.getValue() + "_type_decl_" + superFieldVal + "_" + num);
                                    param.setValue(typeVar);
                                }
                            }
                            new DiffPackageSimulator<T>().execute();
                        }

                        // Execute type declaration block
                        typeDeclBlock();

                        // numbers:1, modifier:default, exp:ArrayCreation
                        default String[] arrayCreation() {
                            int num = 1; // numbers:1
                            // ArrayCreation expression
                            String[] arr = new String[num];
                            arr[0] = param.getValue().toString();
                            return arr;
                        }

                        // for statement
                        int sum = 0;
                        for (String s : arrayCreation()) {
                            sum += s.length();
                            // expression statement
                            String exprVal = s + "_for_loop_" + sum; // expression statement
                            param.setValue((T) exprVal);
                        }

                        // switch statement
                        switch (sum % 3) {
                            case 1:
                                param.setValue((T) (param.getValue() + "_switch_case1"));
                                break;
                            default:
                                param.setValue((T) (param.getValue() + "_switch_default"));
                                break;
                        }

                        // Constructor method call in expression (pos: expression)
                        List<String> constructorResult = this.constructorMethod(param); // instanceReference.methodName(arguments)
                        sum += constructorResult.size();

                        // Call method in annotation attribute values (pos: the attribute values of annotations)
                        @CallMethodAnno(value = String.valueOf(
                            this.overriddenCallMethod(param.getValue().toString()) // overriding + outerInstance.new InnerClass().methodName()
                        ))
                        class AnnotationWrapper {}

                        // access_instance_method feature (call target instance method)
                        String instanceMethodResult = param.processWithAnonymousInner();
                        sum += instanceMethodResult.length();

                        // Variable call (target type parameter + extends/implements/anonymous inner class)
                        String staticVal = StaticNestedSourceClass.format(param.getValue());
                        param.setValue((T) (param.getValue() + "_variable_call_" + staticVal));

                        // No new exception
                        return sum;
                    }
                };
            }
        }.createInnerClass();
    }

    // Abstract method for outer generic class
    public abstract InnerSourceClass<T> createInnerClass();
}

// Target private generic class (extends, implements, anonymous inner class target_feature)
private class PrivateTargetClass<T> extends TargetSuperClass<T> implements TargetProcessable<T> {
    private T value;

    // Inner record (target_inner_rec)
    public record InnerRecord<T>(T value) {
        // Variable call: getter/setter
        public T getValue() { return value; }
        public void setValue(T value) { this.value = value; }
        
        // access super.field (target_feature)
        public T getSuperField() {
            return (T) new TargetSuperClass<T>().superField;
        }

        // access_instance_method + anonymous inner class feature
        public String processWithAnonymousInner() {
            // anonymous inner class (target_feature)
            TargetProcessable<T> processor = new TargetProcessable<T>() {
                @Override
                public T process(T value) {
                    return (T) (value + "_anonymous_inner_processed");
                }
            };
            return processor.process(this.value).toString();
        }
    }

    // Type parameter feature (target_feature)
    public PrivateTargetClass(T value) {
        this.value = value;
    }

    // Implement super interface method (implements feature)
    @Override
    public T process(T value) {
        return (T) (value + "_processed_by_interface");
    }
}