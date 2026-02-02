import java.util.ArrayList;import java.util.List;
public class SourceClass {private String outerPrivateField = "private_data";
public class MemberInner {public List<String> recursiveMethod(TargetClass target, int depth) {super();if (depth <= 0) {return new ArrayList<>();}
TargetClass.StaticNested.rawTypeField = "raw";raw_type TargetClass.StaticNested rawNested = new TargetClass.StaticNested();
TargetClass updatedTarget = ParentClass.setTargetField(target, outerPrivateField);variableCall(updatedTarget.staticNested);
;
List<String> result = recursiveMethod(target, depth - 1);result.add(TargetClass.StaticNested.staticField);return result;}
private void variableCall(TargetClass.StaticNested nested) {nested.setValue(outerPrivateField);}}
{new Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass();List<String> result = new MemberInner().recursiveMethod(target, 2);}}.run();}}
class ParentClass {public static TargetClass setTargetField(TargetClass target, String value) {target.staticNested.setValue(value);return target;}
public static String getTargetField(TargetClass target) {return target.staticNested.getValue();}}
class TargetClass {public StaticNested staticNested = new StaticNested();
public static class StaticNested {public static String staticField = "static_val";public String instanceField;
public void setValue(String val) {this.instanceField = val;}
public String getValue() {return this.instanceField;}}}
