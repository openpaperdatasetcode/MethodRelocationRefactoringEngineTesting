package test;
import java.lang.reflect.Method;
public class SourceClass extends ParentClass {// Member inner class (source feature)public class SourceMemberInner {public void innerMethod() {}public void innerMethod(String param) {} // Overload for overload_exist}
// Anonymous inner class (source feature){Runnable anon = new Runnable() {@Overridepublic void run() {SourceMemberInner inner = new SourceMemberInner();inner.innerMethod();}};}
// Instance code blocks (pos for method feature){SourceMemberInner inner = new SourceMemberInner();inner.innerMethod(); // outerInstance.new InnerClass().methodName()}
// Instance method (private access modifier, returns Object)private Object instanceMethod(TargetClass targetParam) {// Variable call to target classtargetParam.targetMethod();
// Depends on inner class: source's member inner classSourceMemberInner inner = new SourceMemberInner();inner.innerMethod();inner.innerMethod("overload"); // overload_exist
// Used by reflectiontry {Method method = SourceMemberInner.class.getMethod("innerMethod");method.invoke(inner);} catch (Exception e) {// No new exception}
return targetParam;}}
// Parent class for source's extends featureclass ParentClass {}
// Target class (default modifier, no target_features)class TargetClass {public void targetMethod() {}}