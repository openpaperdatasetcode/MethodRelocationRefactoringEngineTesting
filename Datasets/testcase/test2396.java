package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
class ParentClass {protected void parentMethod() {}protected void parentMethod(int a, int b, int c) {}}
class TargetClass<T> extends ParentClass {public static int staticField = 1;
class TargetInner {private T value;
public TargetInner(T value) {this.value = value;}
String getStringValue() {return value.toString();}}
public TargetClass() {super();}}
strictfp class SourceClass extends ParentClass {protected String outerProtected = "protected_val";static class StaticNested {}
public final List<String> process(TargetClass.TargetInner... inners) {List<String> result = new ArrayList<>();if (inners == null || inners.length == 0) {return result;}
// ExpressionStatement with ClassName.field and 1TargetClass.staticField = 1;Object varCall = TargetClass.staticField;
TargetClass<String> target = new TargetClass<>();TargetClass.TargetInner inner = target.new TargetInner("test");result.add(inner.getStringValue());
// Ternary operator with parent class methodRunnable runner = (inners.length > 0) ?() -> super.parentMethod(1, 2, 3) :() -> super.parentMethod();runner.run();
assert inners.length >= 1 : "At least one inner required";
for (TargetClass.TargetInner innerItem : inners) {result.add(innerItem.getStringValue());result.add(outerProtected);if (result.size() >= 5) {return result;}}
return result;}
{new Runnable() {@Overridepublic void run() {TargetClass<String> target = new TargetClass<>();process(target.new TargetInner("anon"));}}.run();}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3072 {@Testpublic void testVarargsMethod() {SourceClass source = new SourceClass();TargetClass<String> target = new TargetClass<>();List<String> result = source.process(target.new TargetInner("first"),target.new TargetInner("second"));assertTrue(result.size() >= 3);}}