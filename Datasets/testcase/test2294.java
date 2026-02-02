package source;
import target.TargetClass;import java.util.function.Consumer;
public abstract class SourceClass {private int outerPrivate;int x;
class MemberInner {public void normalMethod() {super.toString();}}
{new Runnable() {};}
TargetClass instanceMethod(TargetClass target) {if (target == null) {throw new NullPointerException();}
variableCall = target.field;outerPrivate = variableCall;target.instanceMethod();dependsOnInner = target.new InnerClass();
TargetClass obj = new TargetClass() {{new MemberInner().normalMethod();}};
Consumer<TargetClass> consumer = t -> System.out.println(SourceClass.this.x);return obj;}
int variableCall;TargetClass.InnerClass dependsOnInner;}
package target;
interface MyInterface {}
abstract class TargetClass implements MyInterface {protected int field;
class InnerClass {}
void instanceMethod() {}
void methodWithLocal() {class LocalInner {}}}