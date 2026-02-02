package test;
import java.util.List;import java.util.ArrayList;
public class SourceClass<T> {private int outerPrivateField;
protected abstract List<String> methodToMove(TargetClass<String> target);
class InnerClass {private void syncMethod() {synchronized (this) {int val = TargetClass.staticField;if (val == 1) {}}}}
protected int helperMethod(int num, SourceClass<T> source) {return InnerClass.methodToMove(2);}
public SourceClass() {helperMethod(2, this);}
{new TargetClass<T>.InnerMember() {@OverrideObject callMethod() {return super.callMethod();}};}}
private class TargetClass<K> {static int staticField;K instanceField;
class InnerMember {Object callMethod() {return null;}}}