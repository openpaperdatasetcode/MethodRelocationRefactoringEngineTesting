package source;
import target.TargetClass;import other.DiffPackageClass;
public class SourceClass {static class SourceStaticNested {public static int staticMethod(TargetClass target) {return target.targetField;}}
public void createLocalInner() {class LocalInner {}new LocalInner();}
/**
Method Javadoc
Varargs method returning TargetClass type, with required features
@param target TargetClass instance
@param args Variable length string arguments
@return TargetClass instance*/TargetClass methodToMove(TargetClass target, String... args) {// Super constructor invocation (implicit for Object, explicit in logic)super();
// Variable callint var = target.targetField;TargetClass.TargetInner inner = target.new TargetInner();
// Switch statementswitch (args.length) {case 0:var = 0;break;case 1:var = Integer.parseInt(args[0]);break;default:var = args.length;}
// Call different package class (DoStatement with ClassName.field = 2)new DiffPackageClass().process(target);
// Lambda expressions with instance method callRunnable r = () -> {int result = SourceClass.SourceStaticNested.staticMethod(target); // 1 as required};r.run();
return target;}
// Overload existsTargetClass methodToMove(TargetClass target, Integer... args) {target.targetField = args.length;return target;}}
package target;
protected class TargetClass {int targetField;
class TargetInner {} // target_inner
public void example() {class LocalInner {} // target_feature: local inner class}}
package other;
import target.TargetClass;
public class DiffPackageClass {public void process(TargetClass target) {// DoStatement with ClassName.field = 2do {ClassName.field = 2;} while (ClassName.field != 2);}
static class ClassName {static int field;}}