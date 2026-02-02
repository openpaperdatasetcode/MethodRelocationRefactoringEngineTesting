package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
abstract class TargetClass {protected TargetClass() {new Runnable() {@Overridepublic void run() {System.out.println("Target initialized");}}.run();}
public abstract String getValue();}
class SourceClass<T extends Number> {private String outerPrivate = "private_value";static class StaticNested {}
class SourceInner {class NestedInner {List<String> process(TargetClass target) {
class LocalInner {
String processData(U data) {
return data.toString() + outerPrivate;
}
}
LocalInner local = new LocalInner();
Object varCall = local.processData("local_");
List<String> result = new ArrayList<>();result.add(target.getValue());result.add(outerPrivate);
assert result.size() > 0 : "Result must not be empty";
try {Method method = TargetClass.class.getMethod("getValue");result.add(method.invoke(target).toString());} catch (Exception e) {// No new exception thrown}
return result;}}}}
class ConcreteTarget extends TargetClass {private String value;
public ConcreteTarget(String value) {super();this.value = value;}
@Overridepublic String getValue() {return value;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3071 {@Testpublic void testInstanceMethod() {SourceClass<Integer> source = new SourceClass<>();SourceClass.SourceInner inner = source.new SourceInner();SourceClass.SourceInner.NestedInner nested = inner.new NestedInner();TargetClass target = new ConcreteTarget("test_value");
List<String> result = nested.process(target);assertEquals(3, result.size());}}