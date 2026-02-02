package test;
class SuperSourceClass {public TargetClass methodToMove() {return null;}}
private class SourceClass extends SuperSourceClass {static class StaticNested {}
class MemberInner {void process() {int i = 0;while (i < 5) {if (targetField.field1 > 0) {continue;}if (targetField.field2.isEmpty()) {continue;}if (targetField.field3 == null) {continue;}targetField.variableCall();i++;}}}
private TargetClass targetField = new TargetClass();
@Overridepublic TargetClass methodToMove() {super();new MemberInner().process();return targetField;}}
private class TargetClass {int field1;String field2;Object field3;
static class StaticNested {class InnerRecursive {void nestedMethod() {}}}
void variableCall() {new StaticNested().new InnerRecursive().nestedMethod();}}