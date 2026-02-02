package test;
import java.util.function.Supplier;
sealed class SourceClass extends ParentClass implements Runnable permits SourceSubClass {static class SourceStaticNested {}class SourceMemberInner {}
static {Supplier<Object> supplier = ParentClass::getAccessor;}
private int localVar;
public void moveMethod(TargetClass target, String... args) {super();this.localVar = 0;
int field = target.targetField;target.new TargetInner().doSomething();
OtherClass other = new OtherClass() {{int val = new HelperClass().calculate();}};}
@Overridepublic void run() {}}
non-sealed class SourceSubClass extends SourceClass {}
class ParentClass {protected Object getAccessor() {return null;}}
protected class TargetClass {int targetField;
class TargetInner {void doSomething() {}}}
class OtherClass {}
class HelperClass {final int calculate() {return 42;}}