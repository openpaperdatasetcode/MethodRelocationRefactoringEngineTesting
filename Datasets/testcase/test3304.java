package test;
import java.lang.reflect.Method;import java.util.function.Function;
abstract class AbstractTargetSubClass {public abstract TargetClass process(TargetClass target);}
final class SubTargetClass extends AbstractTargetSubClass {@Overridepublic TargetClass process(TargetClass target) {Function<TargetClass, TargetClass> lambda = (t) -> {t.innerClass.doTask();return t;};return lambda.apply(target);}}
public class SourceClass {static class StaticNested {}
private <T extends Number> void moveMethod(TargetClass... targets) {int count = 0;while (count < 3) {for (TargetClass target : targets) {// Three public Assignmentstarget.field1 = 10;target.field2 = "test";target.field3 = true;
variableCall(target);target.innerClass.doTask();
try {Method method = TargetClass.TargetInner.class.getMethod("doTask");method.invoke(target.innerClass);} catch (Exception e) {}}count++;}}
private void variableCall(TargetClass target) {target.innerClass.helper();}
public void callMethod() {TargetClass target = new TargetClass();AbstractTargetSubClass subClass = new SubTargetClass();TargetClass result = subClass.process(target);}}
private class TargetClass {public int field1;public String field2;public boolean field3;
class TargetInner {public void doTask() {}public void helper() {}}
TargetInner innerClass = new TargetInner();
private void moveMethod(TargetClass... targets) {}}