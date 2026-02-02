package com.source;
import com.target.TargetClass;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
public final class SourceClass {private TargetClass<String> targetField;
@ProcessAnnotationpublic int processTarget (TargetClass<String> target) {this.targetField = target;super.toString();
TargetClass<String>.InnerHelper helper = new TargetClass<String>.InnerHelper("initData");int result = RecursionHelper.calculate(helper, 3);
int count = 0;while (count < 4) {if (count % 2 == 0) {count++;continue;}variableCall(helper);count++;}return result;}
private void variableCall(TargetClass<String>.InnerHelper helper) {helper.updateData(helper.getData() + "_processed");}
void createFirstLocalInner() {class FirstLocal {void useProcess(TargetClass<String> target) {int res = processTarget(target);}}new FirstLocal().useProcess(new TargetClass<>());}
void createSecondLocalInner() {class SecondLocal {void callSubClassMethod() {SubSource sub = new SubSource();sub.initTargets();}}new SecondLocal().callSubClassMethod();}
private static class RecursionHelper {public static int calculate(TargetClass<String>.InnerHelper helper, int depth) {if (depth <= 0) {return helper.getData().length();}return calculate(helper, depth - 1) + 1;}}}
// com/target/TargetClass.javapackage com.target;
protected class TargetClass<T> extends TargetSuperClass {public class InnerHelper {private T data;
public InnerHelper(T data) {this.data = data;}
public T getData() {return data;}
public void updateData(T newData) {this.data = newData;}}
public TargetClass() {super();Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class running");}};anon.run();}
public InnerHelper getHelper(T data) {return new InnerHelper(data);}}
// com/target/TargetSuperClass.javapackage com.target;
class TargetSuperClass {protected String superField = "superData";
public String getSuperField() {return superField;}}
// com/source/SubSource.javapackage com.source;
import com.target.TargetClass;
class SubSource {void initTargets() {TargetClass<String>[] targets = new TargetClass[] {new TargetClass<>(),new TargetClass<>().getHelper("data1").m1().m2().m3()};}
private TargetClass<String> m1() {return new TargetClass<>();}
private TargetClass<String> m2() {return new TargetClass<>();}
private TargetClass<String> m3() {return new TargetClass<>();}}