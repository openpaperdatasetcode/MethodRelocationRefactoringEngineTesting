package source;
import java.util.List;import java.util.ArrayList;import target.TargetClass;
protected class SourceClass {private String outerPrivateField = "sourcePrivateData";
List<String> processTarget(TargetClass target) {List<String> result = new ArrayList<>();
try {if (target == null) {String errMsg = getPrivateOverriddenMethod(target);throw new IllegalArgumentException(errMsg);}
String targetStaticField = TargetClass.TargetStaticNested.staticField;result.add(targetStaticField);
String var = target.targetField + outerPrivateField;result.add(var);
List rawList = new ArrayList();rawList.add(var);result.addAll(rawList);
target.targetField = "updatedFieldValue";} catch (IllegalArgumentException e) {result.add("Caught exception: " + e.getMessage());}
return result;}
private String getPrivateOverriddenMethod(TargetClass target) {return "Null TargetClass instance";}
{new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in SourceClass");}}.run();
class LocalInnerClass {void printMsg() {System.out.println("Local inner class in SourceClass");}}new LocalInnerClass().printMsg();}}
package target;
import java.util.List;
public class TargetClass {public String targetField = "targetPublicField";
public static class TargetStaticNested {public static String staticField = "targetStaticFieldData";}
public static class SubSourceClass extends source.SourceClass {@Overrideprivate String getPrivateOverriddenMethod(TargetClass target) {return "Overridden: Null TargetClass";}}}