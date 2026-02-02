package test;
import java.lang.reflect.Method;import java.util.function.Supplier;
/**
TargetClass Javadoc
Target class with anonymous inner class feature*/protected class TargetClass {String targetField;
public TargetClass() {// Target feature: anonymous inner classRunnable r = new Runnable() {@Overridepublic void run() {}};}
// Target instance method for method_featurepublic Object targetInstanceMethod(int param) {return targetField + "_" + param;}}
// Sub_class of TargetClass for call_methodclass TargetSubClass extends TargetClass {@Overridepublic Object targetInstanceMethod(int param) {return super.targetInstanceMethod(param) + "_sub"; // super.methodName(arguments)}
// Call_method: public instance methodpublic int subClassInstanceMethod() {return targetField != null ? targetField.length() : 0;}}
// Source class (final modifier + anonymous inner class + member inner class)final class SourceClass {// Source feature: member inner classclass SourceInner {}
// Source feature: anonymous inner classprivate final Runnable anonymous = new Runnable() {@Overridepublic void run() {new SourceClass().methodToMove(new TargetClass());}};
// Instance method (public access + TargetClass return)public TargetClass methodToMove(TargetClass target) {// Constructor invocationSourceInner inner = new SourceInner();TargetSubClass subClass = new TargetSubClass();
// Variable callString var = target.targetField;target.targetField = "processed";
// Super keywordssuper.toString();
// Switch caseswitch (var != null ? var.length() : 0) {case 0:var = "empty";break;case 1:var = "short";break;default:var = "long";}
// For statement with method_feature (this.methodName(arguments))for (int i = 0; i < 1; i++) {Object methodResult = target.targetInstanceMethod(i);}
// Used_by_reflectiontry {Method instanceMethod = TargetClass.class.getDeclaredMethod("targetInstanceMethod", int.class);Object reflectedResult = instanceMethod.invoke(target, 1);} catch (Exception e) {// No new exception thrown}
// Lambda expressions with call_methodSupplier<Integer> lambda = subClass::subClassInstanceMethod;int lambdaResult = lambda.get();
return target;}}