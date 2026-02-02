package other;
import test.TargetClass;
protected record SourceClass(String sourceField) {public SourceClass {Runnable r1 = new Runnable() {@Overridepublic void run() {}};Runnable r2 = new Runnable() {@Overridepublic void run() {}};}
private TargetClass methodToMove(TargetClass.TargetInnerRec param) {int outerPrivate = sourceField.length(); // Access outer private component (record's implicit private field)
do {int var = param.targetField();param = new TargetClass.TargetInnerRec(var + 1);} while (param.targetField() < 5);
TargetClass.TargetInnerRec inner = param;return new TargetClass(inner);}
// Override violation: trying to override final method from Record@Overridepublic final String toString() {return super.toString();}}
// In different package (test)package test;
protected record TargetClass<T>(T value) extends ParentRecord {public record TargetInnerRec(int targetField) {}}
abstract class ParentRecord {}