package test;
import java.lang.annotation.*;import java.lang.reflect.Method;import java.util.function.Supplier;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
public record SourceClass(String data) {class MemberInner {}
@MyAnnotationpublic void instanceMethod(AbstractTargetClass<?> target) {class LocalInner {}
variableCall();target.someMethod();
// Overloading methods in switch statementswitch (data) {case "a":Supplier<Object> sup1 = new SubTargetClass()::overloadedMethod;break;case "b":Supplier<Object> sup2 = new SubTargetClass()::overloadedMethod;break;case "c":Supplier<Object> sup3 = new SubTargetClass()::overloadedMethod;break;default:break;}
// Used by reflectiontry {Method method = SourceClass.class.getMethod("instanceMethod", AbstractTargetClass.class);} catch (NoSuchMethodException e) {}}
private void variableCall() {}}
abstract record AbstractTargetClass<T>(T value) {class MemberInner {}
void someMethod() {}}
record SubTargetClass() extends AbstractTargetClass<String> {protected Object overloadedMethod() { return new Object(); }protected Object overloadedMethod(int i) { return new Object(); }protected Object overloadedMethod(String s) { return new Object(); }}