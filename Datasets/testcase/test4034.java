package same.pkg;
public non-sealed record SourceClass(String name) permits SubSourceRecord {static class FirstStaticNested {protected void processTargets(TargetClass... targetParams) throws IllegalArgumentException {if (targetParams.length == 0) {throw new IllegalArgumentException("No TargetClass parameters provided");}
SecondStaticNested helper = new SecondStaticNested();for (TargetClass target : targetParams) {if (target == null) {continue;}
TargetClass.TargetInner inner = target.new TargetInner();switch (inner.getType()) {case 1:helper.handleTypeOne(inner);break;case 2:helper.handleTypeTwo(inner);break;default:return;}}}}
static class SecondStaticNested extends ParentHelper {@Overridepublic synchronized TargetClass handleTypeOne(TargetClass.TargetInner inner) {TargetClass newTarget = new TargetClass(inner.getValue());this.processTargets(newTarget);return newTarget;}
public synchronized TargetClass handleTypeTwo(TargetClass.TargetInner inner) {TargetClass newTarget = new TargetClass(inner.getValue() * 2);this.processTargets(newTarget);return newTarget;}}}
record SubSourceRecord(String name) extends SourceClass {public SubSourceRecord(String name) {super(name);}}
class ParentHelper {public synchronized TargetClass handleTypeOne(TargetClass.TargetInner inner) {return new TargetClass(0);}
protected void processTargets(TargetClass... targets) {}}
private record TargetClass(int value) {class TargetInner {int getType() {return value % 2 + 1;}
int getValue() {return TargetClass.this.value;}}}