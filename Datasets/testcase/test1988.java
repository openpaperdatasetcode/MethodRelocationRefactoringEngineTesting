package test;
import java.util.List;import java.util.ArrayList;
interface MyInterface {}
class SourceClass implements MyInterface {protected int outerProtectedField = 5;
class MemberInner {}
private static List<String> process(TargetClass.Inner targetInner) {class LocalInner {}List<String> result = new ArrayList<>();
private synchronized (targetInner) {if (targetInner.superField == 1) {result.add(String.valueOf(targetInner.superField));}result.add(String.valueOf(new SourceClass().outerProtectedField));}
return result;}
private static List<String> process(String str) {return List.of(str);}}
abstract class TargetClass {class Inner {int superField;}}