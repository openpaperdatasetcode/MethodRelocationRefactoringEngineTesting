package test;
import other.DiffPackageClass;
public abstract class TargetClass {int targetField;class TargetInner {}}
sealed abstract class SourceClass permits SourceSubClass {static class SourceStaticNested {static int staticField = 5;}
public SourceClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
public TargetClass methodToMove(TargetClass target, String... args) {// Variable callint var = target.targetField;
// Expression statementtarget.targetField = 10;
// Depends on static fieldvar += SourceStaticNested.staticField;
// Throw statementif (var < 0) {throw new IllegalArgumentException();}
// Call different package classnew DiffPackageClass().process(target);
// For loop with overriding methodfor (int i = 0; i < 2; i++) {Object result = new OtherClass().overrideMethod();}
return target;}}
final class SourceSubClass extends SourceClass {}
class OtherClass extends ParentClass {@Overrideprotected Object overrideMethod() {return super.overrideMethod() + 2;}}
abstract class ParentClass {protected Object overrideMethod() {return new Object();}}
package other;
import test.TargetClass;
public class DiffPackageClass {private void process(TargetClass target) {if (target.targetField == 1) {throw new RuntimeException();}}}