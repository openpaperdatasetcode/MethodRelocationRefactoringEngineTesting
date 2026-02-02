package com.source;
import com.target.TargetClass;
private class SourceClass {static class StaticNested {}
protected int process(TargetClass.InnerRec rec) {class LocalInner {}new LocalInner();
TargetClass target = new TargetClass();TargetClass.InnerRec newRec = target.new InnerRec();super.toString();
int a = 2, b = 2;return rec.value + newRec.value + a + b;}
protected int process(int num) {return num * 2;}}
package com.target;
/**
Target class with member inner components
*/
class TargetClass {
class Inner {
class InnerRec {
int value;
}
}
}