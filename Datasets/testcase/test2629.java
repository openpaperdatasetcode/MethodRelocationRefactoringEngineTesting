package test.same;
import java.lang.reflect.Method;import java.util.function.Function;import test.other.OtherClass;
protected class SourceClass {TargetClass varargsMethod(TargetClass... targets) {class LocalInner {protected TargetClass varargsMethod(TargetClass... args) {return new TargetClass().getInstance();}}LocalInner local = new LocalInner();
int count = 0;do {Object var = targets[0].getLocalField();count++;} while (count < 3);
Function<Integer, TargetClass> func = local::varargsMethod;switch (1) {case 1:new OtherClass().process(targets[0]);break;}
try {Method method = TargetClass.class.getMethod("getLocalField");Object fieldVal = method.invoke(targets[0]);} catch (Exception e) {}
return targets[0];}
Runnable anon = new Runnable() {public void run() {}};}
public class TargetClass {Object getLocalField() {class LocalInner {Object field = 1;}return new LocalInner().field;}
TargetClass getInstance() {return new TargetClass();}}
package test.other;
import test.same.TargetClass;
class OtherClass {void process(TargetClass target) {Object var = target.getLocalField();}}