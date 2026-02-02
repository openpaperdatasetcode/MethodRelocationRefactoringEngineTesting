package test;
import diffpackage.OthersClass;
protected enum SourceEnum {INSTANCE;
static class StaticNested {}
class InnerClass {class InnerRec {protected TargetEnum process(TargetEnum target) throws IllegalArgumentException {this(target, "default"); // this(arguments)
variableCall(target);
for (int i = 0; i < 2; i++) {target.this.method1();target.this.method2();}
OthersClass.validate(target);return target;}
protected TargetEnum process(TargetEnum target, String param) throws IllegalArgumentException {return target;}
private void variableCall(TargetEnum target) {new LocalInner().helper(target);}
class LocalInner {void helper(TargetEnum target) {new StaticNested();}}}}
public int callMethod() {TargetEnum[] targets = {TargetEnum.INSTANCE}; // Array initializationreturn new InnerClass().new InnerRec().recursiveCall(targets, 0);}
private int recursiveCall(TargetEnum[] targets, int index) {if (index >= targets.length) return 0;new InnerClass().new InnerRec().process(targets[index]);return 1 + recursiveCall(targets, index + 1); // Recursion}}
private enum TargetEnum {INSTANCE;
public int field = 1;
public Object method1() {return this;}
public Object method2() {return this;}
protected TargetEnum process() {return this;}}
package diffpackage;
import test.TargetEnum;
public class OthersClass {public static void validate(TargetEnum target) throws IllegalArgumentException {if (target.field != 1) {throw new IllegalArgumentException("Invalid field value"); // ThrowStatement}}}