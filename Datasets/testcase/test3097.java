package test;
import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;
abstract class TargetClass {int targetField;class TargetInner {}}
protected class SourceClass {class Inner1 {}class Inner2 {}
public void methodToMove(TargetClass target) {class TypeDecl {}TypeDecl type = new TypeDecl();
TargetClass.TargetInner inner = target.new TargetInner();int var = target.targetField;
new OtherClass().process(target);
try {Method method = SourceClass.class.getMethod("methodToMove", TargetClass.class);method.invoke(this, target);} catch (Exception e) {}}}
class SourceSubClass extends SourceClass {@Overridepublic void methodToMove(TargetClass target) {int count = 0;while (count < 3) {target.targetField = 3;if (target.targetField == 3) {continue;}count++;}}}
// Different packagepackage other;
import test.TargetClass;import test.SourceClass;import java.util.List;
public class OtherClass {protected List<String> process(TargetClass target) {SourceClass source = new SourceClass();Runnable r = () -> source.new Inner1().helperMethod(target);return new ArrayList<>();}}
package test;
class Helper {void helperMethod(TargetClass target) {}}