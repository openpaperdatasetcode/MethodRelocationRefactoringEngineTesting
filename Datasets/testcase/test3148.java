package test;
class ParentTargetClass {protected static TargetClass superStaticMethod() {return new TargetClass();}}
final class TargetClass extends ParentTargetClass {String targetField;
public void createLocalInner() {class LocalInner {} // target_feature: local inner classnew LocalInner();}
public synchronized static TargetClass targetStaticMethod() {return super.superStaticMethod(); // super.methodName(arguments)}}
abstract class SourceClass {protected int outerProtectedField = 5;
class MemberInner {}
public SourceClass() {// Anonymous inner classRunnable r = new Runnable() {@Overridepublic void run() {}};}
/**
Method Javadoc
Abstract method returning TargetClass type, with required features
@param target TargetClass instance
@return TargetClass instance
*/
abstract TargetClass methodToMove(TargetClass target);
// Concrete implementation skeletonpublic static class SourceConcrete extends SourceClass {@OverrideTargetClass methodToMove(TargetClass target) {// Variable callString var = target.targetField;target.createLocalInner();
// Access outer protectedint protectedVal = super.outerProtectedField;
// While statement with static method callint count = 0;TargetClass staticResult = null;while (count < 1) {staticResult = OtherClass.InnerStatic.staticMethod(target); // 1 as requiredcount++;}
// If/else conditions with call_methodif (protectedVal > 3) {staticResult = TargetClass.targetStaticMethod();} else {staticResult = TargetClass.targetStaticMethod();}
return staticResult;}}
static class OtherClass {static class InnerClass {public static TargetClass staticMethod(TargetClass target) {target.targetField = "processed";return target;}}}}