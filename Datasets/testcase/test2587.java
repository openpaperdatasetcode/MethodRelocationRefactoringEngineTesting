package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
class SuperClass {protected int superField = 2;}
protected class TargetClass extends SuperClass {public TargetClass() {class LocalInTarget {void init() {superField = 2;}}new LocalInTarget().init();}}
abstract class SourceClass<T> {private TargetClass targetField = new TargetClass();class MemberInner1 {}class MemberInner2 {}
@Overridepublic List<String> toString() {List<String> result = new ArrayList<>();Object varCall = targetField.superField;
assert targetField.superField == 2 : "Super field should be 2";
int count = 0;while (count < targetField.superField) {result.add(String.valueOf(count));count++;}
result.forEach(s -> {try {Method method = SourceClass.class.getMethod("synchronizedMethod");method.invoke(SourceClass.this);} catch (Exception e) {// No new exception thrown}});
return result;}
public synchronized int synchronizedMethod() {return targetField.superField * 2;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3037 {@Testpublic void testOverridingMethod() {SourceClass<String> source = new SourceClass<>() {};List<String> result = source.toString();assertEquals(2, result.size());assertEquals(4, source.synchronizedMethod());}}