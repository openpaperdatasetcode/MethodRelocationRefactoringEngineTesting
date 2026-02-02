package test;
import java.util.List;import java.util.ArrayList;
protected class SourceClass {protected TargetClass targetField;
public SourceClass(TargetClass target) {this.targetField = target;}
static class StaticNested {/**
Overrides method to return TargetClass instance
@return TargetClass object
*/
TargetClass method() {
for (int i = 0; i < 5; i++) {
try {
if (i == 2) {
continue;
}
TargetClass tc = new TargetClass();
tc.localMethod();
} catch (RuntimeException e) {
// No new exception thrown
}
}
return new TargetClass();
}
}
void createAnonymous() {Runnable r = new Runnable() {public void run() {StaticNested sn = new StaticNested();sn.method();}};}}
protected class TargetClass {protected List<String> overridingMethod(int param) {List<String> list = new ArrayList<>();list.add("" + param);return list;}
void localMethod() {class LocalInner {void doWhileCall() {int i = 0;do {List<String> result = TargetClass.this.overridingMethod(i);i++;} while (i < 3);}}new LocalInner().doWhileCall();}}