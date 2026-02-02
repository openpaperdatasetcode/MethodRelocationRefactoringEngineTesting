package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import java.util.function.Function;
/**
Private abstract target class with anonymous inner class (target_feature)*/private abstract class TargetClass {public static int STATIC_FIELD = 3; // For depends_on_static_fieldprotected String data;
public TargetClass(String data) {this.data = data;}
public abstract void doTask();
public String getData() {return data;}
public void setData(String data) {this.data = data;}
// Anonymous inner class (target_feature)public Runnable getTargetTask() {return new Runnable() {@Overridepublic void run() {data = data + "_target_anon";}};}}
/**
Protected abstract source class with member inner and anonymous inner classes/
protected abstract class SourceClass {
// Member inner class
class InnerClass {
// Inner recursive class (source_inner_rec)
class InnerRec {
/*
Private varargs method_feature (2 parameters, source, varargs)*/private Object varargsHelper(TargetClass target, String... params) {List<String> result = new ArrayList<>();// instanceReference::methodNameFunction<TargetClass, String> dataGetter = TargetClass::getData;result.add(dataGetter.apply(target));
for (String param : params) {result.add(param);}return result;}
/**
Protected instance method (position: source_inner_rec)*/protected void process(TargetClass target) {// Constructor invocationTargetClass concreteTarget = new TargetClass("concrete") {@Overridepublic void doTask() {}};
// Try statementtry {// Super keywordssuper.toString();
// Private ConditionalExpression (numbers=3)String cond1 = (target.data != null) ? "not_null" : "null";int cond2 = (target.STATIC_FIELD == 3) ? 3 : -1;boolean cond3 = (concreteTarget.getData().length() > 0) ? true : false;String combined = cond1 + "" + cond2 + "" + cond3;
// Variable callvariableCall(target);target.getTargetTask().run();
// Depends on static fieldif (TargetClass.STATIC_FIELD != 3) {throw new IllegalStateException("Static field mismatch");}
// Property assignment (varargs method_feature position)Object varargsResult;varargsResult = varargsHelper(target, combined, "param2");
// Used by reflectionMethod method = TargetClass.class.getMethod("getData");String reflectedData = (String) method.invoke(target);target.setData(reflectedData + "_reflected");} catch (Exception e) {throw new RuntimeException("Processing failed", e);}
// Anonymous inner class (source_class feature)Runnable sourceTask = new Runnable() {@Overridepublic void run() {concreteTarget.setData(concreteTarget.getData() + "_source_anon");}};sourceTask.run();}
private void variableCall(TargetClass target) {target.doTask();}}}
// Trigger methodpublic void triggerProcess(TargetClass target) {new InnerClass().new InnerRec().process(target);}}
/**
Concrete implementation of abstract SourceClass
*/
class SourceImpl extends SourceClass {}
/**
Concrete implementation of abstract TargetClass*/class TargetImpl extends TargetClass {public TargetImpl(String data) {super(data);}
@Overridepublic void doTask() {}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass target = new TargetImpl("test_data");
SourceClass source = new SourceImpl();
source.triggerProcess(target);
assert target.getData().equals("test_data_target_anon_reflected") : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}