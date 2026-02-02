package test;
import java.util.ArrayList;import java.util.List;
class ParentClass {void handle(int num) {}void handle(String str) {}}
protected class SourceClass extends ParentClass {protected List<String> process(TargetClass target) {class LocalInner {}new Runnable() {@Overridepublic void run() {}};
List<String> result = new ArrayList<>();TargetClass.Inner inner = target.new Inner();
for (int i = 0; i < 2; i++) {handle(i, inner);handle("str", inner);}
return result;}
void handle(int num, TargetClass.Inner inner) {super.handle(num);inner.setValue(num);}
void handle(String str, TargetClass.Inner inner) {super.handle(str);inner.setValue(str.length());}
SourceClass getThis() {return this;}}
private class TargetClass {class Inner {private int value;
Inner() {super();}
void setValue(int val) {this.value = val;class LocalInner {}}}}