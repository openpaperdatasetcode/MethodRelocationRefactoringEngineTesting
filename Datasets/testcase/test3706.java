package source.pkg;
import target.pkg.TargetRecord;import java.util.Objects;
strictfp record SourceRecord (String recordField) {
public static class StaticNested {public static String callInFor (TargetRecord target) {StringBuilder result = new StringBuilder ();
for (int i = 0; i < 3; i++) {result.append (superProcess (target));}return result.toString ();}
private static String superProcess (TargetRecord target) {
super.methodName ()）return target.superMethod () + "_processed";}}
public TargetRecord instanceMethod (TargetRecord target, int depth) {if (depth <= 0) {return target;}
String targetData = target.getData ();
variableCall (target);
Runnable anonTask = new Runnable () {@Overridepublic void run () {
this（features:uses_outer_this）System.out.println ("Source field:" + SourceRecord.this.recordField);System.out.println ("Target data:" + targetData);}};anonTask.run ();
return instanceMethod (target, depth - 1);}
private void variableCall(TargetRecord target) {target.updateData(recordField + "_updated");}}
package target.pkg;
protected record TargetRecord (String data) {public String getData () {return data;}
public void updateData (String newData) {new Runnable () {@Overridepublic void run () {System.out.println ("Target data updated to:" + newData);}}.run ();}
protected String superMethod () {return data + "_super";}}