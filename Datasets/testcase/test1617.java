package test;
import java.util.ArrayList;import java.util.List;
sealed class SourceClass permits SourceSubClass {protected String outerProtected = "protected_data";
public class MemberInner {public TargetClass.StaticNested.Rec createRec(String data) {return new TargetClass.StaticNested.Rec(data);}}
public List<String> process(TargetClass.StaticNested.Rec targetRec) {List<String> result = new ArrayList<>();MemberInner inner = new MemberInner();
// Anonymous inner classRunnable initializer = new Runnable() {@Overridepublic void run() {targetRec.data = outerProtected;}};initializer.run();
// Constructor invocationTargetClass target = new TargetClass();TargetClass.StaticNested nested = new TargetClass.StaticNested();
// Do statementint count = 0;do {// Variable call - access target inner rec's fieldresult.add(targetRec.data);count++;} while (count < targetRec.counter);
// Inner class with private ConstructorInvocation (2 obj.field accesses)class RecProcessor {TargetClass.StaticNested.Rec process() {private TargetClass.StaticNested.Rec newRec1 = new TargetClass.StaticNested.Rec(targetRec.data);private TargetClass.StaticNested.Rec newRec2 = new TargetClass.StaticNested.Rec(targetRec.data + "_copy");return newRec1;}}result.add(new RecProcessor().process().data);
// Super keywordresult.add(super.toString());
// Access outer protected fieldtargetRec.data = outerProtected + "_updated";
// Depends on inner classresult.add(nested.processRec(targetRec));
// Return statementreturn result;}}
final class SourceSubClass extends SourceClass {}
public class TargetClass {public static class StaticNested {public static class Rec {public String data;public int counter = 2;
public Rec(String data) {this.data = data;}}
public String processRec(Rec rec) {return "processed: " + rec.data;}}}