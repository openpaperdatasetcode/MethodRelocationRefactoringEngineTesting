package same;
import java.lang.reflect.Constructor;import java.lang.reflect.Method;
protected abstract class Source<T> {private Target<String> sourceTargetField = new Target<>();private T outerPrivateField;
class SourceMemberInner {class SourceInnerRec {private SourceInnerRec(T initVal) {outerPrivateField = initVal;
class InnerClassWithWhile {private void privateWhileStatement() {int count = 0;while (count < 1) {this.toString();count++;}}}new InnerClassWithWhile().privateWhileStatement();
if (sourceTargetField != null) {Object var = sourceTargetField;Target.TargetInner.TargetInnerRec targetRec = sourceTargetField.new TargetInner().new TargetInnerRec();targetRec.recMethod(outerPrivateField.toString());}
try {Constructor<?> targetInnerConstr = Target.TargetInner.class.getConstructor(Target.class);Method recMethod = Target.TargetInner.TargetInnerRec.class.getMethod("recMethod", String.class);Object targetInner = targetInnerConstr.newInstance(sourceTargetField);recMethod.invoke(((Target.TargetInner) targetInner).new TargetInnerRec(), "reflection_param");} catch (Exception e) {e.printStackTrace();}}}}
public void createInnerRec(T val) {new SourceMemberInner().new SourceInnerRec(val);}
Runnable sourceAnonInner = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class: " + outerPrivateField);}};}
abstract class Target<T> {
class TargetInner {
class TargetInnerRec {
public void recMethod (String param) {
class TargetLocalInner {
private String localVal = param;
public String getLocalVal () {
return localVal;
}
}
new TargetLocalInner ().getLocalVal ();
}
}
}
}