package test;
import java.util.List;
@RefactorTestAnnotationpublic class SourceClass {private int outerPrivateField = 50;
{Runnable anon = new Runnable() {@Overridepublic void run() {}};}
<T extends Number> int moveMethod(PublicTarget target, T... values) {class LocalInner {}LocalInner local = new LocalInner();
PublicTarget.StaticNested nested = new PublicTarget.StaticNested();int sum = 0;for (T val : values) {sum += target.inner.innerRec.calculate(val.intValue(), this.outerPrivateField);nested.process(sum);}
this.helperMethod(sum);return sum;}
private void helperMethod(int num) {}}
interface TestInterface {}
public class PublicTarget implements TestInterface {public static class StaticNested {public void process(int val) {}}
TargetInner inner = new TargetInner();
class TargetInner {TargetInnerRec innerRec = new TargetInnerRec();
class TargetInnerRec {public int calculate(int a, int b) {return a + b;}}}}
@interface RefactorTestAnnotation {}