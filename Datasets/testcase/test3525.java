package sourcepkg;
import java.util.List;import java.util.ArrayList;import targetpkg.TargetClass;
private class SourceClass {private TargetClass targetField = new TargetClass();
class MemberInner {class InnerRec {List<String> moveMethod(TargetClass... params) {List<String> result = new ArrayList<>();for (TargetClass param : params) {if (param == null) {continue;}
private class TypeDeclaration1 {}new TypeDeclaration1();private class TypeDeclaration2 {}new TypeDeclaration2();
if (TargetClass.staticField == 1) {variableCall(param);}
TargetClass newTarget = new TargetClass();result.add(new SubClass().moveMethod("arg1", "arg2", "arg3"));}return result;}
List<String> moveMethod(String... params) {return new ArrayList<>();}
private void variableCall(TargetClass target) {target.innerClass.doTask();}}}
{new Runnable() {@Overridepublic void run() {MemberInner inner = new MemberInner();InnerRec innerRec = inner.new InnerRec();innerRec.moveMethod(targetField);}}.run();}}
class SubClass extends TargetClass {@Overridepublic List<String> moveMethod(String... params) {return new ArrayList<>();}}
package targetpkg;
/**
Javadoc for TargetClass: Public target class with member inner class*/public class TargetClass {public static int staticField = 1;
class TargetInner {List<String> moveMethod(TargetClass... params) {return new ArrayList<>();}
void doTask() {}}
private TargetInner innerClass = new TargetInner();
public List<String> moveMethod(String param1, String param2) {return new ArrayList<>();}}