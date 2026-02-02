package test;
import java.util.List;import java.util.ArrayList;
enum SourceEnum {INSTANCE;
static class SourceStaticNested {static String staticField = "staticData"; // Depends on static field}
class SourceMemberInner {// Abstract methodprotected abstract <T extends CharSequence> TargetEnum.TargetInner abstractMethod(TargetEnum targetParam);
// Instance method (overload exists)protected <T extends CharSequence> TargetEnum.TargetInner instanceMethod(TargetEnum targetParam) {new Runnable() {@Overridepublic void run() {targetParam.new TargetInner().doAction();}}.run();
List<TargetEnum.TargetInner> innerList = new ArrayList<>();innerList.add(targetParam.new TargetInner());
// Enhanced for statementfor (TargetEnum.TargetInner inner : innerList) {inner.doAction(); // Variable call}
try {// Array initialization + instanceReference.methodName(arguments)TargetEnum.TargetInner[] innerArr = new TargetEnum.TargetInner[]{parentHelperMethod(targetParam)};return innerArr[0];} catch (Exception e) {return targetParam.new TargetInner();}}
// Overload methodprotected <T extends CharSequence> TargetEnum.TargetInner instanceMethod(TargetEnum targetParam, T data) {return targetParam.new TargetInner();}
// Parent class helper methodprivate TargetEnum.TargetInner parentHelperMethod(TargetEnum target) {return target.new TargetInner();}}}
non-sealed enum TargetEnum permits {} {VALUE;
class TargetInner {void doAction() {}}}
class SubClass extends SourceEnum.SourceMemberInner {@Overrideprotected <T extends CharSequence> TargetEnum.TargetInner abstractMethod(TargetEnum targetParam) {TargetEnum.TargetInner inner = null;do {// outerInstance.new InnerClass().methodName()inner = targetParam.new TargetInner();inner.doAction();} while (inner == null);return inner;}}