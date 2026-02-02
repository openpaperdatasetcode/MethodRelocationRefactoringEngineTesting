package test.refactor.movemethod;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
// Parent class for super.field and superTypeReferenceclass ParentSuperClass {protected int superField = 1; // target_feature: super.field, 1
public int superTypeMethod(String arg) {return arg.length();}}
// Source class: default modifier, same package with targetclass SourceClass {private String outerPrivateField = "outerPrivate"; // for access_outer_private
// Member inner class (method_position: source_inner)public class SourceInnerClass extends ParentSuperClass {// Target method: abstract, default access, return List<String>public abstract List<String> abstractMethod(TargetClass targetParam) throws IOException; // per_condition: contains target parameter, requires_throws
// Concrete implementation helper (for feature validation)public List<String> implementAbstractMethod(TargetClass targetParam) throws IOException {List<String> result = new ArrayList<>();
// Private ConstructorInvocation (type=ConstructorInvocation, modifier=private, target_feature=[super.field, 1], pos=source)privateConstructorInvocation();
// Protected instance method feature (type=instance, modifier=protected, pos=object initialization, method_feature=[1, inner_class, instance, superTypeReference.methodName(arguments)])int instanceResult = protectedInstanceMethod(targetParam);result.add(String.valueOf(instanceResult));
// Assert statementassert targetParam != null : "Target parameter cannot be null";
// Variable callLocalInnerClass localInner = new LocalInnerClass();localInner.helperMethod();targetParam.useAnonymousInnerClass(); // target_feature: anonymous inner class
// Access outer privateresult.add(SourceClass.this.outerPrivateField);
// Recursive call (target_inner_rec)if (result.size() < 3) {result.addAll(implementAbstractMethod(targetParam));}
// Requires_throws: simulate checked exception conditionif (targetParam.getCount() > 5) {throw new IOException("Count exceeds limit");}
return result;}
private void privateConstructorInvocation() {// target_feature: super.field (super.superField = 1)ParentSuperClass superInstance = new ParentSuperClass() {@Overridepublic int superTypeMethod(String arg) {return superField; // super.field = 1}};}
protected int protectedInstanceMethod(TargetClass targetParam) {// method_feature: inner_class, object initialization posTargetClass.InnerRecursiveClass innerRec = targetParam.new InnerRecursiveClass() {@Overrideint recursiveCall(int depth) {return depth;}};// method_feature: 1, superTypeReference.methodName(arguments)return super.superTypeMethod("arg") + innerRec.recursiveCall(1);}
// Local inner class (source_class feature)private class LocalInnerClass {public void helperMethod() {}}}
// Anonymous inner class (source_class feature)public Runnable createAnonymous() {return new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous inner class");}};}}
// Target class: public, target_feature=anonymous inner classpublic class TargetClass {private int count = 3;
public int getCount() {return count;}
// Inner recursive class (target_inner_rec)public abstract class InnerRecursiveClass {public abstract int recursiveCall(int depth);}
// Anonymous inner class (target_feature)public void useAnonymousInnerClass() {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class");}};runnable.run();}}
// Test classimport org.junit.Test;import static org.junit.Assert.*;import java.util.List;
public class MoveMethodRefactoring5317Test {@Testpublic void testAbstractMethodImplementation() throws IOException {SourceClass source = new SourceClass();SourceClass.SourceInnerClass inner = source.new SourceInnerClass();TargetClass targetParam = new TargetClass();
// Execute implementation methodList<String> result = inner.implementAbstractMethod(targetParam);
// Verify result contentassertEquals(3, result.size());assertTrue(result.containsAll(List.of("2", "outerPrivate", "2")));
// Verify per_condition: method contains target parameterassertNotNull(targetParam);
// Verify target_feature: anonymous inner classtargetParam.useAnonymousInnerClass();
// Verify requires_throwsTargetClass highCountTarget = new TargetClass() {@Overridepublic int getCount() {return 6;}};assertThrows(IOException.class, () -> inner.implementAbstractMethod(highCountTarget));}}