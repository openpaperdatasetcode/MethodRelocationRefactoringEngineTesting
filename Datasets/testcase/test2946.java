package sourcepkg;
import targetpkg.TargetInterface;import java.util.function.Supplier;
interface SourceInterface permits SourceImpl {class MemberInner {}
final Object process(TargetInterface<String> targetParam) {// Local inner class in source interfaceclass LocalInnerSource {}
// Super constructor invocation (via anonymous class)TargetInterface.InnerRec rec = new TargetInterface.InnerRec() {};
// Switch statementString type = targetParam.getType();switch (type) {case "A" -> rec.methodA();case "B" -> rec.methodB();default -> rec.methodC();}
// 3 default MethodInvocation expressionstargetParam.defaultMethod1();targetParam.defaultMethod2();targetParam.defaultMethod3();
// Functional interface with private instance methodSupplier<TargetInterface<String>> supplier = () -> {return targetParam.privateInnerMethod();};
// Variable call and requires_try_catchtry {LocalInnerSource local = new LocalInnerSource();OthersClass.outerInstance.new OthersInner().callMethod();} catch (Exception e) {}
// Overload exist (target has overloaded methods)targetParam.overloadedMethod(1);targetParam.overloadedMethod("test");
return new Object();}}
class SourceImpl implements SourceInterface {}
package targetpkg;
import java.util.function.Consumer;
public interface TargetInterface<T> {record InnerRec() {public void methodA() {}public void methodB() {}public void methodC() {}}
// Local inner class in target interfacedefault void targetLocalInner() {class LocalInnerTarget {}}
// Default methods (3 MethodInvocation targets)default void defaultMethod1() {}default void defaultMethod2() {}default void defaultMethod3() {}
// Overloaded methodsvoid overloadedMethod(int num);void overloadedMethod(String str);
// Private instance method returning TargetClass Typeprivate TargetInterface<T> privateInnerMethod() {return this;}
String getType();}
package targetpkg;
public class OthersClass {public static OthersClass outerInstance = new OthersClass();
public class OthersInner {public void callMethod() {}}
public void doWhileCall() {do {outerInstance.new OthersInner().callMethod();} while (false);}}