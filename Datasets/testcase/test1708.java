package test;
import java.util.ArrayList;import java.util.List;
public enum SourceEnum {VALUE;
static class StaticNested {}
{new Runnable() {};}
protected List<String> instanceMethod(TargetEnum.MemberInner.InnerRec param) {// Access target fieldString fieldVal = param.targetField;
// Instance method from target using super.methodName() in property assignmentparam.value = param.superMethod();variableCall();
// Access instance method of target inner recordparam.instanceMethod();
// Call others_class constructor with method in switchswitch (param.toString()) {case "case1":Object obj = new OthersClass().method();break;default:break;}
return new ArrayList<>();}
private void variableCall() {}}
enum TargetEnum {TYPE;
class MemberInner {class InnerRec extends ParentClass {String targetField;int value;
void instanceMethod() {}
private int superMethod() {return super.parentMethod();}}}}
class ParentClass {protected int parentMethod() {return 0;}}
class OthersClass {protected Object method() {return new Object();}}