package test;
import otherpackage.External;import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnno {}
interface MyInterface {}
abstract class SourceClass {protected int outerProtected = 100;protected TargetClass targetField = new TargetClass();
class MemberInner {}
void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}
/**
Method to be moved, demonstrates various features*/@MyAnnopublic void methodToMove() {External ext = new External();ext.superField = 1;
List<String> result = ((TargetClass) targetField).getList(() -> super.toString());
int i = 0;while (i < 5) {targetField.variableCall();i++;}
String expr = "expression";
try {Method method = TargetClass.TargetInner.class.getMethod("innerMethod");method.invoke(new TargetClass.TargetInner());} catch (Exception e) {}
System.out.println(outerProtected);}}
protected class TargetClass implements MyInterface {class TargetInner {void innerMethod() {}}
public List<String> getList(Runnable r) {r.run();return new ArrayList<>();}
void variableCall() {}}
package otherpackage;
public class External extends SuperExternal {public External() {super();}}
class SuperExternal {public int superField;}