protected class SourceClass {static class StaticNested {}
class MemberInner {class InnerRec {private Object methodToMove () {return processTarget (new TargetClass ());}
private Object methodToMove (TargetClass target) {return processTarget (target);}
private Object processTarget(TargetClass target) {class LocalInner {private void invokeConstructor() {TargetClass.MemberInner targetInner = target.new MemberInner();target.field = 1;assert target.field == 1;}}
new LocalInner().invokeConstructor();
for (int i = 0; i < 2; i++) {System.out.println("Processing target field: " + target.field);target.new MemberInner().doAction();}
return target.field;}}}}
strictfp class TargetClass {int field;
class MemberInner {void doAction() {}}}