package test;
import java.lang.reflect.Field;import java.lang.reflect.Method;
private class SourceClass {static class StaticNested1 {}static class StaticNested2 {}
@MyAnnotationfinal TargetClass method(Object... args) {TargetClass target = new TargetClass();
// Labeled statementprocessLabel: {if (args.length == 0) {break processLabel;}target.field = args[0];}
// CastExpression with number 1 and default modifiertarget.count = (int) 1;
// Variable call to target's fieldtarget.field = "processed";
// Reflection usagetry {Field field = TargetClass.class.getField("field");field.set(target, "reflection");
Method method = TargetClass.class.getMethod("increment");method.invoke(target);} catch (Exception e) {throw new RuntimeException(e);}
return target;}}
@interface MyAnnotation {}
public class TargetClass extends ParentClass {public Object field;public int count;
void increment() {// Local inner classclass Counter {void update() {count++;}}new Counter().update();}}
class ParentClass {}