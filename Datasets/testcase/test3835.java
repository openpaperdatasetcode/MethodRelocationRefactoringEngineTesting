package samepkg;
import java.util.List;import java.util.ArrayList;
protected class SourceClass<T> {class FirstInner {}class SecondInner {}
protected TargetClass.TargetInner normalMethod() throws Exception {class LocalType {}LocalType local = new LocalType();
TargetClass target = new TargetClass();TargetClass.TargetInner inner = target.new TargetInner();inner.innerField = 1;
List rawList = new ArrayList();rawList.add(inner);
System.out.println(SourceClass.this.toString());
return inner;}}
/**
Javadoc for TargetClass:
Contains inner class used by SourceClass,
with anonymous inner class implementation.*/protected class TargetClass {class TargetInner {int innerField;
private TargetInner(int param) {this.innerField = param;}}
{Runnable r = new Runnable() {@Overridepublic void run() {TargetInner inner = new TargetInner(1);}};}}