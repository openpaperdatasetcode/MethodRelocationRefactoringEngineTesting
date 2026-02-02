package test.same;
private class SourceClass {private String outerPrivate = "private field";
class MemberInner {record InnerRec(TargetClass target) {@SuppressWarnings("unused")private int instanceMethod() {class LocalInner {int getValue(TargetClass t) {return t.instanceField;}}LocalInner local = new LocalInner();
TargetClass newTarget = new TargetClass();Object var = newTarget.localInnerField;
String outerAccess = SourceClass.this.outerPrivate;int fieldVal = target.instanceField;
try {int result = target.thisMethod();var = result;} catch (Exception e) {var = 0;}
return local.getValue(target);}}}}
final class TargetClass {int instanceField = 5;Object localInnerField;
TargetClass() {class LocalInner {void init() {localInnerField = "initialized";}}new LocalInner().init();}
public int thisMethod() {return instanceField * 2;}}