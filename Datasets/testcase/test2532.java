package source;
import target.TargetClass;import java.util.List;
protected class SourceClass {static class StaticNested {}
class Inner {private TargetClass process(String... args) {TargetClass target = new TargetClass();TargetClass.InnerRec inner = target.new InnerRec();
transient int total = inner.superField1 + inner.superField2 + inner.superField3;
int i = 0;do {inner.add(args[i]);i++;} while (i < args.length);
List<String> result;if (total > 0) {result = inner.getItems();} else {result = inner.getItems("default");}
return target;}}
void createLocal() {class LocalInner {void useInner(TargetClass.InnerRec inner) {}}}}
package target;
protected class TargetClass extends ParentClass {class InnerRec {protected List<String> getItems() {return List.of();}
protected List<String> getItems(String fallback) {return List.of(fallback);}
void add(String item) {}}}
package target;
class ParentClass {int superField1 = 1;int superField2 = 2;int superField3 = 3;}
