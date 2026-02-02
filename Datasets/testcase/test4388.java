package test;
import java.util.List;
private class SourceClass extends ParentSourceClass implements Runnable {private int sourceField;
int moveMethod(TargetClass target, int... varargs) {super();if (target == null) {throw new NullPointerException("Target cannot be null");}
TargetClass.TargetInner inner = target.new TargetInner();int targetFieldVal = target.targetField;
List rawList = new ArrayList();for (int i = 0; i < 3; i++) {this.sourceField = i;rawList.add(targetFieldVal + varargs[i]);inner.recursiveMethod(i);}
return this.sourceField + targetFieldVal;}
void methodWithFirstLocal() {class SourceLocalInnerOne {}}
void methodWithSecondLocal() {class SourceLocalInnerTwo {}}
@Overridepublic void run() {}}
class ParentSourceClass {protected ParentSourceClass() {}}
private class TargetClass extends ParentTargetClass {int targetField;
class TargetInner {void recursiveMethod(int count) {if (count <= 0) return;recursiveMethod(count - 1);}}}
class ParentTargetClass {protected ParentTargetClass() {}}