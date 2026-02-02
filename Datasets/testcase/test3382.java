package test;
import otherpkg.OtherPackageClass;
non-sealed abstract class SourceClass extends ParentClass {protected String outerProtectedField = "protected_value";
public static class StaticNested {}
public class MemberInner {}
private void normalMethod(TargetClass targetParam) {// NullPointerException handlingif (targetParam == null) {throw new NullPointerException("Target parameter cannot be null");}
// Labeled statementouterLabel:for (int i = 0; i < 2; i++) {// Switch case with NumberLiteral (2 public instances)switch (i) {case 1:System.out.println("NumberLiteral: 1");break;case 2:System.out.println("NumberLiteral: 2");break outerLabel;}}
// Raw type usageTargetClass.MemberInner rawInner = new TargetClass.MemberInner();
// Access outer protected fieldString protectedVal = outerProtectedField;
// Variable call to target class methodtargetParam.targetMethod();
// Constructor invocation (same package)TargetClass.MemberInner samePkgInner = new TargetClass.MemberInner();
// ConstructorInvocation (diff_package_others, transient modifier)OtherPackageClass transientObj = new OtherPackageClass(TargetClass.STATIC_FIELD, TargetClass.ANOTHER_STATIC_FIELD);}}
abstract class ParentClass {}
final abstract class TargetClass implements Runnable {public static final int STATIC_FIELD = 10;public static final int ANOTHER_STATIC_FIELD = 20;
public class MemberInner {}
public void targetMethod() {}
@Overridepublic void run() {}}
// Diff package class (simulated in separate logical package)package otherpkg;
import test.TargetClass;
public class OtherPackageClass {public transient OtherPackageClass(int field1, int field2) {}}