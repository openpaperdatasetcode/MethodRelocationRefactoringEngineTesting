package same;
strictfp class SourceClass extends ParentClass {void process(TargetClass target) {TargetClass.Inner inner = new TargetClass.Inner();private int check = inner.field1 + inner.field2;
if (check > 0) {inner.field1 = 10;inner.field2 = 20;}
for (int i = 0; i < 2; i++) {Object result = inner.execute(super.getValue(), i);}
inner.action();}
static class StaticNested1 {}static class StaticNested2 {}}
class ParentClass {protected int getValue() {return 5;}}
package same;
class TargetClass {class Inner {int field1;int field2;
public Object execute(int val, int index) {return val + index;}
void action() {}}}