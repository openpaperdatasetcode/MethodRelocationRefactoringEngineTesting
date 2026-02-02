import java.util.List;import java.util.ArrayList;
private class SourceClass {protected String outerProtected = "protected_field";
static class StaticNested {void nestedHelper(TargetClass target) {new InnerClass().process(target);}}
class InnerClass {public Object process(TargetClass targetParam) {super();TargetClass.MemberInner targetInner = targetParam.new MemberInner();
// With bounds: generic type with upper boundList<? extends CharSequence> boundedList = new ArrayList<>();boundedList.add(outerProtected);
for (int i = 0; i < 3; i++) {// Variable call + access instance methodtargetInner.setValue(boundedList.get(0) + i);System.out.println(targetInner.getValue());}
// Access outer protected field directlytargetParam.updateData(outerProtected);
// Anonymous inner class in sourceRunnable r = new Runnable() {public void run() {targetInner.setValue("anonymous_update");}};r.run();
return targetInner.getValue();}}}
class ParentTarget {}
class TargetClass extends ParentTarget {private String data;
class MemberInner {private String value;
String getValue() {return value;}
void setValue(String val) {this.value = val;}}
void updateData(String newData) {this.data = newData;}
String getData() {return data;}}