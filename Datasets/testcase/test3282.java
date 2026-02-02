package sourcepkg;
import java.util.function.Supplier;import targetpkg.TargetClass;import targetpkg.TargetSubClass;import targetpkg.OtherClass;
// Sealed source class (permits + anonymous inner class + static nested class)sealed class SourceClass permits SourceSubClass {// Static nested class (source feature)public static class SourceStaticNested {}
// Anonymous inner class (source feature)private final Runnable anonRunnable = new Runnable() {@Overridepublic void run() {}};
// Member inner class (method_position: source_inner)public class SourceInner {// Abstract method (public access modifier, return_type: void)/**
Method Javadoc: Abstract method with target parameters
@param target Instance of TargetClass (per condition)
*/
public abstract void abstractMethod(TargetClass target);
// Instance code blocks for method feature{// Instance method feature (3, sub_class, instance, new ClassName().method(), pos: instance code blocks)new TargetSubClass().subClassMethod();new TargetSubClass().subClassMethod();new TargetSubClass().subClassMethod();}
// SynchronizedStatement (private, target_feature: ClassName.field x3, pos: source)private void syncMethod() {synchronized (TargetClass.class) {String f1 = TargetClass.staticField1;String f2 = TargetClass.staticField2;String f3 = TargetClass.staticField3;}}}}
// Permitted subclass of SourceClassnon-sealed class SourceSubClass extends SourceClass {@Overridepublic class SourceInner {@Overridepublic void abstractMethod(TargetClass target) {// Constructor invocationSourceStaticNested nested = new SourceStaticNested();TargetClass.TargetInner inner = target.new TargetInner();
// While statementint count = 0;while (count < 2) {// Variable call + access_instance_methodtarget.targetMethod();inner.innerMethod();count++;}
// Call method (others_class, private, static, ClassName::methodName, pos: property assignment)Supplier<String> supplier = OtherClass::privateStaticMethod;String propValue = supplier.get();}}}
package targetpkg;
// Target class (default modifier, member inner class)class TargetClass {public static String staticField1 = "field1"; // ClassName.field for SynchronizedStatementpublic static String staticField2 = "field2";public static String staticField3 = "field3";public String instanceField; // Field for per_condition
public void targetMethod() {}
// Member inner class (target_feature)public class TargetInner {public void innerMethod() {}}}
// Target sub_class for method featureclass TargetSubClass extends TargetClass {public void subClassMethod() {}}
// Others class for call_methodclass OtherClass {private static String privateStaticMethod() {return "called";}}