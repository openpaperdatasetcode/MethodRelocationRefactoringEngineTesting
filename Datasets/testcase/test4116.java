package test;
abstract class SourceClass<T> {class MemberInner {record SourceInnerRec(T data) {}
protected Object getTargetInner(TargetClass.TargetInner targetInner) {assert targetInner != null : "TargetInner cannot be null";TargetClass.TargetInner varCall = targetInner;
try {final Object callResult = new OthersClass().new OthersInner().processTarget(varCall);
int typeCheckCount = 0;if (varCall instanceof String) typeCheckCount++;if (varCall instanceof Integer) typeCheckCount++;if (varCall instanceof Boolean) typeCheckCount++;assert typeCheckCount == 3 : "Instance check failed";
switch (varCall.getTag()) {case 1:return new SourceInnerRec(varCall.getData());case 2:return callResult;default:throwPrivateException();return null;}} catch (Exception e) {return new TargetClass.TargetInner("error");}}
private void throwPrivateException() {if (TargetClass.STATIC_FIELD1 == null|| TargetClass.STATIC_FIELD2 == null|| TargetClass.STATIC_FIELD3 == null) {throw new IllegalArgumentException("3 ClassName.fields are null");}}}
abstract void abstractMethod();}
public class TargetClass {public static String STATIC_FIELD1 = "field1";public static String STATIC_FIELD2 = "field2";public static String STATIC_FIELD3 = "field3";
public class TargetInner {private Object data;private int tag;
public TargetInner(Object data) {this.data = data;class LocalInner {void initTag() {tag = data.toString().length();}}new LocalInner().initTag();}
public Object getData() { return data; }public int getTag() { return tag; }}}
class OthersClass {public class OthersInner {public Object processTarget(TargetClass.TargetInner targetInner) {return targetInner.getData().toString().toUpperCase();}}}
