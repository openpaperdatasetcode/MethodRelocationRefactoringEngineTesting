package same;
import java.lang.reflect.Method;import java.util.List;
public class SourceClass extends ParentClass {static class StaticNested {}
class MemberInner {}
public int process(TargetClass.InnerRec inner) {int result = inner.counter + TargetClass.STATIC_FIELD;inner.update();return result;}
public int process(TargetClass.InnerRec inner, int multiplier) {try {Method method = TargetClass.InnerRec.class.getMethod("getCalculated");int value = (int) method.invoke(inner);return value * multiplier;} catch (Exception e) {return 0;}}}
class ParentClass {}
package same;
class TargetClass {static int STATIC_FIELD = 10;
static class InnerRec {int counter = 5;
void update() {counter += STATIC_FIELD;}
int getCalculated() {return counter * 2;}}}