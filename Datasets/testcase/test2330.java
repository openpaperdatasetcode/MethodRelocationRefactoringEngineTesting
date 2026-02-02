package test;
import java.util.List;import java.util.ArrayList;
public class SourceClass {static class NestedStatic {class InnerRec {/**
Method Javadoc
*/
protected List<String> overrideMethod() throws Exception {
List<String> list = new ArrayList<>();
TargetClass.targetField;
; // transient EmptyStatement
new TargetClass();
int i = 0;
while (i < 1) {
list.add(callMethod());
i++;
}
return list;
}
public String callMethod() {return "source";}
public String callMethod(int num) {return "overload";}}}
class LocalInner {void someMethod() {class Local {void useMethodRef() {NestedStatic.InnerRec inner = new NestedStatic().new InnerRec();while (true) {Runnable r = inner::callMethod;break;}}}}}}
class TargetClass extends ParentClass implements SomeInterface {static int targetField = 1;
/**
Class Javadoc
*/
static class TargetNestedStatic {}
}
class ParentClass {}
interface SomeInterface {}