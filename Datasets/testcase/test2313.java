package same.pkg;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
public class Source extends Parent {class MemberInner {}
Runnable anon = new Runnable() {@Overridepublic void run() {new Source().toString();}};
@Overrideprivate Object toString() {// ConstructorInvocationprivate Target.Inner.Rec rec = new Target.Inner.Rec(target.obj.field);if (target.obj.field == 1) {}
// Instance method in constructor parameter listTarget target = new Target(Parent::instanceMethod);
// Expression statementtarget.innerField = "value";
// Variable callObject varCall = target.new Inner().rec;
// Requires try-catchtry {target.throwIO();} catch (IOException e) {}
return varCall;}}
class Parent {Object objField;
public Object instanceMethod() {return objField;}}
protected class Target {Object innerField;OtherClass obj = new OtherClass();
Target(Object param) {}
class Inner {record Rec(Object val) {}Rec rec = new Rec(3);}
void throwIO() throws IOException {}
void methodWithLocal() {class LocalInner {}}}
class OtherClass {int field;
protected List<String> callMethod(Target target) {switch (1) {case 1:Runnable r = target.new Inner()::toString;return new ArrayList<>();default:return null;}}}