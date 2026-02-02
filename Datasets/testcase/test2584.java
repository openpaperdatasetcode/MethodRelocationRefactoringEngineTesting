package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import java.util.function.Function;
abstract class ParentClass {private Object parentMethod1() {return "parent1";}
private Object parentMethod2() {return "parent2";}}
protected abstract class TargetClass extends ParentClass {static class TargetInner {class NestedInner {private int value;
public NestedInner(int v) {this.value = v;}
public Object getValue() {return value;}}}}
abstract class SourceClass extends ParentClass {private TargetClass targetField = new TargetClass() {};
class MemberInner {Object helper() {return "helper";}}
private Object sourceMethod() throws Exception {class LocalInner {Object process(TargetClass.TargetInner.NestedInner nested) {return nested.getValue();}}
TargetClass.TargetInner.NestedInner nested = targetField.new TargetInner().new NestedInner(5);LocalInner local = new LocalInner();Object varCall = local.process(nested);
List<TargetClass.TargetInner.NestedInner> list = new ArrayList<>();list.add(nested);
for (TargetClass.TargetInner.NestedInner item : list) {Function<TargetClass.TargetInner.NestedInner, Object> func1 = ParentClass::parentMethod1;Function<TargetClass.TargetInner.NestedInner, Object> func2 = ParentClass::parentMethod2;varCall = func1.apply(item) + func2.apply(item);}
Object result = this.anotherMethod();
Method method = TargetClass.TargetInner.NestedInner.class.getMethod("getValue");result = method.invoke(nested);
return result;}
private Object anotherMethod() {return new MemberInner().helper();}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3033 {@Testpublic void testMethod() throws Exception {SourceClass source = new SourceClass() {};Object result = source.sourceMethod();assertEquals(5, result);}}