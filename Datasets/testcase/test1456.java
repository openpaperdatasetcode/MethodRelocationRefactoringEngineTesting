package test.refactoring;
// Source class: normal, default modifier, same package, has local inner/anonymous inner classclass SourceClass extends ParentClass {private int sourceInstanceField = 5; // For access_instance_field
// Anonymous inner class (source feature)private Runnable sourceAnonymous = new Runnable() {@Overridepublic void run() {new SourceInnerClass().moveTargetMethod(new TargetClass());}};
// Member inner class (source_inner: method's original position)class SourceInnerClass {// Target method: instance, base type (int), default access, source_inner position// per_condition: contains target parameter (TargetClass)int moveTargetMethod(TargetClass targetParam) {// Super constructor invocation (via parent class)super();
int result = 0;int var = sourceInstanceField; // variable call + access_instance_field
// Try statement + no_new_exceptiontry {// EnhancedForStatement (private, pos: diff_package_others, target_feature: obj.field x3)private int sum = 0;for (TargetClass.TargetData data : targetParam.dataList) {sum += data.field1; // obj.field 1sum += data.field2; // obj.field 2sum += targetParam.targetField; // obj.field 3}
// Instance method feature (protected, inner_class, instance, super.methodName())InnerInstanceMethod innerMethod = new InnerInstanceMethod();// pos: ternary operatorsboolean flag = true;flag ? innerMethod.execute(targetParam) : innerMethod.execute(targetParam);} catch (RuntimeException e) {// No new checked exception}
return result + var;}
// Inner class for method_feature (inner_class)protected class InnerInstanceMethod {// method_feature: 1 (single method), instance, super.methodName()public void execute(TargetClass target) {super.toString(); // super.methodName()System.out.println(target.targetField);}}}
// Local inner class (source feature)public void sourceLocalMethod() {class SourceLocalInner {public void invokeInnerMethod() {new SourceInnerClass().moveTargetMethod(new TargetClass());}}new SourceLocalInner().invokeInnerMethod();}
public SourceClass() {super();}}
// Parent class for super constructor invocationclass ParentClass {}
// Target class: normal, default modifier, has anonymous inner class (target_feature)class TargetClass {int targetField = 10; // obj.field targetTargetData[] dataList = {new TargetData(1,2), new TargetData(3,4)};
// Nested data class for EnhancedForStatementstatic class TargetData {int field1;int field2;TargetData(int f1, int f2) {this.field1 = f1;this.field2 = f2;}}
// Anonymous inner class (target_feature)private Runnable targetAnonymous = new Runnable() {@Overridepublic void run() {System.out.println(targetField);}};
// Inner class for call_methodfinal class CallerInnerClass {// Call method: inner_class, final, instance, super.methodName(), pos: do-while, return StringString callMethod(SourceClass.SourceInnerClass inner) {String result = "";int i = 0;// pos: do-whiledo {result = String.valueOf(inner.moveTargetMethod(new TargetClass()));// target_feature: super.methodName()super.toString();i++;} while (i < 2);return result;}}
// Caller inner class instanceCallerInnerClass caller = new CallerInnerClass();}