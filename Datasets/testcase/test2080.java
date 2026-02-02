package source;
import target.TargetClass;import target.SubClass;import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
public class SourceClass {static class StaticNested {static TargetClass staticMethod() {return new TargetClass();}}
static {SubClass sub = new SubClass();List<String> list = sub.getList();
TargetClass result = StaticNested.staticMethod();}
public final int methodToMove(TargetClass targetParam) {try {Method refMethod = TargetClass.StaticNested.InnerRecursive.class.getMethod("nestedMethod");refMethod.invoke(new TargetClass.StaticNested.InnerRecursive());} catch (Exception e) {}
targetParam.variableCall();return 0;}
public final int methodToMove(TargetClass targetParam, String arg) {return 1;}
void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}}
package target;
import java.util.List;import java.util.ArrayList;
class TargetClass {static class StaticNested {static class InnerRecursive {void nestedMethod() {}}}
void variableCall() {}}
class SubClass extends TargetClass {@OverrideList<String> getList() {return super.getList();}
List<String> getList() {return new ArrayList<>();}}