package test;
import otherpackage.OtherClass;import java.util.function.Supplier;
class SourceClass {static class StaticNested {}class MemberInner {}
int normalMethod(TargetClass targetParam) {// ConstructorInvocation: private modifier, diff_package_others, ClassName.field (1)otherpackage.OtherClass.PrivateInner otherInner = new otherpackage.OtherClass.PrivateInner(OtherClass.staticField);
// Super constructor invocationsuper();
// Type declaration statementTargetClass.MemberInner targetInner;
// Super keywordsString superStr = super.toString();
// Variable calltargetParam.doAction();targetInner = targetParam.new MemberInner();
// Call_method: source protected instance, ClassName::methodName in expressionSupplier<TargetClass> supplier = SourceClass::protectedMethod;TargetClass result = supplier.get();
return targetInner.getValue();}
protected TargetClass protectedMethod() {return new TargetClass();}}
class TargetClass {class MemberInner {int getValue() {return 42;}}
void doAction() {}}
// Different package: otherpackagepackage otherpackage;
public class OtherClass {public static final String staticField = "otherStaticField";
private static class PrivateInner {private PrivateInner(String field) {}}}