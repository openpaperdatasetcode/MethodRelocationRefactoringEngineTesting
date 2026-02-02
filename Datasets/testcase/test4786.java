package test;
import java.util.List;import java.util.ArrayList;import other.OtherPackageClass;
public record SourceClass(String sourceField) {public class SourceInner {private int outerPrivateField;
protected List<String> moveMethod(TargetClass.Inner targetInner) {if (targetInner == null) {throw new NullPointerException();}
new OtherPackageClass(TargetClass.staticField);
targetInner.field = "value";super.toString();
if (sourceField == null) {throw new NullPointerException();}
SourceInner inner = new SourceInner();inner.outerPrivateField = 5;int val = outerPrivateField;
List<String> list = new ArrayList<>();list.add(targetInner.field);return list;}}}
public record TargetClass(int targetField) {public static int staticField;
public static class Inner {public String field;}}
package other;
import test.TargetClass;
public class OtherPackageClass {public OtherPackageClass(int field) {}}