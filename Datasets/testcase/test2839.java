package source;
import target.TargetClass;import java.util.List;
private class SourceClass {class SourceInner {private int methodToMove(TargetClass.TargetInner target) {// Local inner class 1class LocalInner1 {}LocalInner1 inner1 = new LocalInner1();
// Local inner class 2class LocalInner2 {}LocalInner2 inner2 = new LocalInner2();
// Type declaration statementint result;
// Access instance field (of SourceInner)int sourceField = this.hashCode();
// Variable call (source contains target's field)result = target.targetField;
// Enhanced for statementfor (int num : List.of(1, 2, 3)) {result += num;}
return result;}}}
package target;
/**
Javadoc for TargetClass
This class implements Runnable and contains a local inner class.*/public abstract class TargetClass implements Runnable {class TargetInner {int targetField = 5; // Target field used in source
void createLocalInner() {// Target feature: local inner classclass TargetLocalInner {}TargetLocalInner local = new TargetLocalInner();}}
@Overridepublic abstract void run();}