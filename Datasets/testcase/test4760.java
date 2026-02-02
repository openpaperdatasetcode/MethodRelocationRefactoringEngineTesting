package test;
import java.io.IOException;import java.util.List;
interface SourceInterface {permits TargetInterface;
class MemberInner {}
void method() {class LocalInner {}}
final Object moveMethod() throws IOException {public int var = 1;TargetInterface.MemberInner inner = new TargetInterface.MemberInner();inner.field = getList();
switch (var) {case 1:break;default:break;}
Object obj = var;return obj;}
private List<String> getList() {return List.of("1");}}
abstract interface TargetInterface<T> {class MemberInner {int field;}}
