package same.pkg;
import java.lang.reflect.Constructor;import java.lang.reflect.InvocationTargetException;import java.util.Arrays;import java.util.List;
protected abstract class SourceClass {private TargetClass.TargetInner targetInnerField = TargetClass.INSTANCE.new TargetInner();
protected class SourceInner {protected SourceInner() throws Exception {super();
int i = 0;while (i < 2) {if (i == 1) break;i++;}
List<String> list = Arrays.asList("a", "b");for (String s : list) {OtherClass.overriddenMethod();variableCall();}
new Runnable() {@Overridepublic void run() {targetInnerField.someMethod();}}.run();
class LocalInner {void localMethod() {}}new LocalInner().localMethod();
try {Constructor<?> ctor = TargetClass.TargetInner.class.getConstructor(TargetClass.class);ctor.newInstance(TargetClass.INSTANCE);} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {throw new Exception(e);}}
private void variableCall() {}}}
protected abstract class TargetClass {static TargetClass INSTANCE = new TargetClass() {};
protected class TargetInner {void someMethod() {}}}
class OtherClass {public String overriddenMethod() {return TargetClass.INSTANCE.new TargetInner().someMethod() + "";}}