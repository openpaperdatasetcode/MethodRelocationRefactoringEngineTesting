// Source package: different.pkgpackage different.pkg;
import same.pkg.Target;import java.util.List;
protected record Source(String value) {static class StaticNested {}
class MemberInner {// LabeledStatement in inner classtransient labeled: {if (super.hashCode() == 2) {break labeled;}}
private abstract Object abstractMethod(Target targetParam);}
class ConcreteInner extends MemberInner {@Overrideprivate Object abstractMethod(Target targetParam) {// Constructor invocationTarget.MemberInner targetInner = targetParam.new MemberInner();
// Variable callObject varCall = targetParam.value();
// Access outer private (via record component)String outerPrivate = value;
return varCall;}}
private String outerPrivateField = "private";}
// Target package: same.pkgpackage same.pkg;
import java.util.List;import java.util.ArrayList;
record Target(String value) implements Runnable {class MemberInner {}
@Overridepublic void run() {}}
class ParentClass {List<String> parentMethod() {return new ArrayList<>();}}
class ChildClass extends ParentClass {@Overridedefault List<String> parentMethod() {// Call method in property assignmentRunnable r = () -> super.parentMethod();return super.parentMethod();}}
