package test;
import java.io.IOException;
public class SourceClass extends ParentClass {public void createLocal1() {class LocalInner1 {}}
public void createLocal2() {class LocalInner2 {protected TargetClass<String> methodToMove(TargetClass.MemberInner param) throws IOException {TargetClass<String> target = new TargetClass<>();target.field = 3;for (int i = 0; i < 5; i++) {if (param.value == i) {break;}}param.value = 10;return target;}}}}
abstract class TargetClass<T> {int field;
class MemberInner {int value;}}
class ParentClass {}
