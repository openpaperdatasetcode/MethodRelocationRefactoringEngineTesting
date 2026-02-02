package test;
import java.lang.reflect.Method;
private class SourceClass {class MemberInner {}static class StaticNested {}
record SourceInnerRec() {protected Object methodToMove() {TargetClass target = new TargetClass();Object fieldVal = target.targetField;
try {Method method = SourceInnerRec.class.getMethod("methodToMove");method.invoke(this);} catch (Exception e) {}
return fieldVal;}}}
class TargetClass {Object targetField;class TargetInner {}}