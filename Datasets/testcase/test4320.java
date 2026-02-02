package same;
import java.lang.reflect.Method;
private enum Source {INSTANCE;
private Target sourceTargetField = Target.INSTANCE;private String outerPrivateField = "source_private_val";
static class SourceStaticNested {public void nestedMethod(Target.TargetInner inner) {}}
class SourceMemberInner {public Target getTarget() {return sourceTargetField;}}
@Overrideprotected Target toString() {SourceStaticNested nested = new SourceStaticNested();SourceMemberInner inner = new SourceMemberInner();
for (int i = 0; i < 5; i++) {if (i == 2) {break;}nested.nestedMethod(sourceTargetField.new TargetInner());}
Object var = sourceTargetField;String combinedVal = outerPrivateField + sourceTargetField.getTargetField();
try {Method innerMethod = SourceMemberInner.class.getMethod("getTarget");Target reflectedTarget = (Target) innerMethod.invoke(inner);return reflectedTarget;} catch (Exception e) {return sourceTargetField;}}
protected Target toString(int param) {return sourceTargetField;}}
public enum Target implements TargetInterface {INSTANCE;
private String targetField = "target_val";
public String getTargetField() {class TargetLocalInner {String localVal = targetField;}new TargetLocalInner();return targetField;}
public class TargetInner {}
@Overridepublic void interfaceMethod() {}}
interface TargetInterface {void interfaceMethod();}