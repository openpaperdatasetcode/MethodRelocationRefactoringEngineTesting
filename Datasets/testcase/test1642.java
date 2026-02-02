package test;
import java.util.ArrayList;import java.util.List;import other.OthersClass;
abstract class SuperType {public abstract List<String> combine(String... parts);}
non-sealed abstract class SourceClass extends SuperType {private String outerPrivate = "outer_private";
public static class StaticNested {public static int calculateLength(String data) {return data.length();}}
public class MemberInner {// Overriding method in source_inner with final access modifier@Overridepublic final void combine(String... parts) {// Local inner classclass TargetHandler {void process(TargetClass target) {target.inner.data = outerPrivate;}}TargetHandler handler = new TargetHandler();
// Enhanced for statementfor (TargetClass.Inner inner : targetList) {// Variable call - access target inner's fieldparts = new String[]{inner.data, outerPrivate};}
// Switch statementswitch (parts.length) {case 0:throw new IllegalArgumentException("No parts provided");case 1:System.out.println(parts[0]);break;default:break;}
// Public varargs method from others_class in exception throwing (2 parameters)try {List<String> result = OthersClass.merge(SuperType.this, parts[0], parts[1]);} catch (IllegalStateException e) {throw new RuntimeException("Merge failed", e);}
// Access outer private fieldTargetClass target = new TargetClass();target.inner.data = outerPrivate;
// Raw type usageList rawList = new ArrayList();rawList.add(target.inner.data);
// Call source's public static method in for loopint totalLength = 0;for (String part : parts) {totalLength += StaticNested.calculateLength(part);}}
private List<TargetClass.Inner> targetList = new ArrayList<>();}}
private abstract class TargetClass {public Inner inner = new Inner();
public class Inner {public String data;}}
package other;
import test.SuperType;import java.util.Arrays;import java.util.List;
public class OthersClass {// Public varargs method for source's callpublic static List<String> merge(SuperType superType, String... elements) {return Arrays.asList(elements);}}