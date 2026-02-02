package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
public class SourceClass {// First member inner classpublic class FirstMemberInner1 {private TargetClass processTarget(TargetClass target) {return target.setValue("inner1").addFlag(true).getTarget();}}
// Second member inner classpublic class SourceInner2 {private TargetClass processTarget(TargetClass target) {return target.setValue("inner2").addFlag(false).getTarget();}}
private static void staticMethod(TargetClass target) {// Expression statementList<String> logs = new ArrayList<>();
// Variable calllogs.add(target.getValue());
// Instance method with 2 source methods (method chaining)TargetClass processed1 = new SourceInner1().processTarget(target);TargetClass processed2 = new SourceInner2().processTarget(processed1);logs.add(processed2.getValue());
// Continue statementfor (int i = 0; i < 5; i++) {if (i % 2 == 0) continue;logs.add("i=" + i);}
// Used by reflectiontry {Method method = TargetClass.StaticNested.class.getMethod("log", String.class);method.invoke(null, logs.toString());} catch (Exception e) {// No new exception}
// Array initialization with parent_class final methodTargetAction[] actions = {() -> target.parentAction("init"),() -> target.parentAction("update")};for (TargetAction action : actions) {action.execute();}}
@FunctionalInterfaceprivate interface TargetAction {void execute();}}
class TargetClass extends TargetParent {private String value;private boolean flag;
// Static nested classpublic static class StaticNested {public static void log(String message) {System.out.println("Log: " + message);}}
public TargetClass setValue(String value) {this.value = value;return this;}
public TargetClass addFlag(boolean flag) {this.flag = flag;return this;}
public TargetClass getTarget() {return this;}
public String getValue() {return value;}}
class TargetParent {public final void parentAction(String action) {System.out.println("Parent action: " + action);}}