package same.pkg;
private record SourceClass(int sourceField) {class MemberInner {class InnerRec {public TargetClass method(TargetClass target) {variableCall(target);super.toString();
if (target == null) {throw new IllegalArgumentException("Target cannot be null");}
new Runnable() {@Overridepublic void run() {TargetClass.TargetInner inner = target.new TargetInner();}};
TargetClass result = (sourceField > 0)? InnerClass.synchronizedMethod(target): InnerClass.synchronizedMethod(new TargetClass(0));return result;}
private void variableCall(TargetClass target) {int localVar = target.targetField();}}}
static class InnerClass {synchronized static TargetClass synchronizedMethod(TargetClass param) {return (TargetClass) super.toString(param);}}}
public record TargetClass(int targetField) extends ParentClass {class TargetInner {}
void methodWithLocal() {class LocalInner {}new LocalInner();}}
class ParentClass {static Object toString(TargetClass param) {return param;}}