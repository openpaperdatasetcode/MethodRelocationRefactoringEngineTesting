package test;
import java.lang.reflect.Method;
// Protected source class (same package) with local inner and static nested classesprotected class SourceClass implements Processable {private String outerPrivateField = "source_private";
// Static nested class (source_class feature)protected static class StaticNested {public void assist(TargetClass target) {}}
// Static code block (method_feature position)static {TargetClass target = new TargetClass();SourceClass source = new SourceClass();source.new InnerHelper().invokeInnerMethod(target, "init1", "init2", "init3");}
class InnerClass {// Protected overriding method (position: source_inner)@Overrideprotected void process(TargetClass target) {// Access outer private fieldString combined = outerPrivateField + "_" + target.publicField;
// Access instance fieldtarget.publicField = combined.length();
// Depends on inner classStaticNested.assist(target);new LocalProcessor().processTarget(target);
// Variable callvariableCall(target);
// Call instance method_feature via instanceReferenceTargetClass result = new InnerHelper().invokeInnerMethod(target, "arg1", "arg2", "arg3");
// Used by reflectiontry {Method method = TargetClass.StaticNested.class.getDeclaredMethod("process", TargetClass.class);method.invoke(null, result);} catch (Exception e) {throw new RuntimeException("Reflection failed", e);}}
// Local inner class (source_class feature)private class LocalProcessor {void processTarget(TargetClass target) {target.publicField *= 2;}}
private void variableCall(TargetClass target) {target.doTask();}}
// Private instance method_feature (3 parameters, inner_class, instance)private class InnerHelper {public TargetClass invokeInnerMethod(TargetClass target, String arg1, String arg2, String arg3) {// instanceReference.methodName(arguments)target.staticNested.process(target);String data = arg1 + arg2 + arg3;return new TargetClass(data);}}}
// Protected target class with extends and static nested class (target_features)protected class TargetClass extends ParentClass {public int publicField = 0;private String data;
// Static nested class (target_feature)public static class StaticNested {public void process(TargetClass target) {}}
public StaticNested staticNested = new StaticNested();
public TargetClass() {}
public TargetClass(String data) {this.data = data;}
public void doTask() {}}
// Parent class for target_class extends featureclass ParentClass {}
// Interface for overriding featureinterface Processable {void process(TargetClass target);}
// Test class for Move Method refactoring verificationpublic class RefactoringTest {public static void main(String[] args) {TargetClass target = new TargetClass();SourceClass.InnerClass inner = new SourceClass().new InnerClass();inner.process(target);System.out.println("Refactoring test passed");}}