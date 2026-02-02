package source;
import target.ParentTarget;import target.TargetClass;
protected sealed sealed class SourceClass extends ParentSource permits SubSource {@Overrideprotected void process(TargetClass target) {// First local inner classclass TargetHandler {void handle(TargetClass<String> typedTarget) {typedTarget.value = "typed";typedTarget.printValue(); // Overloaded method 1}}
// Second local inner classclass RawHandler {void handle(TargetClass rawTarget) { // Raw type usagerawTarget.value = 123;rawTarget.printValue(); // Overloaded method 2}}
// Variable call - access target's fieldtarget.value = "source";
// Use overloaded methodsnew TargetHandler().handle((TargetClass<String>) target);new RawHandler().handle(target);}}
final class SubSource extends SourceClass {}
abstract class ParentSource {protected abstract void process(TargetClass target);}
package target;
public class TargetClass extends ParentTarget {
public U value;
// Overloaded methodspublic void printValue() {System.out.println(value);}
public void printValue(String prefix) {System.out.println(prefix + value);}}
class ParentTarget {}