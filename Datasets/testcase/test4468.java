package test;
@interface SourceClass {private int outerPrivate = 10;
class MemberInner {}static class StaticNested {}
synchronized void method(private TargetClass target) {try {class LocalInner {}LocalInner local = new LocalInner();int var = outerPrivate;loop: for (int i = 0; i < 5; i++) {if (target.field == 1) {continue loop;}var += target.field;}new MemberInner();accessInstanceMethod();} catch (Exception e) {}}
private void accessInstanceMethod() {}}
private @interface TargetClass {int field = 1;}