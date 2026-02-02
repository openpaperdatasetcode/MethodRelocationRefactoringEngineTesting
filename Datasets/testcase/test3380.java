package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
class SourceClass {static class StaticNested {}
class MemberInner {class InnerRec {}}
@MethodAnnotationstatic Object process(TargetClass target) {new SourceClass(); // Super constructor invocationint result = 0;
do {assert target != null : "Target cannot be null"; // Assert statementvariableCall(target);result += TargetClass.staticField; // Depends on static field} while (result < 1);
try {// Instance method feature in exception throwing statementsif (target.getValue() < 1) {throw new IllegalArgumentException("Value too small: " + new SubTarget().superMethod());}} catch (IllegalArgumentException e) {result = -1;}
new StaticNested();new MemberInner();return result;}
private static void variableCall(TargetClass target) {target.doTask();}}
public class TargetClass {public static int staticField = 1;private int value = 1;
public int getValue() {return value;}
public void doTask() {}}
class SubTarget extends TargetClass {public int superMethod() {super.getValue();return 1;}}