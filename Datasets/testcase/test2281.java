package test;
import java.lang.reflect.Method;import java.util.function.Supplier;
abstract sealed class SourceClass<T> permits SourceSubClass<T> {class MemberInner {class InnerRec {strictfp Object overloadingMethod(TargetClass<?> target) {class TypeDecl {}super.toString();variableCall = target.field;
try {Method method = InnerRec.class.getMethod("overloadingMethod", TargetClass.class);method.invoke(this, target);} catch (Exception e) {}
if (target.field.equals("3")) {Supplier<Object> supplier = OthersClass::instanceMethod;}
return target;}
strictfp Object overloadingMethod(String str) {return null;}
String variableCall;}}
{new Runnable() {};}}
final class SourceSubClass<T> extends SourceClass<T> {}
class OthersClass {private Object instanceMethod() {return null;}}
private class TargetClass<S> {String field;class TargetInner {}}