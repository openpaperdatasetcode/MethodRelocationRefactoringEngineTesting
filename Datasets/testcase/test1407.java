package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;import java.io.IOException;
class SuperSourceClass {}class SuperTargetClass {}
protected class SourceClass extends SuperSourceClass {// Static nested class (source feature)static class SourceStaticNested {void nestedMethod() {}}
// Member inner class (source feature)class SourceInner {// source_inner_recclass SourceInnerRec {protected List<String> methodToMove(TargetClass.TargetParam param) throws IOException { // requires_throws// contains target parameter (per_condition)if (param == null) {throw new IOException("Target parameter is null");}
// variable callSourceStaticNested staticNested = new SourceStaticNested();staticNested.nestedMethod();TargetClass target = new TargetClass();TargetClass.MemberInner targetInner = target.new MemberInner();TargetClass.MemberInner.TargetInnerRec targetInnerRec = targetInner.new TargetInnerRec();targetInnerRec.doAction(param);
// overload_existtarget.overloadMethod(1);target.overloadMethod("str");SourceClass.this.overloadMethod();
// used_by_reflectiontry {Method method = TargetClass.MemberInner.TargetInnerRec.class.getMethod("doAction", TargetClass.TargetParam.class);method.invoke(targetInnerRec, param);} catch (Exception e) {throw new IOException("Reflection failed", e);}
return new ArrayList<>();}}}
// overload_exist (source side)protected void overloadMethod() {}protected void overloadMethod(int num) {}}
sealed class TargetClass extends SuperTargetClass { // target_feature: extendsstatic class TargetParam {String getValue() { return "targetParam"; }}
// target_feature: member inner classclass MemberInner {// target_inner_recclass TargetInnerRec {void doAction(TargetParam param) {System.out.println(param.getValue());}}
void innerMethod() {}}
// overload_exist (target side)public void overloadMethod(int num) {}public void overloadMethod(String str) {}}
// Permitted subclass for sealed TargetClassfinal class TargetSubclass extends TargetClass {}