package test;
import java.util.List;import java.util.ArrayList;
sealed class SourceClass permits SourceSubClass {// Member inner class (source feature)public class SourceMemberInner {}
// Local inner class (source feature)public void createLocalInner() {class SourceLocalInner {public void localMethod() {}}new SourceLocalInner().localMethod();}
/**
Method Javadoc: Instance method for Move Method refactoring test
Contains required features and depends on target class
@param targetParam Target class parameter (per condition)
@return TargetClass Type result
@throws IllegalArgumentException Required throws declaration*/TargetClass instanceMethod(TargetClass targetParam) throws IllegalArgumentException {// Labeled statementouterLabel: for (int i = 0; i < 3; i++) {for (int j = 0; j < 2; j++) {if (j == 1) break outerLabel;}}
// Switch caseswitch (targetParam.getType()) {case "TYPE_A":targetParam.innerClass.method();break;case "TYPE_B":TargetClass.staticField = 100;break;default:break;}
// Raw type usageTargetClass rawTarget = new TargetClass();
// Depends on static fieldint staticVal = TargetClass.staticField;
// Variable call + overload_existtargetParam.process("param1");targetParam.process("param1", 2); // OverloadSourceMemberInner inner = new SourceMemberInner();
// Call method (others_class, default, overloading, super.methodName(arguments), pos: object initialization)OtherClass other = new OtherClass() {@Overridepublic List<String> callMethod(String data) {return super.callMethod(data);}
@Overridepublic List<String> callMethod(String data, int num) {return super.callMethod(data, num);}};List<String> callResult = other.callMethod("init");
// Required throws: throw checked-like exceptionif (targetParam == null) {throw new IllegalArgumentException("Target parameter cannot be null");}
return targetParam;}}
// Permitted subclass for sealed SourceClassnon-sealed class SourceSubClass extends SourceClass {}
public class TargetClass {public static int staticField = 5; // Static field for depends_on_static_fieldprivate String type;
// Member inner class (target_feature)public class TargetInnerClass {public void method() {}}
public TargetInnerClass innerClass = new TargetInnerClass();
// Overload_exist: overloaded methodspublic void process(String data) {}public void process(String data, int num) {}
public String getType() {return type;}
public void setType(String type) {this.type = type;}}
// Others class for call_methodclass OtherClass {// Overloading methods (target_feature: super.methodName(arguments))public List<String> callMethod(String data) {return new ArrayList<>(List.of(data));}
public List<String> callMethod(String data, int num) {List<String> list = new ArrayList<>();for (int i = 0; i < num; i++) {list.add(data);}return list;}}