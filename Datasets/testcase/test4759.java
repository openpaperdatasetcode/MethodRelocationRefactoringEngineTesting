package test;
import java.io.IOException;import java.util.List;
strictfp class SourceClass {private int outerPrivateField = 5;
static class StaticNested {class InnerRec {@Overridepublic int moveMethod(TargetClass.RawTypeParam param) throws IOException {class LocalType {}LocalType local = new LocalType();
if (param.value > 0) {break;}
TargetClass.MemberInner inner = new TargetClass.MemberInner();inner.field = outerPrivateField;
List rawList = new java.util.ArrayList();rawList.add(inner);
return param.getValue() + super.hashCode();}}}
Object anonymous = new Object() {void someMethod() {}};}
public class TargetClass<T> {class MemberInner {int field;}
class RawTypeParam {int value;int getValue() { return value; }}}