package test.same;
protected class SourceClass {static class StaticNested {}
private TargetClass varargsMethod(TargetClass... targets) {class LocalInner {TargetClass createTarget() {return new TargetClass();}}LocalInner local = new LocalInner();
TargetClass result = local.createTarget();int count = 0;
for (TargetClass target : targets) {Object var = target.field;target.field = count++;var = count++; // PostfixExpression with 2 increments}
OtherClass other = new OtherClass();other.process(this);
return result;}}
strictfp class TargetClass {Object field;
{Runnable anon = new Runnable() {public void run() {Object var = field;}};}}
class OtherClass {void process(SourceClass source) {}}