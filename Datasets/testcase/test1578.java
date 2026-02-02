package source;
import target.TargetClass;
private class SourceClass {private String outerField = "outer_value";
public class MemberInner {public void process(TargetClass target) {// Type declaration statementclass LocalType {void handle() {// Uses outer thisSystem.out.println(SourceClass.this.outerField);}}LocalType local = new LocalType();
// Constructor invocation of target's inner classesTargetClass.MemberInner targetInner = target.new MemberInner();TargetClass.MemberInner.InnerRec targetInnerRec = targetInner.new InnerRec();
// Super constructor invocation in raw typeTargetClass rawTarget = new TargetClass();TargetClass.MemberInner rawInner = rawTarget.new MemberInner();
// Variable call - access target's inner rec fieldtargetInnerRec.data = "processed";
// Expression statementlocal.handle();
// This method callthis.log(targetInnerRec.data);}
private void log(String message) {System.out.println(message);}}}
package target;
private class TargetClass {public class MemberInner {public class InnerRec {public String data;
public InnerRec() {super(); // Super constructor invocation}}
public MemberInner() {super(); // Super constructor invocation}}}