package test.same;
interface SomeInterface {}
public class SourceClass implements SomeInterface {static class StaticNested {}class MemberInner {}
private void instanceMethod(TargetClass target) {int count = 0;do {TargetClass.LocalInner local = target.createLocalInner();private Object var1 = this;private Object var2 = this;
var1 = local.field;var2 = target.field;
try {local.process();} catch (Exception e) {// Required try-catch block}
count++;} while (count < 5);}}
class TargetClass {Object field;
LocalInner createLocalInner() {class LocalInner {Object field;
void process() throws Exception {}}return new LocalInner();}}