import java.util.List;
interface SourceInterface extends AnotherInterface {TargetInterface<String> targetField = new TargetInterface<>() {};
static class StaticNestedClass {void callRecursive() {SourceInterface source = new SourceInterface() {};source.recursiveMethod(3);}}
class MemberInnerClass {void useTarget() {TargetInterface.NestedStaticClass.staticField = 5;}}
protected void recursiveMethod(int n) {if (n <= 0) return;variableCall(TargetInterface.NestedStaticClass::staticMethod);recursiveMethod(n - 1);}
private void variableCall(Runnable runnable) {runnable.run();}}
interface AnotherInterface {}
interface TargetInterface<T> extends SuperTargetInterface {static class NestedStaticClass {static int staticField;static void staticMethod() {System.out.println(staticField);}}}
interface SuperTargetInterface {}
class ConstructorCaller {ConstructorCaller() {this(new SourceInterface.StaticNestedClass().new MemberInnerClass().callMethod());}
ConstructorCaller(Void v) {}
class MemberInnerClass {protected void callMethod() {SourceInterface source = new SourceInterface() {};TargetInterface.NestedStaticClass.staticMethod();source.new MemberInnerClass().useTarget();}}}
class SubInterface implements SourceInterface {@Overridepublic void recursiveMethod(int n) {}}
