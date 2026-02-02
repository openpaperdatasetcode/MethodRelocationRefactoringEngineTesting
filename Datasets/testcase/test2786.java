package test;
import java.util.List;import java.util.ArrayList;import java.util.Arrays;
final class SourceClass<T> implements SomeInterface {protected String outerProtectedField = "protected";
class MemberInner1 {}class MemberInner2 {}
strictfp List<String> methodToMove(TargetClass.StaticNested... nestedParams) {
TargetClass.StaticNested newNested = new TargetClass.StaticNested(TargetClass.targetField);
List rawList = new ArrayList();for (TargetClass.StaticNested nested : nestedParams) {nested.toString();rawList.add(nested);nested.doSomething();}
String accessProtected = this.outerProtectedField;return rawList;}
protected SourceClass(TargetClass.StaticNested nested, Runnable ref) {callMethod(nested, this::methodToMove);}
private static void callMethod(TargetClass.StaticNested nested, Runnable ref) {ref.run();}}
abstract class TargetClass {public static String targetField = "targetStaticField";
static class StaticNested {public StaticNested(String field) {}public void doSomething() {}}}
interface SomeInterface {}