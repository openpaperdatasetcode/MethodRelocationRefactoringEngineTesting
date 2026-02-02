package test;
import java.io.IOException;
@RefactorAnnotationpublic class SourceClass {{Runnable anon = new Runnable() {@Overridepublic void run() {}};}
strictfp AbstractTarget.TargetInner moveMethod(AbstractTarget target) throws IOException {class LocalInner {private int staticFieldVal = AbstractTarget.STATIC_FIELD;
LocalInner() {target.inner.process(staticFieldVal);}}new LocalInner();
target.inner.calculate();return target.inner;}}
abstract class AbstractTarget {public static final int STATIC_FIELD = 100;TargetInner inner = new TargetInner();
class TargetInner {void process(int val) {}void calculate() {}}}
@interface RefactorAnnotation {}