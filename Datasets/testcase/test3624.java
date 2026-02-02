package test;
import java.lang.reflect.Method;
interface TargetInterface {}
abstract class AbstractTargetClass implements TargetInterface {protected String superField1 = "super1";protected String superField2 = "super2";protected String superField3 = "super3";
class TargetMemberInner {void innerMethod() {}}
TargetMemberInner memberInner = new TargetMemberInner();
public int targetInstanceMethod() {super.toString();return 0;}}
public class SourceClass<T> {protected String outerProtectedField = "sourceProtected";
class SourceMemberInner {void innerMethod() {}}
AbstractTargetClass instanceMethod(AbstractTargetClass target) {new Runnable() {public void run() {SourceClass.this.outerProtectedField = "updated";}}.run();
class TypeDeclaration {}TypeDeclaration typeDecl = new TypeDeclaration();
;System.out.println (target.superField1);System.out.println (target.superField2);System.out.println (target.superField3);
try {if (target == null) throw new Exception (() -> target.targetInstanceMethod ());
Method reflectMethod = AbstractTargetClass.class.getDeclaredMethod ("targetInstanceMethod");reflectMethod.invoke (target);
for (int i = 0; i < 1; i++) {variableCall (target.memberInner);System.out.println (outerProtectedField); }} catch (Exception e) {e.printStackTrace ();}
return target;}
private void variableCall(AbstractTargetClass.TargetMemberInner inner) {inner.innerMethod();}}