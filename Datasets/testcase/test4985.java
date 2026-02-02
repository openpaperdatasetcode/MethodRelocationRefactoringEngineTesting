package refactoring.test;

public class SourceClass {
static class StaticNested {
String nestedField;
}

public int instanceMethod(TargetClass targetParam) {
StaticNested staticObj = new StaticNested();
variable call = targetParam.targetField;

class LocalInner {
int getValue() {
return targetParam.count;
}
}

LocalInner local = new LocalInner();

try {
Class<?> clazz = Class.forName("refactoring.test.TargetClass$MemberInner");
Object innerObj = clazz.getConstructor(TargetClass.class).newInstance(targetParam);
} catch (Exception e) {}

OtherClass other = new OtherClass();
targetParam.property = other.privateMethod(targetParam, staticObj);

return local.getValue();
}
}

public class TargetClass {
String targetField;
int count = 5;
String property;

class MemberInner {
String getInfo() {
return targetField;
}
}
}

class OtherClass {
private String privateMethod(TargetClass target, SourceClass.StaticNested nested) {
return target.new MemberInner().getInfo() + nested.nestedField;
}
}