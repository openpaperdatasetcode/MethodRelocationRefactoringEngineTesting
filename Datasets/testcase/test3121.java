package test;
import java.util.List;import java.util.ArrayList;
// Different packagepackage other;
import test.TargetClass;
public class OtherPackageClass {public void process(TargetClass<?>.TargetInner.TargetInnerRec rec) {// VariableDeclarationStatement with this.field and 3class VarDecl {private int field;public void init() {this.field = 3;}}new VarDecl().init();}}
// Same packagepackage test;
interface ParentInterface {}
class TargetClass<T> {T targetField;
public void example() {class LocalInner {} // target_feature: local inner class}
class TargetInner {record TargetInnerRec(T value) {} // target_inner_rec}
public static final String staticMethod() { // call_method: staticreturn "static_result";}}
public sealed class SourceClass<T> extends ParentClass<T> permits SourceSubClass {private T outerPrivateField;class Inner1 {}class Inner2 {}
// Abstract method (overload 1)public strictfp abstract <U extends TargetClass<T>> TargetClass<T> methodToMove(U target, TargetClass<T>.TargetInner.TargetInnerRec rec);
// Overload exists (overload 2)public strictfp abstract <U extends TargetClass<T>> TargetClass<T> methodToMove(U target, TargetClass<T>.TargetInner.TargetInnerRec rec, String arg);
// Constructor with call_method in parameter listpublic SourceClass() {this(TargetClass.staticMethod()); // OuterClass.InnerClass.methodName()}
private SourceClass(String param) {}
protected void helperMethod() {// For statementfor (int i = 0; i < 2; i++) {}
// CreationReference with numbers:2List<TargetClass<T>> list = new ArrayList<>();list.add(TargetClass::new);list.add(TargetClass::new);
// Variable call + Access outer privateTargetClass<T> target = new TargetClass<>();T var = target.targetField;this.outerPrivateField = var;
// Call different package classnew other.OtherPackageClass().process(target.new TargetInner().new TargetInnerRec(var));}}
// Permitted subclassfinal class SourceSubClass<T> extends SourceClass<T> {@Overridepublic strictfp <U extends TargetClass<T>> TargetClass<T> methodToMove(U target, TargetClass<T>.TargetInner.TargetInnerRec rec) {helperMethod();return target;}
@Overridepublic strictfp <U extends TargetClass<T>> TargetClass<T> methodToMove(U target, TargetClass<T>.TargetInner.TargetInnerRec rec, String arg) {helperMethod();target.targetField = (T) arg;return target;}}
class ParentClass<T> {}