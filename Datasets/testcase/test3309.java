package test;
import java.lang.reflect.Method;import diffpackage.OthersClass;
abstract class SourceClass<T extends Number> {static class StaticNested1 {}static class StaticNested2 {}
class InnerClass {int moveMethod(TargetClass target) throws Exception {OthersClass.process(target);
; // Empty statement
variableCall(target);System.out.println(SourceClass.this.toString());
try {Method method = TargetClass.class.getMethod("instanceMethod");method.invoke(target);} catch (Exception e) {throw e;}
return target.staticField;}
private void variableCall(TargetClass target) {target.localInnerHelper();}}
// Override violation (incorrect return type for Object.toString())@Overridepublic int toString() {return 0;}}
class TargetClass {public static int staticField = 3;
public void localInnerHelper() {class LocalInner {}new LocalInner();}
public void instanceMethod() {}
int moveMethod() {return staticField;}}
package diffpackage;
import test.TargetClass;
public class OthersClass {public static void process(TargetClass target) {public int field1 = TargetClass.staticField;public int field2 = TargetClass.staticField;public int field3 = TargetClass.staticField;}}