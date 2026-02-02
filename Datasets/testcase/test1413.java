package test.refactoring.movemethod;
import java.io.IOException;import java.lang.reflect.Method;
class SuperClass {public SuperClass(String arg) {}public void superMethod() {}}
final class SourceClass extends SuperClass {private TargetClass targetField = new TargetClass() {};
public static class MemberInnerClass {}
public SourceClass() {super("superArg");}
protected Object sourceMethod() throws IOException {// Super constructor invocation (implicit in class inheritance, explicit super() in constructor)// Type declaration statementclass LocalType {}LocalType localType = new LocalType();
// For statementfor (int i = 0; i < 1; i++) {}
// Assert statementassert targetField != null : "Target field must not be null";
// Public modifier + SuperMethodReference + numbers:1Runnable superRef = SuperClass::superMethod;superRef.run();
// Variable callString var = "test";var.length();
// Used by reflectiontry {Method method = TargetClass.class.getMethod("targetMethod");method.invoke(targetField);} catch (Exception e) {throw new IOException(e);}
return new Object();}
public void outerMethod() throws IOException {// Local inner classclass LocalInnerClass {public void innerMethod() throws IOException {sourceMethod();}}LocalInnerClass inner = new LocalInnerClass();inner.innerMethod();
// Call method position: if/else conditionsif (targetField.isValid()) {TargetClass result = targetField.targetMethod("arg1", 1);} else {TargetClass result = targetField.targetMethod("arg2", 2);}}}
/**
Javadoc for TargetClass
Target feature: javadoc*/abstract class TargetClass {public boolean isValid() {return true;}
/**
Static nested class (target feature)
*/
public static class StaticNestedClass {}
// Target method: overriding + this.methodName(arguments) + public modifierpublic TargetClass targetMethod() {return this;}
@Overridepublic TargetClass targetMethod(String arg, int num) {if (num == 1) {return this.targetMethod();} else {return new TargetClass() {};}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoringTest5040 {@Testpublic void testMoveNormalMethodFromSourceToTarget() throws IOException {SourceClass source = new SourceClass();source.outerMethod();
TargetClass target = new TargetClass() {@Overridepublic Object sourceMethod() throws IOException {class LocalType {}LocalType localType = new LocalType();
for (int i = 0; i < 1; i++) {}
assert this != null : "Target field must not be null";
Runnable superRef = SuperClass::superMethod;superRef.run();
String var = "test";var.length();
try {Method method = TargetClass.class.getMethod("targetMethod");method.invoke(this);} catch (Exception e) {throw new IOException(e);}
return new Object();}};
assertNotNull(target.sourceMethod());TargetClass callResult = target.targetMethod("testArg", 1);assertEquals(target, callResult);}}