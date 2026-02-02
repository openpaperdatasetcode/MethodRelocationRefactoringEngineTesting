package test.same;
import java.util.List;
class SourceClass {class FirstMemberInner {}class SecondMemberInner {}
class MemberInner {record InnerRec(TargetClass target) {@Deprecatedprivate void instanceMethod() {TargetClass.StaticNested inner = new TargetClass.StaticNested();Object var = inner.targetField;
List<String> list = SourceClass.<String>genericMethod(3);
switch (3) {case 3:inner.targetField = "updated";break;}
try {var = (String) inner.targetField;} catch (ClassCastException e) {}
// Override violation: attempting to override final methodinner.toString() {return "Override";}}}}
protected static <T> Object genericMethod(int num) {return List.of(num);}}
/**
Javadoc for TargetClass*/class TargetClass {static class StaticNested {Object targetField;
final String toString() {return "Original";}}}