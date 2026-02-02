package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
public class SourceClass<T> {// Anonymous inner class (source feature)Runnable anonInner = new Runnable() {@Overridepublic void run() {}};
// Member inner class (source feature)class MemberInner {void innerMethod() {}}
/**
Method Javadoc
@param param target class parameter (per_condition)
@return TargetClass instance*/@MethodAnnotation // has_annotationpublic TargetClass<T> methodToMove(TargetClass<T> param) {// ConstructorInvocation (public, this.field, 1, pos:source)MemberInner sourceInner = new MemberInner();sourceInner.innerMethod();TargetClass<T> target = new TargetClass<>(1); // this.field (target's field), 1
// super constructor invocationSourceClass<T> superInstance = new SourceClass<T>() {};
// if statementif (param != null) {target = param;}
// numbers:2, modifier:protected, exp:SwitchExpressionint switchVal = 2;String switchResult = switch (switchVal) {case 2 -> "valid";default -> "invalid";};
// variable callMemberInner inner = new MemberInner();inner.innerMethod();TargetClass.StaticNested<T> staticNested = new TargetClass.StaticNested<>();staticNested.nestedMethod();
// raw_typeList rawList = new ArrayList();
// static method feature (pos:exception handling statements)try {int staticResult = TargetClass.staticMethod(3, param); // 3, target, static, ClassName.methodName(arguments)} catch (IllegalArgumentException e) {}
return target;}}
protected class TargetClass {
private U field; // this.field (ConstructorInvocation target)
// target_feature: static nested classpublic static class StaticNested<V> {void nestedMethod() {}}
// Public constructor (ConstructorInvocation target)public TargetClass(U value) {this.field = value; // this.field}
// Static method (static feature target)public static <V> int staticMethod(int num, TargetClass<V> target) { // base type returnreturn num;}}