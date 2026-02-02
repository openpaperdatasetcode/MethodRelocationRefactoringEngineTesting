package test.refactoring;
public class SourceClass {// Source contains target's field (per_condition)public static TargetClass targetField = new TargetClass();private static String sourceStaticField = "source_static"; // For depends_on_static_field
// Local inner class (source feature)public void sourceLocalMethod() {class SourceLocalInner {public void localMethod() {SourceClass.moveTargetMethod();}}new SourceLocalInner().localMethod();}
// Anonymous inner class (source feature)private Runnable sourceAnonymous = new Runnable() {@Overridepublic void run() {SourceClass.moveTargetMethod(10);}};
// Target method: static, Object, final, source positionpublic static final Object moveTargetMethod() throws NullPointerException { // NullPointerException + requires_throws// VariableDeclarationStatement (private, pos: source, target_feature: ClassName.field x1)private String declaredVar = TargetClass.targetStaticField; // ClassName.field (target_feature)
// Variable call + depends_on_static_fieldString var = sourceStaticField;Object result = var + declaredVar;
// Break statementouter:for (int i = 0; i < 5; i++) {if (i == 2) {break outer;}result = i;}
// NullPointerException (feature)if (result == null) {throw new NullPointerException("Result cannot be null");}
return result;}
// Overload_exist: overloaded static methodpublic static final Object moveTargetMethod(int param) throws NullPointerException {String var = sourceStaticField + param; // variable callObject result = var + TargetClass.targetStaticField;
if (param < 0) {throw new NullPointerException("Parameter cannot be negative");}
return result;}}
class TargetClass {// Target static field (ClassName.field - per_condition)public static String targetStaticField = "target_static_field";
// Anonymous inner class (target_feature)private Runnable targetAnonymous = new Runnable() {@Overridepublic void run() {System.out.println(targetStaticField);}};
// Member inner class (target_inner_rec: method's target position)public class TargetInnerRecClass {public void innerMethod() {}}
// Target inner rec class instancepublic TargetInnerRecClass targetInnerRecClass = new TargetInnerRecClass();}