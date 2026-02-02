package test;
import java.util.List;import java.util.ArrayList;import targetpkg.TargetClass;
public sealed class SourceClass permits SourceSubClass {public class MemberInner {}
public List<String> SourceClass(TargetClass.TargetInner targetInner) {class LocalInner {}LocalInner local = new LocalInner();
try {static VariableDeclarationStatement() {String staticField = TargetClass.StaticNested.targetStaticField;}
String targetField = targetInner.innerField;accessInstanceMethod(targetField);
List<String> result = new ArrayList<>();result.add(targetField);return result;} catch (Exception e) {return new ArrayList<>();}}
public List<String> SourceClass(String strParam) {return new ArrayList<>(List.of(strParam));}
private void accessInstanceMethod(String param) {System.out.println("Instance method called with: " + param);}}
final class SourceSubClass extends SourceClass {}
package targetpkg;
import java.util.List;
class TargetClass {public class TargetInner {public String innerField;}
public static class StaticNested {public static String targetStaticField = "targetStaticData";}
void targetMethod() {class TargetLocalInner {}TargetLocalInner local = new TargetLocalInner();}}