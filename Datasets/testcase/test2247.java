package test;
import java.util.List;import java.util.ArrayList;import java.util.stream.Collectors;
non-sealed class SourceClass<T> {class MemberInner {record SourceInnerRec() {protected List<String> methodToMove(TargetClass<String> target) {TargetClass<String>.InnerClass inner = target.new InnerClass();int field = inner.instanceField;field += TargetClass.staticField;
if (TargetClass.staticField == 3) {; // Empty statement}
List<String> list = new ArrayList<>();list.forEach(InnerClass::setValue);
return list.stream().map(s -> s + inner.getField()).collect(Collectors.toList());}}}
{new Runnable() {public void run() {}};}}
class TargetClass<K> {static int staticField = 3;K instanceField;
class InnerClass {int getField() {return 3;}
void setValue(String s) {// Implementation}}}