package test;
import java.util.ArrayList;import java.util.List;
class ParentClass {protected int parentMethod1() {return 1;}
protected int parentMethod2() {return 2;}
protected int parentMethod3() {return 3;}}
class SubClass extends ParentClass {public List<String> instanceMethod(String arg1, String arg2, String arg3) {super.parentMethod1();List<String> list = new ArrayList<>();list.add(arg1 + arg2 + arg3);return list;}
public List<String> instanceMethod(Integer... args) {return new ArrayList<>();}}
public class SourceClass<T> extends ParentClass {private T instanceField;public static String staticField = "staticData";
static class StaticNested {void useStaticField() {System.out.println(staticField);}}
class MemberInner {T getInnerField() {return instanceField;}}
public int getBaseType(TargetClass target) {super();this.instanceField = (T) "sourceData";
StaticNested staticNested = new StaticNested();staticNested.useStaticField();
MemberInner inner = new MemberInner();variableCall(inner);
SubClass sub = new SubClass();List<String> subResult = sub.instanceMethod("a", "b", "c");
TargetClass.MemberInner targetInner = target.new MemberInner();int parentResult = (targetInner != null) ?targetInner.getParent().parentMethod1().parentMethod2().parentMethod3() : 0;
return parentResult;}
private void variableCall(MemberInner inner) {System.out.println(inner.getInnerField());}}
public class TargetClass {class MemberInner {ParentClass getParent() {return new ParentClass() {};}}
{new Runnable() {public void run() {System.out.println("Target anonymous inner class");}}.run();}}