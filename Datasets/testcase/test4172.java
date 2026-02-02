package test;
import java.util.List;import java.util.ArrayList;
interface DataCollector {List<String> collect();}
public abstract class SourceClass {protected TargetClass targetField = new TargetClass ();
default List<String> abstractMethod (TargetClass.TargetInner inner) {
private void declarePrivateVars () {String field1Val = inner.objField1;String field2Val = inner.objField2;String field3Val = inner.objField3;}declarePrivateVars ();
List<String> result = new ArrayList<>();try {
TargetClass.TargetInner varCall = inner;result.add (varCall.objField1);result.add (varCall.objField2);result.add (varCall.objField3);
result.add (targetField.sourceRelatedField);} catch (Exception e) {
result.add ("Exception caught:" + e.getMessage ());}return result;}}
public class TargetClass implements DataCollector {
public String sourceRelatedField = "targetInstanceData";
public class TargetInner {
public String objField1 = "innerField1";public String objField2 = "innerField2";public String objField3 = "innerField3";}
@Overridepublic List<String> collect() {return new ArrayList<>(List.of(sourceRelatedField));}}
class SamePackageOthers {public static void useTargetInner (TargetClass.TargetInner inner) {System.out.println (inner.objField1);}}