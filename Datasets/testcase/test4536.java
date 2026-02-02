package same.pkg;
import other.pkg.OtherClass;
final class SourceClass {private TargetClass targetField = new TargetClass();
public TargetClass varargsMethod(String... args) {variableCall();
for (String arg : args) {OtherClass.useTargetField(TargetClass.staticField, 1);}
return targetField;}
private void variableCall() {String localVar = TargetClass.staticField + "_variable";}
void methodWithTwoLocalClasses() {class LocalInner1 {void localMethod1() {}}new LocalInner1().localMethod1();
class LocalInner2 {void localMethod2() {}}new LocalInner2().localMethod2();}}
final class TargetClass {public static String staticField = "targetStaticField";
void methodWithLocalClass() {class TargetLocalInner {void targetLocalMethod() {}}new TargetLocalInner().targetLocalMethod();}}
package other.pkg;
import same.pkg.TargetClass;
public class OtherClass {public static void useTargetField(String field, int count) {System.out.println(field + "_count:" + count);}}