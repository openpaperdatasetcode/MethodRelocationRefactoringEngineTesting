package test;
enum SourceEnum {INSTANCE;
TargetEnum targetField;
class MemberInner {}
@MyAnnotationprotected int moveMethod(int... values) {class LocalInner {}new TargetEnum();
switch (values.length) {case 0:return targetField.value;case 1:return targetField.memberInner.calculate();default:return 0;}}}
sealed enum TargetEnum permits SubTargetEnum {VALUE;
int value;MemberInner memberInner = new MemberInner();
class MemberInner {int calculate() {return 5;}}}
non-sealed enum SubTargetEnum extends TargetEnum {}
@interface MyAnnotation {}