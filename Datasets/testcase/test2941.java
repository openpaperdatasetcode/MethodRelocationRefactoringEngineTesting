import java.util.Arrays;
non-sealed enum SourceEnum {INSTANCE;
static class StaticNested {}class MemberInner {}
private Object methodToMove(TargetEnum... targets) throws Exception {SourceEnum.this.toString();TargetEnum.FieldType field = targets[0].field;StaticNested sn = new StaticNested();MemberInner mi = new MemberInner();int num = 42;System.out.println(num);return new TargetEnum.LocalInner(field);}}
enum TargetEnum {VALUE;
FieldType field = new FieldType();
class FieldType {}
void someMethod() {class LocalInner {LocalInner(FieldType ft) {super();}}}}
abstract class ParentClass {final int callMethod() throws Exception {throw new Exception(SourceEnum.INSTANCE.methodToMove(TargetEnum.VALUE));}}
class SubClass extends ParentClass {@Overridefinal int callMethod() throws Exception {return new ParentClass().callMethod();}}