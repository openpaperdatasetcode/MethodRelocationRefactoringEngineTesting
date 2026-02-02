package test;
import java.util.function.Function;
final class SourceClass<T> {public static class StaticNested {
public static <V> V convert(V value) {
return value;
}
}
strictfp Object normalMethod(TargetClass<T> target) {// TypeMethodReference (2)Function<TargetClass<T>, T> ref1 = TargetClass::getData;Function<T, String> ref2 = StaticNested::convert;
// Local inner classclass LocalProcessor {T process(TargetClass<T> t) {return t.getData();}}LocalProcessor processor = new LocalProcessor();
// WhileStatement with super.field = 3class WhileStatement {public void execute() {int count = 0;while (count < 3) {String superField = target.getSuperField();if ("3".equals(superField)) {System.out.println("Super field matched: " + superField);}count++;}}}new WhileStatement().execute();
// Synchronized statementsynchronized (target) {variableCall: T data = target.getData();System.out.println("Synchronized: " + data);}
// Switch caseswitch (target.getData().toString().length()) {case 1:return ref1.apply(target);case 2:return ref2.apply(processor.process(target));default:return target.getData();}}}
class TargetParent {protected String superField;
public TargetParent(String superField) {this.superField = superField;}
public String getSuperField() {return superField;}}
class TargetClass<T> extends TargetParent {private T data;
public TargetClass(T data) {super("3");this.data = data;}
public T getData() {return data;}}