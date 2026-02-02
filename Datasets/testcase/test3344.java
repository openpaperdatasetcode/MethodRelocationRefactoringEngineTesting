package source;
import target.TargetClass;
protected class SourceClass {private int outerPrivateField = 5;
static class StaticNested {}
class InnerClass {class InnerRec {void process(TargetClass... targets) {super(); // Super constructor invocation
for (TargetClass target : targets) {if (target == null) {throw new NullPointerException();}
access_instance_field(target);variableCall(target);System.out.println(SourceClass.this.outerPrivateField); // Access outer private}
class LocalInner {}new LocalInner();}
private void variableCall(TargetClass target) {target.staticNested.doTask();new StaticNested();}
private void access_instance_field(TargetClass target) {System.out.println(target.publicField);}}}}
package target;
protected class TargetClass {public int publicField = 10;
public static class StaticNested {public void doTask() {}}
public StaticNested staticNested = new StaticNested();
void process(TargetClass... targets) {}}