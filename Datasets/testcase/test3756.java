package test;
import java.util.List;import java.util.ArrayList;import otherpkg.OtherClass;
class SourceClass {public static class SourceStaticNested1 {}public static class SourceStaticNested2 {}private static String sourceStaticField = "staticData";
private List<String> recursiveMethod(TargetClass target, int depth) {List<String> result = new ArrayList<>();
if (depth <= 0) {result.add(target.targetField);return result;}
OtherClass other = new OtherClass();other.privateTypeDecl = new OtherClass.PrivateTypeDecl() {@Overridevoid useField() {this.field = target.targetField;}};
Object methodResult = this.normalMethod(target);result.add(methodResult.toString());
String var = target.targetField + sourceStaticField;result.add(var);
result.addAll(recursiveMethod(target, depth - 1));return result;}
private List<String> recursiveMethod(TargetClass target) {return recursiveMethod(target, 2);}
default Object normalMethod(TargetClass target) {super.toString();target.targetField = "updated_" + sourceStaticField;return target.targetField;}}
protected class TargetClass {public String targetField = "targetData";public static class TargetStaticNested {}}
package otherpkg;
public class OtherClass {PrivateTypeDecl privateTypeDecl;
private abstract class PrivateTypeDecl {String field;abstract void useField();}}