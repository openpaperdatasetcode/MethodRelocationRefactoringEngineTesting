package test;
import otherpkg.OtherDiffPackageClass;import java.util.List;import java.util.ArrayList;
private class SourceClass {static class SourceStaticNested {}
class SourceMemberInner {private List<String> normalMethod(TargetClass targetParam) {new Runnable() {@Overridepublic void run() {targetParam.staticNested.doAction();}}.run();
List<String> result = new ArrayList<>();do {result = this.overloadedMethod(targetParam, 1);} while (result.isEmpty());
System.out.println(super.toString()); // Super keywordstargetParam.doSomething(); // Variable callSourceStaticNested nested = new SourceStaticNested(); // Depends on inner class
return result;}
public List<String> overloadedMethod(TargetClass target) {return new ArrayList<>(List.of(target.name));}
public List<String> overloadedMethod(TargetClass target, int count) {return new ArrayList<>(List.of(target.name + count));}}}
private class TargetClass {String name = "target";static TargetStaticNested staticNested = new TargetStaticNested();
static class TargetStaticNested {void doAction() {}}
void doSomething() {OtherDiffPackageClass.publicIfStatement(this);}}
package otherpkg;
import test.TargetClass;import java.util.List;
public class OtherDiffPackageClass {public static void publicIfStatement(TargetClass target) {if (target.name != null && target.staticNested != null) { // this.field (2 targets)System.out.println("Valid target");}}}
class ParentClass {// Override violation (method not properly overridden)public List<String> normalMethod(TargetClass target) {return new ArrayList<>();}}