package test;
import java.lang.reflect.Method;
public class SourceClass {public class InnerSource {public class DeepInner {strictfp void varargsMethod(TargetClass... targets) {class LocalInnerFirst {void validateTargets(TargetClass[] ts) {assert ts != null && ts.length > 0 : "Targets cannot be empty";}}
class LocalInnerSecond {void invokeInnerMethod(TargetClass t) {try {Method method = TargetClass.Inner.class.getMethod("process");method.invoke(t.new Inner());} catch (Exception e) {e.printStackTrace();}}}
LocalInnerFirst first = new LocalInnerFirst();LocalInnerSecond second = new LocalInnerSecond();first.validateTargets(targets);
labeledBlock: {for (TargetClass target : targets) {if (target == null) break labeledBlock;
target.field = "processed";expressionStatement: second.invokeInnerMethod(target);
try {Method getFieldMethod = TargetClass.class.getMethod("getField");System.out.println(getFieldMethod.invoke(target));} catch (Exception e) {e.printStackTrace();}}}}}}}
protected class TargetClass extends ParentClass {String field;
public String getField() {return field;}
// Override violation: ParentClass has public method, this is default accessvoid setField(String field) {this.field = field;}
public class Inner {public void process() {System.out.println("Inner class processing: " + TargetClass.this.field);}}}
class ParentClass {public void setField(String field) {}}