package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
interface Processable {}
private class SourceClass implements Processable {static class StaticNested {}
// Anonymous inner classprivate final Runnable anonymousTask = new Runnable() {@Overridepublic void run() {new InnerClass().process(new TargetClass());}};
class InnerClass {protected List<String> process(TargetClass target) {List<String> result = new ArrayList<>();super.toString(); // Super keywords
loop: {// transient AssertStatement (target_feature: this.field=1)transient boolean valid = target.this.field == 1;assert valid : "Field value is not 1";
if (!valid) break loop; // Break statement
variableCall(target);target.instanceMethod(); // Access instance methodresult.add(String.valueOf(target.field));
// Used by reflectiontry {Method method = TargetClass.StaticNested.class.getMethod("doTask");method.invoke(target.staticNested);} catch (Exception e) {}}
new StaticNested();return result;}
private void variableCall(TargetClass target) {target.staticNested.doTask();}}}
private class TargetClass {public int field = 1; // this.field=1
public static class StaticNested {public void doTask() {}}
public StaticNested staticNested = new StaticNested();
public void instanceMethod() {}
protected List<String> process() {return new ArrayList<>() {{ add(String.valueOf(field)); }};}}
