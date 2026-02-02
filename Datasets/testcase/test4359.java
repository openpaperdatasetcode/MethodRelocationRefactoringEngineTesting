package test;
import java.lang.reflect.Method;import java.util.function.Predicate;
private enum SourceEnum {INSTANCE;
private <T extends TargetEnum> void varargsMethod(T target, String... args) {class LocalInner {void processTarget(T tc) {variableCall(tc);tc.innerClass.instanceMethod(tc, args[0], args[1]);}}
Runnable anonymousInner = new Runnable() {@Overridepublic void run() {System.out.println(target.field1 + target.field2);}};anonymousInner.run();
labeledBlock: {if (args == null || args.length < 2) {return;}
SubTargetEnum subTarget = new SubTargetEnum(target.field1, target.field2) {};LocalInner localInner = new LocalInner();localInner.processTarget(target);
int i = 0;while (i < args.length) {switch (args[i]) {case "field1":System.out.println(target.getField1());break;case "field2":System.out.println(target.getField2());break;default:break labeledBlock;}i++;}
try {Method method = TargetEnum.class.getMethod("getField1");System.out.println(method.invoke(target));} catch (Exception e) {}}}
private void variableCall(TargetEnum target) {target.field1 += "_updated";}}
enum TargetEnum {VAL1("val1", 10), VAL2("val2", 20);
String field1;int field2;InnerClass innerClass = new InnerClass();
TargetEnum(String field1, int field2) {this.field1 = field1;this.field2 = field2;}
class InnerClass {private void instanceMethod(TargetEnum target, String arg1, String arg2) {target.field2 += arg1.length() + arg2.length();}}
String getField1() {return field1;}
int getField2() {return field2;}}
abstract class SubTargetEnum extends TargetEnum {protected SubTargetEnum(String field1, int field2) {super(field1, field2);}}