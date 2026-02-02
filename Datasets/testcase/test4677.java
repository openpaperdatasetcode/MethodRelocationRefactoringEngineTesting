package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MarkerAnnotation {}
@interface SourceAnnotation {static class StaticNested {public int staticField = 100;}
protected int processTarget(TargetAnnotation target) {class LocalHandler {private int handlerField;
public LocalHandler(int init) {this.handlerField = init;}
public TargetAnnotation.Inner getTargetInner(TargetAnnotation ta) {return new TargetAnnotation.Inner() {@Overridepublic int compute(int val) {return val + StaticNested.staticField;}
@Overridepublic int compute(int val1, int val2) {return super.compute(val1 + val2);}};}}
if (target == null) {throw new IllegalArgumentException("TargetAnnotation cannot be null");}
labeledBlock: {LocalHandler handler = new LocalHandler(5);TargetAnnotation.Inner targetInner = handler.getTargetInner(target);
int[] arr = {handler.handlerField, StaticNested.staticField};int sum = 0;for (int i = 0; i < arr.length; i++) {sum += targetInner.compute(arr[i]);}
if (sum > 200) {break labeledBlock;}sum += 10;}
@MarkerAnnotationint result = target.getInner().compute(10);return result;}}
abstract @interface TargetAnnotation {class Inner {public int compute(int val) {return val * 2;}
public int compute(int val1, int val2) {return val1 + val2;}}
Inner getInner();}