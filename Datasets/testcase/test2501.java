package same;
class SourceClass extends Parent {private Object field = "private";
class InnerRec {@Overrideprivate Object process(TargetClass.Inner targetInner) {TargetClass target = new TargetClass();transient int sum = targetInner.field1 + targetInner.field2 + targetInner.field3;
sum++;targetInner.update(sum);
try {Class<?> cls = Class.forName("same.TargetClass$Inner");Object obj = cls.getDeclaredConstructor(TargetClass.class).newInstance(target);} catch (Exception e) {e.printStackTrace();}
Runnable lambda = () -> {Object result = targetInner.finalMethod(super.getValue());};lambda.run();
return field;}}}
class Parent {protected Object getValue() {return null;}}
package same;
public class TargetClass {class Inner {int field1 = 1;int field2 = 2;int field3 = 3;
void update(int val) {}
final Object finalMethod(Object arg) {return arg;}
void createLocal() {class LocalInner {void useFields() {System.out.println(field1);}}}}}