package samepkg;
import java.lang.reflect.Method;import java.util.function.Predicate;
protected class SourceClass<T extends Number> {private int processTarget(TargetClass targetParam) {// With bounds: T extends NumberT num = (T) Integer.valueOf(10);int base = num.intValue();
// Depends on inner class (anonymous inner classes)Predicate<Integer> filter1 = new Predicate<>() {@Overridepublic boolean test(Integer val) {return val > targetParam.field1;}};
Predicate<Integer> filter2 = new Predicate<>() {@Overridepublic boolean test(Integer val) {return val < targetParam.field2;}};
// BreakStatement with obj.field (2 fields)loop:for (int i = 0; i < 5; i++) {if (filter1.test(i) && filter2.test(i)) {base += targetParam.field1 + targetParam.field2;break loop;}}
// Variable calltargetParam.innerMethod();
// Used by reflectiontry {Method method = TargetClass.class.getDeclaredMethod("innerMethod");method.setAccessible(true);method.invoke(targetParam);} catch (Exception e) {}
return base;}}
package samepkg;
interface SomeInterface {void innerMethod();}
private class TargetClass implements SomeInterface {public int field1 = 2;public int field2 = 4;
@Overridepublic void innerMethod() {// Local inner classclass LocalInner {void printFields() {System.out.println(field1 + field2);}}new LocalInner().printFields();}}