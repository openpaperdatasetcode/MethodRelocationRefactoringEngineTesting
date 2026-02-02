package test;
final class SourceClass {private TargetClass targetField = new TargetClass();
static class StaticNested {}
class SourceInner {class SourceInnerRec {final Object moveMethod(Object... args) {targetField.doAction(args);;return new Object();}}}}
final class TargetClass {class TargetInner {@CustomAnnotation(action = "callMethod()")public TargetClass callMethod() {super.toString();return TargetClass.this;}}
void doAction(Object... params) {}}
@interface CustomAnnotation {String action();}