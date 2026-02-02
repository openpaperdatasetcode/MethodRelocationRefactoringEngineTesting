package test;
import java.util.List;import java.util.ArrayList;
// Others class for call_methodclass OtherClass {public static int staticChainMethod() {return new OtherClass().m1().m2().m3();}
public OtherClass m1() { return this; }public OtherClass m2() { return this; }public int m3() { return 5; }}
// Source enum class (public modifier, two member inner classes)public enum SourceEnum {INSTANCE;
// First member inner class (source feature)public class FirstMemberInner {}
// Second member inner class (source feature)public class SecondMemberInner extends TargetEnum.SubEnum {// Private constructor method feature (2, sub_class, constructor, this.methodName(arguments), pos: switch)private SecondMemberInner(String param1, String param2) {super();this.init(param1, param2);}
private void init(String p1, String p2) {}}
/**
Method Javadoc: Varargs method for Move Method refactoring test
@param targetParams Target enum parameters (per condition)
@return List<String> result*/public List<String> varargsMethod(TargetEnum... targetParams) {List<String> result = new ArrayList<>();FirstMemberInner firstInner = new FirstMemberInner();
// ArrayInitializer (numbers=2, modifier=private)private String[] arr1 = {"init1", "init2"};private String[] arr2 = {"itemA", "itemB"};
for (TargetEnum target : targetParams) {// Uses outer thisSourceEnum.this.processTarget(target);
// Super constructor invocation (target's sub enum super)SecondMemberInner secondInner = new SecondMemberInner(arr1[0], arr2[0]);
// ThrowStatement (public, target_feature: obj.field x3, pos: source)public IllegalArgumentException ex;if (target.field1 < 0 || target.field2 < 0 || target.field3 < 0) {ex = new IllegalArgumentException("Invalid fields: " + target.field1 + "," + target.field2 + "," + target.field3);throw ex;}
// Variable calltarget.targetMethod();target.createLocalInner();result.add(target.value().toString());
// Constructor method feature in switchswitch (target.field1) {case 1:SecondMemberInner feat1 = new SecondMemberInner("switch1", "val1");break;case 2:SecondMemberInner feat2 = new SecondMemberInner("switch2", "val2");break;}
// Call method (others_class, public, static, obj.m1().m2().m3(), pos: array initialization)int callResult = OtherClass.staticChainMethod();result.add(String.valueOf(callResult));}
return result;}
private void processTarget(TargetEnum target) {}}
// Target enum class (protected modifier, local inner class)protected enum TargetEnum {VALUE1("one"), VALUE2("two");
private final String value;public int field1 = 1; // obj.field 1public int field2 = 2; // obj.field 2public int field3 = 3; // obj.field 3
TargetEnum(String value) {this.value = value;}
public String value() {return value;}
public void targetMethod() {}
// Local inner class (target_feature)public void createLocalInner() {class TargetLocalInner {public void localMethod() {}}new TargetLocalInner().localMethod();}
// Sub enum for sub_class featurepublic static non-sealed enum SubEnum {}}
