package test;
import java.util.ArrayList;import java.util.List;
private class SourceClass {static class SourceStaticNested {void nestedMethod(TargetClass target) {target.StaticNested.process(target.field);}}
{new SourceLocalInner(this).init();new SourceLocalInner(this, "init").init();}
@MyAnnotationpublic List<String> method(TargetClass target) {List<String> result = new ArrayList<>(target.field);
for (String s : target.field) {result.add(s.toUpperCase());}
int i = 0;while (i < target.field.size()) {target.StaticNested.add(result, target.field.get(i));i++;}
return result;}
class SourceLocalInner {SourceClass outer;String data;
SourceLocalInner(SourceClass outer) {super();this.outer = outer;}
SourceLocalInner(SourceClass outer, String data) {super();this.outer = outer;this.data = data;}
void init() {outer.method(new TargetClass());}}}
public class TargetClass {List<String> field = new ArrayList<>();
static class StaticNested {static void process(List<String> list) {list.removeIf(String::isEmpty);}
static void add(List<String> dest, String s) {dest.add(s.toLowerCase());}}}
@interface MyAnnotation {}