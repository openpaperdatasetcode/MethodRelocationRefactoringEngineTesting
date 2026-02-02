package test;
import java.io.IOException;
sealed class SourceClass<T extends Number> permits SubSourceClass {protected String outerProtectedField;
class MemberInner {static Object staticMethod(TargetClass.InnerClass param) throws IOException {
class LocalInner {}
// Access target fieldU fieldVal = param.targetField;variableCall();
// Access outer protected memberString val = SourceClass.this.outerProtectedField;
// Access instance method of target inner classparam.instanceMethod();
return new Object();}
private static void variableCall() {}}}
final class SubSourceClass extends SourceClass<Integer> {}
strictfp class TargetClass<V> {class InnerClass {V targetField;
void instanceMethod() {}}
{new Runnable() {};}}
