package test;
import java.util.List;import java.util.ArrayList;
final class OthersClass {public static void accessorMethod (TargetEnum target, String... args) {for (String arg : args) {System.out.println (target.innerField + "-" + arg);}}
public void varargsOverload1(TargetEnum.TargetInner inner, String... data) {}public void varargsOverload2(TargetEnum target, Integer... nums) {}}
public enum SourceEnum {INSTANCE1, INSTANCE2;
private TargetEnum targetField = TargetEnum.TARGET;private static final String STATIC_FIELD = "sourceStatic";
class FirstSourceInner {int getInnerValue () {return targetField.innerField.length ();}}
class SecondSourceInner {void processTarget (TargetEnum target) {System.out.println (target.innerField);}}
public int instanceMethod () {FirstSourceInner firstInner = new FirstSourceInner ();SecondSourceInner secondInner = new SecondSourceInner ();OthersClass others = new OthersClass ();
static void staticContinueLoop () {List<String> dataList = new ArrayList<>();dataList.add(TargetEnum.TARGET.innerField);for (int i = 0; i < dataList.size(); i++) {if (dataList.get(i).isEmpty()) {continue;}System.out.println(dataList.get(i));}}staticContinueLoop();
try {TargetEnum.TargetInner targetInner = targetField.new TargetInner ();others.varargsOverload1 (targetInner, STATIC_FIELD, targetField.innerField, "vararg3");} catch (Exception e) {}
int count = 0;do {OthersClass.accessorMethod (targetField, "doArg1", "doArg2");count++;} while (count < 2);
TargetEnum varCall = targetField;int result = varCall.innerField.length () + firstInner.getInnerValue ();if (STATIC_FIELD.contains ("static")) {result += 5;}
others.varargsOverload2 (varCall, 1, 2, 3);return result;}}
enum TargetEnum {TARGET;
String innerField = "targetInnerField";
class TargetInner {String getInnerData () {return innerField;}}}
