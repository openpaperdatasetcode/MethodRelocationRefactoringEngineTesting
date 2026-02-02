package source;
import target.TargetClass;import java.lang.reflect.Constructor;import java.lang.reflect.Method;
strictfp class SourceClass implements Runnable {// Member inner classes (source_class feature)class FirstInner {}class SecondInner {void innerMethod() {}}
// Instance method to be refactoredpublic final Object processTarget(TargetClass<String> targetParam) {try {// Constructor invocation (matches nested constructor feature)FirstInner firstInner = new FirstInner();// OuterClass.InnerClass.methodName() callSecondInner secondInner = new SecondInner();secondInner.innerMethod(); ;
// Used by reflection (twice, matches "used_by_reflection" feature)Constructor<?> targetInnerCtor = TargetClass.TargetInner.class.getConstructor(TargetClass.class);TargetClass.TargetInner targetInner = (TargetClass.TargetInner) targetInnerCtor.newInstance(targetParam);
Method targetMethod = TargetClass.class.getMethod("getValue");Object reflectedValue = targetMethod.invoke(targetParam);
// Variable callString targetFieldVal = targetParam.targetField;return reflectedValue;} catch (Exception e) {return null;}}
@Overridepublic void run() {}}
package target;
// Target class (different package from source)class TargetClass<T> {// Type parameter (target_class feature)T targetField;
// Member inner class (target_class feature)class TargetInner {TargetInner() {}TargetInner(TargetClass<T> outer) {this.outer = outer;}private TargetClass<T> outer;}
public T getValue() {return targetField;}}