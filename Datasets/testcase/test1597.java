package test;
import java.util.ArrayList;import java.util.List;
private class SourceClass {private String outerPrivate = "private_source_data";
public static class StaticNested {public static void log(String message) {System.out.println(message);}}
TargetClass process(TargetClass target) {// Local inner classclass TargetHandler {TargetClass handle(TargetClass t) {// Access outer private fieldt.inner.setData(outerPrivate);return t;}}TargetHandler handler = new TargetHandler();
// Instance method (private, target, this call) in property assignmentprivate TargetClass setupTarget(TargetClass t) {this.configure(t);return t;}TargetClass processedTarget = setupTarget(target);
// For statementfor (int i = 0; i < 3; i++) {// Expression statementprocessedTarget.counter++;}
// ThisExpression (1 occurrence, protected)protected SourceClass thisRef = this;StaticNested.log(thisRef.outerPrivate);
// Call target's protected constructor chain in collection operationsList<String> results = new ArrayList<>();results.add(new TargetClass().inner.getData().toUpperCase().trim());
// Variable call - access target's fieldStaticNested.log("Target counter: " + processedTarget.counter);
// Super constructor invocation in target's inner classTargetClass.MemberInner newInner = processedTarget.new MemberInner();
// Return statementreturn handler.handle(processedTarget);}
private void configure(TargetClass target) {target.counter = 0;}}
/**
TargetClass with Javadoc
Contains member inner class for data handling*/public class TargetClass {public int counter;public MemberInner inner = new MemberInner();
/**
Member inner class to manage data*/public class MemberInner {private String data;
public MemberInner() {super(); // Super constructor invocation}
protected void setData(String data) {this.data = data;}
protected String getData() {return data;}}
protected TargetClass() {// Protected constructor}}