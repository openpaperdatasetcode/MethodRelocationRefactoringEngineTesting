package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Type;import com.google.inject.TypeLiteral;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorTestAnno {}
// Super type for call_method's superTypeReferenceinterface SuperType {<T> void processTarget(TargetClass<T> target);}
// Others_class for call_methodclass CallOthersClass implements SuperType {@Overridepublic <T> void processTarget(TargetClass<T> target) {}}
/**
Javadoc for TargetClass (meets target_class target_feature)
Strictfp generic class with local inner class
@param <T> type parameter*/strictfp class TargetClass<T> {private T value;
public TargetClass(T value) {this.value = value;}
// Local inner class (target_class target_feature)public TargetClass<T> createLocalInner() {class LocalInner extends TargetClass<T> {public LocalInner(T value) {super(value);}}return new LocalInner(value);}
public T getValue() {return value;}}
// Source: public generic class (two anonymous inner classes)public class SourceClass<T> {private TargetClass<T> targetField; // Contains target field (meets per_condition)private String outerPrivateField = "outerPrivate"; // For access_outer_private feature
@RefactorTestAnno // Has annotationprivate int instanceMethod(TargetClass<T>... targets) { // Base type return (int)int count = 0;
// Enhanced for statementfor (TargetClass<T> target : targets) {// Variable callvariableCall(target);
// Access outer private fieldcount += outerPrivateField.length();
// Switch statementswitch (target.getValue().toString().length()) {case 3:count++;break;default:count--;}
// Depends on target's local inner class (target_inner_rec)TargetClass<T> innerTarget = target.createLocalInner();}
// Public TypeLiteral (numbers: 1)public TypeLiteral<TargetClass<T>> typeLiteral = new TypeLiteral<TargetClass<T>>() {};Type type = typeLiteral.getType();
// Anonymous inner class 1 (source_class feature)Runnable runnable1 = new Runnable() {@Overridepublic void run() {overloadMethod(targets[0]);}};runnable1.run();
// Anonymous inner class 2 (source_class feature)Runnable runnable2 = new Runnable() {@Overridepublic void run() {overloadMethod(targets[0], "extra");}};runnable2.run();
// Object initialization (pos for call_method)SuperType superTypeRef = new CallOthersClass();superTypeRef.processTarget(targets[0]); // superTypeReference.methodName(arguments)
return count; // No new exception}
// Overloaded methods (overload_exist feature)private void overloadMethod(TargetClass<T> target) {}private void overloadMethod(TargetClass<T> target, String extra) {}
private void variableCall(TargetClass<T> target) {TargetClass<T> local = target;local.createLocalInner();}}