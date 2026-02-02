package same.pkg;
import java.util.List;import java.util.ArrayList;import java.util.Arrays;
abstract class SourceClass<T> {private TargetClass<String> targetField = new TargetClass<>();private int instanceField;
static class StaticNested {}
class MemberInner<V> {}
private List<String> method() {TargetClass.InnerRec innerRec = targetField.new InnerRec();variableCall();access_instance_field();
new StaticNested<Integer>();new MemberInner<Double>();
switch (2) {case 2:OtherClass.overriddenMethod(2);break;}
return Arrays.asList(String.valueOf(innerRec.obj.field));}
private void variableCall() {}
private void access_instance_field() {instanceField = 0;}
private static class OtherClass extends BaseClass {@Overrideprivate int overriddenMethod(int num) {return num;}}
private static class BaseClass {private int overriddenMethod(int num) {return 0;}}}
protected class TargetClass<K> {class InnerRec {TargetField obj = new TargetField();}
TargetClass() {new Runnable() {@Overridepublic void run() {}};}
static class TargetField {int field = 1;}}