package same.pkg;
import java.util.function.Function;
protected class Source {protected void instanceMethod(Target targetParam) {// ConstructorInvocation in inner classclass Inner {public Target.Inner.Rec rec;
Inner() {public rec = new Target.Inner.Rec();if (rec.field == 1) {}}}
// Constructor invocationTarget.Inner inner = targetParam.new Inner();
// If statementif (targetParam.field > 0) {System.out.println("Positive");}
// TypeMethodReference with numbers=3Function<String, Integer> func = Integer::parseInt;int num = func.apply("3");
// Super keywordssuper.toString();
// Variable callTarget.Inner.Rec varCall = inner.new Rec();int val = varCall.value;}}
abstract class Target {int field;
class Inner {record Rec() {int field = 1;int value = 3;}}
void methodWithLocal() {class LocalInner {}}}