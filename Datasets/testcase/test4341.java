package same;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.function.Function;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
private class Source<T extends Number> {private Target sourceTargetField = new Target();
class SourceMemberInner {
public U innerProcess(U param) {
return param;
}
}
@MethodAnnoint instanceMethod(U param, String strParam) {
if (param == null || strParam == null) {
throw new NullPointerException("Param cannot be null");
}
private int objField1 = sourceTargetField.targetIntField;private String objField2 = sourceTargetField.targetStrField;private Target.TargetStaticNested objField3 = new Target.TargetStaticNested();
SourceMemberInner memberInner = new SourceMemberInner<>();
U processedParam = memberInner.innerProcess(param);
int doCount = 0;do {objField3.staticNestedMethod("do_loop_" + doCount);doCount++;} while (doCount < 2);
int forTotal = 0;for (int i = 0; i < processedParam.intValue(); i++) {forTotal += i;}
synchronized (this) {sourceTargetField.targetIntField = forTotal;}
Function<String, Integer> ref1 = Target.TargetStaticNested::strToInt;Function<Number, String> ref2 = Target.TargetStaticNested::numToStr;int refResult = ref1.apply(ref2.apply(processedParam));
Object var = sourceTargetField;return objField1 + forTotal + refResult;}
int instanceMethod(U param) {return instanceMethod(param, "default_str");}
public void localInnerDemo() {class SourceLocalInner {public void printTargetField() {System.out.println(sourceTargetField.targetStrField);}}new SourceLocalInner().printTargetField();}}
public static class TargetStaticNested {public void staticNestedMethod (String param) {}
public static int strToInt(String str) {return str.length();}
public static String numToStr(Number num) {return num.toString();}}}
package same;
import org.junit.Test;import static org.junit.Assert.*;
public class SourceMethodTest {@Testpublic void testInstanceMethod() {Source<Integer> source = new Source<>();int result = source.instanceMethod(3, "test");
assertEquals(10 + 3 + 4, result);assertEquals(3, source.sourceTargetField.targetIntField);}
@Test(expected = NullPointerException.class)public void testInstanceMethodWithNull() {Source<Long> source = new Source<>();source.instanceMethod(null, "test");}
@Testpublic void testOverloadMethod() {Source<Double> source = new Source<>();int result = source.instanceMethod(2.0);
assertEquals(10 + 1 + 3, result);}}