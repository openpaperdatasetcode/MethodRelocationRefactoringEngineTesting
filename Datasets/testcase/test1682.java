package test;
import java.util.List;
public record SourceClass(String data) {class MemberInner1 {}class MemberInner2 {}
{OthersClass.method(this);}
@Overridepublic void data() {TargetClass param = new TargetClass(0);int a = 1, b = 2, c = 3;++a;++b;++c;
variableCall();SourceClass.this.data();
loop:for (int i = 0; i < 5; i++) {if (i == 3) {break loop;}}}
private void variableCall() {}}
protected record TargetClass<Integer extends Number>(Integer value) extends ParentRecord {class MemberInner {}}
record ParentRecord() {}
class OthersClass {private static void method(SourceClass source) {}}