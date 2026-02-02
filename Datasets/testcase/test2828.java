package test;
import other.OthersClass;
public enum SourceEnum {INSTANCE;
class SourceInner {protected static int methodToMove(TargetEnum.TargetInnerRec target) {// Super constructor invocation (via inner class)class InnerWithSuper extends ParentClass {InnerWithSuper() {super(1);}}InnerWithSuper innerSuper = new InnerWithSuper();
// Anonymous inner classRunnable anon1 = new Runnable() {@Overridepublic void run() {}};
// Variable call (contains target parameter)int val = target.field;// Expression statementval += 1;
// Break statementloop: {if (val > 2) {break loop;}}
// ConstructorInvocation in diff_package_othersOthersClass others = new OthersClass();others.this.field = 1;
return val;}}}
protected enum TargetEnum {INSTANCE;
// Target feature: anonymous inner classRunnable anon2 = new Runnable() {@Overridepublic void run() {}};
class TargetInner {record TargetInnerRec(int field) {}}}
class ParentClass {ParentClass(int num) {}}
package other;
public class OthersClass {public int field;}
