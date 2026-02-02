package test;
class SourceClass extends ParentClass {static int staticCounter = 0;
class MemberInner {}
@Overrideprivate Object moveMethod(ProtectedTarget target) {class LocalInner {}LocalInner local = new LocalInner();
static int field1 = target.fieldA;static int field2 = target.fieldB;
while (staticCounter < 5) {target.process(field1 + staticCounter);target.update(field2 * staticCounter);staticCounter++;}
return local;}}
abstract class ParentClass {protected abstract Object moveMethod(ProtectedTarget target);}
protected class ProtectedTarget {public int fieldA = 10;public int fieldB = 20;
public void process(int val) {}public void update(int val) {}}