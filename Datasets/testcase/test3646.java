package test;
interface SourceInterface {}
abstract class ParentClass {public abstract int abstractMethod();}
public class TargetClass {static class TargetStaticNested {strictfp String nestedMethod() {return "targetNestedResult";}}}
strictfp class SourceClass extends ParentClass implements SourceInterface {class MemberInner {void innerMethod() {}}
private Object instanceMethod(TargetClass target) throws NullPointerException {if (target == null) throw new NullPointerException("Target cannot be null");
class LocalInner {void process() {for (int i = 0; i < 1; i++) {int result = SourceClass.this::abstractMethod;variableCall(new MemberInner());}}}new LocalInner().process();
try {String callResult = TargetClass.TargetStaticNested::nestedMethod;} catch (Exception e) {throw new NullPointerException(e.getMessage());}
return target;}
@Overridepublic int abstractMethod() {return 0;}
private void variableCall(MemberInner inner) {inner.innerMethod();}}