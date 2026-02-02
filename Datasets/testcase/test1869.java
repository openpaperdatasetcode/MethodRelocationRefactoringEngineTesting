package test;
import java.io.IOException;
class SourceClass {// First member inner classpublic class FirstInner {}
// Second member inner classpublic class SecondInner {strictfp Object instanceMethod(TargetClass.InnerRec targetRec) throws IOException {// Super constructor invocation in anonymous subclassTargetClass subTarget = new TargetClass() {{super(targetRec.value());}};
// Variable callObject result = targetRec.id() + "_" + targetRec.value();
// Overload existsresult += "" + subTarget.process(targetRec);
result += "" + subTarget.process(targetRec, 2);
// Requires throws (checked exception)if (targetRec.id() < 0) {throw new IOException("Negative ID");}
return result;}}}
public class TargetClass {private String base;
public TargetClass(String base) {this.base = base;}
// Static nested classpublic static class TargetStaticNested {public static String format(InnerRec rec) {return rec.id() + ":" + rec.value();}}
public record InnerRec(int id, String value) {}
// Overloaded methodspublic String process(InnerRec rec) {return TargetStaticNested.format(rec);}
public String process(InnerRec rec, int multiplier) {return TargetStaticNested.format(rec).repeat(multiplier);}}