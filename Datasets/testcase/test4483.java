package test;
import java.util.ArrayList;import java.util.List;
class SourceClass {class SourceInner {TargetClass targetField = new TargetClass<>();static int staticField = 1;
protected TargetClass sourceMethod() {abstract class AbstractLocal {abstract int[] getArray();}AbstractLocal local = new AbstractLocal() {int[] getArray() {return new int[staticField];}};int val = local.getArray()[0];
List rawList = new ArrayList();rawList.add(targetField);
try {synchronized (this) {val = targetField.inner.field;val = ((ParentClass) targetField).parentField;}} catch (Exception e) {}
return targetField;}}
void createAnonymous() {Runnable r = new Runnable() {public void run() {new SourceInner().sourceMethod();}};}}
/**
Javadoc for TargetClass
*/
class TargetClass<T> extends ParentClass {
class TargetInner {
int field = 1;
}
TargetInner inner = new TargetInner();
}
class ParentClass {int parentField = 1;}