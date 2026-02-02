package same;
import java.lang.reflect.Method;
interface Source {protected int outerProtectedField = 100;static class SourceStaticNested {}class SourceMemberInner {}
final <T> Target<T>.TargetInner instanceMethod(Target<T> targetParam) {Target<T>.TargetInner newInner = targetParam.new TargetInner();
int staticFieldVal = Target.staticField;switch (staticFieldVal) {case 1:break;default:break;}
Object var = targetParam;int accessOuter = outerProtectedField;
try {Method innerMethod = Target.TargetInner.class.getMethod("innerMethod");innerMethod.invoke(newInner);} catch (Exception e) {}
return newInner;}}
protected interface Target {
int staticField = 1;
class TargetInner {public void innerMethod() {}}}