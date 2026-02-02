package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
interface MyInterface {List<String> getValues();}
private class TargetClass {class TargetInner {private String name;
public TargetInner(String name) {this.name = name;}
public String getName() {return name;}}}
abstract class SourceClass implements MyInterface {private TargetClass target = new TargetClass();
class SourceInner {class NestedInner {// Override violation: MyInterface.getValues() has no parameters@Overridepublic List<String> getValues(TargetClass.TargetInner... inners) {List<String> result = new ArrayList<>();Object varCall = result;
if (inners == null || inners.length == 0) {return this.defaultValues();}
for (TargetClass.TargetInner inner : inners) {result.add(inner.getName());result.add(SourceClass.this.getOuterValue());}
return result;}
private List<String> defaultValues() {List<String> defaults = new ArrayList<>();defaults.add("default");return defaults;}}}
protected String getOuterValue() {return "outer_value";}
{new Runnable() {@Overridepublic void run() {SourceInner inner = new SourceInner();TargetClass.TargetInner targetInner = target.new TargetInner("test");inner.new NestedInner().getValues(targetInner);}}.run();}
// Required to implement MyInterface (override_violation context)@Overridepublic List<String> getValues() {return new ArrayList<>();}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3047 {@Testpublic void testVarargsMethod() {SourceClass source = new SourceClass() {};SourceClass.SourceInner inner = source.new SourceInner();SourceClass.SourceInner.NestedInner nested = inner.new NestedInner();TargetClass target = new TargetClass();
List<String> result = nested.getValues(target.new TargetInner("first"),target.new TargetInner("second"));
assertEquals(4, result.size());}}