package test;
import java.util.function.Consumer;
abstract class SourceClass {class MemberInner {default int varargsMethod(ProtectedTargetClass... targets) {class LocalInner1 {}class LocalInner2 {}
// Super constructor invocationclass SubClass extends ProtectedTargetClass {SubClass() {super();}}
// Access target instance fieldfor (ProtectedTargetClass target : targets) {int fieldVal = target.targetField;}
// Instance method from target in functional interfaceConsumer<ProtectedTargetClass> consumer = target -> target.instanceMethod();if (targets.length > 0) {consumer.accept(targets[0]);}
// Lambda expression: () -> System.out.println(this.value)Runnable printRunnable = () -> System.out.println(this.value);printRunnable.run();
variableCall();
return 0;}
private int value;private void variableCall() {}}}
protected abstract class ProtectedTargetClass {int targetField;
protected void instanceMethod() {}}