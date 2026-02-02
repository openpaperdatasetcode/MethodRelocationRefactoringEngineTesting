package com.source;
import com.target.TargetClass;import java.lang.reflect.Method;
private class SourceClass {private String outerPrivate = "source_private";
class MemberInner {void assist(TargetClass target) {target.callAction();}}
/**
Method javadoc: Processes target class instance with reflection and inner class dependency
@param target TargetClass instance to process
@return Processed Object result*/protected Object process(TargetClass target) {try {// Access outer private fieldString data = outerPrivate + "_processed";
// Member inner class dependencyMemberInner inner = new MemberInner();inner.assist(target);
// Anonymous inner class in sourceRunnable r = new Runnable() {public void run() {target.overloadMethod(data);}};r.run();
// Variable call + overload exist (call overloaded methods)target.overloadMethod();Object result = target.overloadMethod(100);
// Reflection usageMethod method = TargetClass.class.getMethod("overloadMethod", String.class);method.invoke(target, data);
return result;} catch (Exception e) {return null;}}}
// Different package: com.targetpackage com.target;
public class TargetClass {// Anonymous inner class in targetpublic void callAction() {Runnable r = new Runnable() {public void run() {System.out.println("Target anonymous inner class action");}};r.run();}
// Overloaded methods (overload_exist)public void overloadMethod() {}public Object overloadMethod(String arg) { return arg; }public Object overloadMethod(int num) { return num; }}