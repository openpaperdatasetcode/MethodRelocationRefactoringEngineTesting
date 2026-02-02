package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
class ParentSource {protected void parentMethod1() {}protected void parentMethod2() {}protected void parentMethod3() {}}
protected class SourceClass<T> extends ParentSource {private TargetClass targetField = new TargetClass();
class MemberInner {}
public void initAnonymous() {new Runnable() {@Overridepublic void run() {try {SourceClass.this.moveMethod();} catch (Exception e) {}}}.run();}
private List<String> moveMethod() throws Exception {class TypeDeclaration {}new TypeDeclaration();
super.parentMethod1();super.parentMethod2();super.parentMethod3();
variableCall();
try {Method method = TargetClass.StaticNested.class.getMethod("doTask");method.invoke(new TargetClass.StaticNested());} catch (Exception e) {throw e;}
System.out.println(SourceClass.this);return new ArrayList<>();}
private void variableCall() {targetField.staticNested.doTask();}}
public class TargetClass {public static class StaticNested {public void doTask() {}}
public StaticNested staticNested = new StaticNested();
public String callMethod() {return this.overloadMethod("arg1") + this.overloadMethod("arg1", "arg2");}
public String overloadMethod(String param) {return param;}
public String overloadMethod(String param1, String param2) {return param1 + param2;}}