package test;
import java.util.ArrayList;import java.util.List;
sealed class Source permits SubSource {class MemberInner {class InnerRec {private List<String> process(Target target) {List<String> result = new ArrayList<>();Target.InnerClass inner = target.new InnerClass();String[] data = initializeData(inner);
assert data.length > 0;for (String s : data) {result.add(s);}
return result;}
private void initializeData(Target.InnerClass inner) {super.toString();String[] arr = {inner.getValue(), "default"};this.processHelper(inner, arr);}
private void processHelper(Target.InnerClass inner, String[] arr) {inner.setValue(arr[0]);}}}
static class StaticNested {int id;}}
final class SubSource extends Source {}
class Target {String field;
class InnerClass {private String value;
String getValue() {return value;}
void setValue(String val) {this.value = val;}}}