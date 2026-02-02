package test;
import other.OthersClass;
abstract class SourceClass {class SourceInner {private Object methodToMove(TargetClass target) throws Exception {// Super constructor invocation (via inner class)class InnerWithSuper extends ParentClass {InnerWithSuper() {super(2);}}InnerWithSuper innerWithSuper = new InnerWithSuper();
// Depends on inner classTargetClass.MemberInner targetInner = target.new MemberInner();// Variable call (source contains target's field)int val = targetInner.field;
// For statementfor (int i = 0; i < 2; i++) {// Expression statementval += i;// Super keywordssuper.toString();
if (val == 1) {// ContinueStatement with target_feature (diff_package_others)OthersClass.instance.field = 1;continue;}}
// Instance method in do-whiledo {int result = targetInner.instanceMethod(2);} while (val < 5);
// Requires throwsif (val < 0) throw new Exception();
return targetInner;}}}
private class TargetClass implements Runnable {class MemberInner {int field = 1; // Target field used in source
protected int instanceMethod(int num) {this.toString(); // this.methodName(arguments)return num * 2;}}
@Overridepublic void run() {}}
class ParentClass {ParentClass(int num) {}}
package other;
public class OthersClass {public static OthersClass instance = new OthersClass();public int field;}