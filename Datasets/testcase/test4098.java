package test;
import java.lang.reflect.Method;import java.util.Arrays;import java.util.List;
abstract class ParentAbstract {protected ParentAbstract() {}}
abstract class SourceClass extends ParentAbstract {public SourceClass() {super();new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in SourceClass");}}.run();}
public final TargetClass instanceMethod(TargetClass targetParam) {if (targetParam == null) {return null;}
class LocalInner {void processTargetInner() {List<TargetClass.MemberInner> innerList = Arrays.asList(targetParam.new MemberInner(),targetParam.new MemberInner());
for (TargetClass.MemberInner inner : innerList) {if (inner.thisField == 1) {break;}++inner.counter;}
int num = 2;switch (num) {case 1:System.out.println("Case 1");break;case 2:System.out.println("Case 2");break;}}}
LocalInner local = new LocalInner();local.processTargetInner();
try {Method innerMethod = TargetClass.MemberInner.class.getMethod("getCounter");int reflectedVal = (int) innerMethod.invoke(targetParam.new MemberInner());System.out.println("Reflected counter: " + reflectedVal);} catch (Exception e) {e.printStackTrace();}
int var = targetParam.targetField;return targetParam;}}
abstract class TargetClass {int targetField = 1;
class MemberInner {int thisField = 1;int counter = 0;
public int getCounter() {return counter;}}
public TargetClass instanceMethod(TargetClass targetParam) {if (targetParam == null) {return null;}return instanceMethod(targetParam);}}