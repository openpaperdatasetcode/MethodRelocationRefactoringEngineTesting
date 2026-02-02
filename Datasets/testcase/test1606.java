package source;
import target.TargetClass;import java.util.function.Consumer;
interface DataHandler {void handle();}
private class SourceClass implements DataHandler {protected String outerProtected = "source_protected";
public class MemberInner {public void process(TargetClass.Inner.Rec rec) {// Access target inner rec's fieldrec.value = outerProtected;}}
public static class StaticNested {public static void log(String message) {System.out.println("Log: " + message);}}
@Overridepublic void handle() {}
public void process(TargetClass.Inner.Rec... recs) {MemberInner inner = new MemberInner();String localVar = "local_data";
// this.var = varthis.outerProtected = localVar;
// Expression statementTargetClass.StaticNested counter = new TargetClass.StaticNested();
for (TargetClass.Inner.Rec rec : recs) {// Variable call - access target inner rec's fieldStaticNested.log("Current value: " + rec.value);
// Access instance field of target inner recrec.count++;
// Access outer protected fieldinner.process(rec);
// Expression statementcounter.value += rec.count;}}}
package target;
public abstract class TargetClass extends ParentClass {public class Inner {public class Rec {public String value;public int count;}}
public static class StaticNested {public int value;}}
package target;
public class ParentClass {}