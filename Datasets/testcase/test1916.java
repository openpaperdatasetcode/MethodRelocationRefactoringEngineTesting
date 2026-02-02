package test;
import java.util.ArrayList;import java.util.List;
public enum SourceClass {INSTANCE;
static class StaticNested {private int callMethod(TargetClass target) {return target.this.method();}}
class MemberInner {class InnerRec {strictfp List<String> method(TargetClass target) throws Exception {target.field.add("test");int i = 0;do {target.addElement(target.field, "elem" + i);i++;} while (i < 3);
return target.field;}
strictfp List<String> method(TargetClass target, String param) throws Exception {this.method(target);target.field.add(param);return target.field;}}}}
enum TargetClass {VALUE(new StaticNested().callMethod(VALUE));
List<String> field = new ArrayList<>();
TargetClass(int value) {}
void addElement(List<String> list, String elem) {list.add(elem);}
int method() {return field.size();}
{Runnable r = new Runnable() {public void run() {field.clear();}};}
static class StaticNested {private int callMethod(TargetClass target) {return target.method();}}}