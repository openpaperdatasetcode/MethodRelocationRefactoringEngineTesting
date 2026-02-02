package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
interface ActionInterface<T> {List<String> execute();}
private class SourceClass<T> implements ActionInterface<T> {static int staticField = 100;
class MemberInner {class InnerRec {/**
Method with Javadoc to return List<String>
@return List of strings
*/
public List<String> moveMethod(TargetClass target) {
List<String> result = new ArrayList<>();
int count = 0;
while (count < 3) {
variableCall(target);
result.add(String.valueOf(SourceClass.staticField + target.staticField));
count++;
}
return result;
}
private void variableCall(TargetClass target) {target.innerClass.doTask();}}}
public void initAnonymous() {new Supplier<Void>() {@Overridepublic Void get() {new MemberInner().new InnerRec().moveMethod(new TargetClass());return null;}}.get();}
// Override violation (incorrect return type)@Overridepublic String execute() {return "Violates interface method return type";}
public TargetClass callMethod(TargetClass target) {try {return target.new TargetInner().targetMethod(target);} catch (Exception e) {throw new RuntimeException("Call method failed", e);}}}
public class TargetClass {public static int staticField = 50;
class TargetInner {private TargetClass targetMethod(TargetClass target) {return target;}
void doTask() {}}
private TargetInner innerClass = new TargetInner();
public List<String> moveMethod() {return new ArrayList<>();}}