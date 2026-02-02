package test;
import java.util.ArrayList;import java.util.List;
public class SourceClass {public static class StaticNested {public static int staticCount = 0;}
public class MemberInner {public class InnerRec {private List<String> process(TargetClass... targets) {List<String> result = new ArrayList<>();int index = 0;
do {if (index >= targets.length) break;TargetClass target = targets[index];
// Access target's anonymous inner classtarget.action(new Runnable() {@Overridepublic void run() {StaticNested.staticCount++;}});
// Call synchronized instance methodssynchronized (this) {result.add(String.valueOf(target.getValue(1)));result.add(String.valueOf(target.getValue(2)));}
index++;} while (true);
try {// Use static field from StaticNestedresult.add("Static count: " + StaticNested.staticCount);} catch (Exception e) {// requires_try_catch}
return result;}}}}
protected class TargetClass extends ParentClass {public TargetClass() {super();}
public synchronized int getValue(int param) {return param * 2;}
public void action(Runnable task) {task.run();}}
class ParentClass {protected ParentClass() {}}
