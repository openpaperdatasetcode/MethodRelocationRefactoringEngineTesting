package test;
import java.util.ArrayList;import java.util.List;
protected enum SourceEnum {INSTANCE;
private TargetEnum targetField = TargetEnum.VALUE;
void outerMethod() {class FirstLocalInner {class SecondLocalInner {/**
Javadoc for overloaded method (takes no params)
@return List of strings
*/
List<String> processTarget() {
List<String> result = new ArrayList<>();
try {
assert targetField != null;
result.add(targetField.name());
variableCallHelper(result);
} catch (Exception e) {
e.printStackTrace();
}
return result;
}
/**
Javadoc for overloaded method (takes String param)
@param suffix String to append
@return List of strings
*/
List<String> processTarget(String suffix) {
List<String> result = new ArrayList<>();
try {
assert targetField.getDescription() != null;
result.add(targetField.getDescription() + suffix);
variableCallHelper(result);
} catch (Exception e) {
e.printStackTrace();
}
return result;
}
private void variableCallHelper(List<String> list) {list.add("processed");}}}
class ThirdLocalInner {void useOverloadedMethods() {FirstLocalInner.SecondLocalInner inner = new FirstLocalInner().new SecondLocalInner();inner.processTarget();inner.processTarget("_suffix");}}new ThirdLocalInner().useOverloadedMethods();}}
public enum TargetEnum {VALUE {@OverrideString getDescription() {return "TargetValueDesc";}};
private final Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class");}};
abstract String getDescription();}
