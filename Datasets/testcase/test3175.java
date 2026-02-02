import java.util.function.Function;
interface Processable<T> {}
abstract class SourceClass<T> implements Processable<T> {static class StaticNested1 {}static class StaticNested2 {}
class MemberInner {/**
Method javadoc: Processes TargetClass instance, returns count of valid fields
@param target Abstract TargetClass instance to process
@return int count of processed fields*/public int process(TargetClass<T> target) {super();if (target == null) {throw new IllegalArgumentException("Target cannot be null");}
// Uses outer this (SourceClass.this) + access instance fieldtarget.instanceField = SourceClass.this.getDefaultValue();int count = target.instanceField != null ? 1 : 0;
// Variable call + depends on inner class (anonymous)target.executeInnerAction();
// Call source protected constructor via lambda (functional interfaces pos)Function<T, Object> constructorCall = (val) -> new SourceHelper(val);constructorCall.apply(target.instanceField);
return count;}}
protected T getDefaultValue() {return null;}
protected class SourceHelper {public SourceHelper(T data) {}}}
abstract class TargetClass<T> extends ParentTarget<T> {T instanceField;
void executeInnerAction() {// Anonymous inner classRunnable action = () -> System.out.println("Processing: " + instanceField);action.run();}}
abstract class ParentTarget<T> {}
