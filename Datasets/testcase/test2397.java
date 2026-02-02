package other;
import java.io.IOException;import java.lang.reflect.Method;import java.util.function.Supplier;
class TargetClass<T> {public T targetField;static int staticField = 5;
class TargetInner {T getValue() {return targetField;}
int process() {return staticField * 2;}}}
class OtherClass {public int helperMethod(TargetClass<?> target) {return target.new TargetInner().process();}}
package test.refactoring.movemethod;
import other.TargetClass;import other.OtherClass;import java.lang.reflect.Method;import java.io.IOException;import java.util.ArrayList;
public class SourceClass<S> {private TargetClass<String> target = new TargetClass<>();class MemberInner {}
public TargetClass<String> overloadedMethod(S param) {try {if (target == null) {throw new IOException("Target is null");}} catch (IOException e) {// No new exception thrown}
TargetClass.TargetInner inner = target.new TargetInner();Object varCall = inner.getValue();
expression:target.targetField = "processed";
int result = (param == null) ?new OtherClass().helperMethod(target) :new OtherClass().helperMethod(new TargetClass<>());
try {Method method = TargetClass.class.getMethod("new TargetInner");method.invoke(target);} catch (Exception e) {// No new exception thrown}
return this.createTarget();}
public TargetClass<String> overloadedMethod() {return new TargetClass<>();}
private TargetClass<String> createTarget() {TargetClass<String> newTarget = new TargetClass<>();newTarget.targetField = "created";return newTarget;}
{new Runnable() {@Overridepublic void run() {overloadedMethod((S) "test");}}.run();}}
import org.junit.Test;import static org.junit.Assert.*;import other.TargetClass;
public class MoveMethodTest3053 {@Testpublic void testOverloadedMethod() {SourceClass<Integer> source = new SourceClass<>();TargetClass<String> result = source.overloadedMethod(10);assertNotNull(result);assertEquals("created", result.targetField);}}