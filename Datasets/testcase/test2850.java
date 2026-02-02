package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
protected class SourceClass {private int outerPrivateField = 42;private TargetClass instanceField = new TargetClass();
static class StaticNested {}
class MemberInner {/**
Javadoc for instanceMethod: processes TargetClass parameters and returns TargetClass instance
@param target parameter of TargetClass type (meets per_condition)
@return TargetClass instance*/TargetClass instanceMethod(TargetClass target) {labeledBlock: {// Access outer private fieldint privateVal = outerPrivateField;// Access instance fieldTargetClass fieldRef = instanceField;
// Volatile variables for MethodInvocation expressionvolatile TargetClass volatileTarget1 = target;volatile TargetClass volatileTarget2 = new TargetClass();
// For loop (pos for generic method feature)for (int i = 0; i < 1; i++) {// Generic method reference: instanceReference::methodNameGenericInterface<Integer> genericFunc = volatileTarget1::genericMethod;int baseVal = genericFunc.execute(10);
// Raw type usageRawList rawList = new RawList();rawList.add(volatileTarget2);}
// Variable callvariableCall(target);
// Depends on inner class (anonymous inner class)DependencyHelper helper = new DependencyHelper() {@OverrideTargetClass process(TargetClass t) {return t.createLocalInner().getValue();}};TargetClass result = helper.process(target);
if (privateVal > 0) break labeledBlock;}
// Do-while (pos for call_method)do {// SuperTypeReference.methodName(arguments)List<String> callResult = SuperType.superTypeStaticMethod(target);} while (false);
return instanceField;}
private void variableCall(TargetClass target) {TargetClass localTarget = target;localTarget.createLocalInner().getValue();}
// Final call_method (source type, normal feature)final List<String> superTypeStaticMethod(TargetClass target) {return SuperType.superTypeStaticMethod(target);}}}
class TargetClass {// Local inner class (meets target_feature)TargetClass createLocalInner() {class LocalInner {TargetClass getValue() {return TargetClass.this;}}return new LocalInner().getValue();}}
// Others_class for generic method featureclass GenericHelper {// Generic method (public modifier, base type return)public <T extends Number> int genericMethod(T num) {return num.intValue() * 2;}}
interface GenericInterface<T extends Number> {int execute(T t);}
// Raw type classclass RawList {private List list = new ArrayList();public void add(Object obj) {list.add(obj);}}
// Dependency inner classabstract class DependencyHelper {abstract TargetClass process(TargetClass t);}
// SuperType for call_method's superTypeReferenceclass SuperType {public static List<String> superTypeStaticMethod(TargetClass target) {return new ArrayList<>();}}