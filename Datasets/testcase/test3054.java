package test;
/**
Javadoc for TargetClass
*/
record TargetClass(int targetField) {
class MemberInner {}
}
private record SourceClass(String sourceField) {static class StaticNested1 {}static class StaticNested2 {}
private TargetClass methodToMove(TargetClass param) {class LocalType {}LocalType local = new LocalType();
if (param.targetField() < 0) {throw new IllegalArgumentException();}
TargetClass.MemberInner inner = param.new MemberInner();return param;}}