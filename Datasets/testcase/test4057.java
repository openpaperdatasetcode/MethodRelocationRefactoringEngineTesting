package test;
import java.util.ArrayList;import java.util.List;
public class SourceClass {// Static nested class (source_class feature)static class SourceStaticHelper {// Protected normal method (matches method_feature "1" for source normal method)protected static Object processHelper(TargetClass<?> target) {return target.getValue() + "_processed";}}
// Local inner class (source_class feature)void createLocalInner() {class SourceLocalInner {void callVarargsMethod(TargetClass<?>... targets) {SourceClass.this.processTargets(targets);}}new SourceLocalInner().callVarargsMethod(new TargetClass<>("val1"), new TargetClass<>(2));}
// Varargs method to be refactoredpublic final Object processTargets(TargetClass<?>... targets) {// Labeled statementProcessing: {if (targets == null || targets.length == 0) {break Processing;}
// Object initialization (pos for helper method call)Object helperResult = SourceStaticHelper.processHelper(targets[0]); // ClassName.methodName(arguments)
// Raw type (matches "raw_type" feature)List rawList = new ArrayList();
for (TargetClass target : targets) { // Constructor invocation: create target's local inner class instance TargetClass.Processor processor = target.createProcessor();// Super constructor invocation (implicit in inner class initialization)
// Variable call: access target and its local inner class methodsObject targetVal = target.getValue();Object processedVal = processor.process(targetVal);rawList.add(processedVal);}
rawList.add(helperResult);return rawList;}return null;}}
public class TargetClass<T> {private T value;
// Constructor with super constructor invocation (implicit)public TargetClass(T value) {super();this.value = value;}
// Type parameter usage + local inner class (target_class features)public Processor<T> createProcessor() {// Local inner class (for "target_inner_rec")class TargetLocalProcessor implements Processor<T> {@Overridepublic T process(T input) {return input;}}return new TargetLocalProcessor();}
public T getValue() {return value;}
// Inner interface for local inner class implementationinterface Processor<T> {T process(T input);}}