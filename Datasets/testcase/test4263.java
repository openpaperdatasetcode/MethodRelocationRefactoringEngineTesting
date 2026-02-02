package same.pkg;
import java.lang.reflect.Field;
public class TargetClass {public int instanceField;
public static class TargetInner {private TargetClass outerTarget;
public TargetInner(TargetClass outerTarget) {this.outerTarget = outerTarget;}
public int getOuterField() {return outerTarget.instanceField;}}}
class SourceClass {private TargetClass targetField = new TargetClass();
class SourceMemberInner {void updateTargetField(int value) {targetField.instanceField = value;}}
public final TargetClass.TargetInner createTargetInner() throws NoSuchFieldException, IllegalAccessException {if (targetField == null) {throw new NullPointerException("TargetClass field cannot be null");}
synchronized (targetField) {new SourceMemberInner().updateTargetField(10);
transient int checkVal = targetField.instanceField;assert checkVal == 10 : "Target instance field value mismatch";}
Field innerField = TargetClass.TargetInner.class.getDeclaredField("outerTarget");innerField.setAccessible(true);TargetClass.TargetInner targetInner = new TargetClass.TargetInner(targetField);innerField.set(targetInner, super.getClass().cast(targetField));
Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println(targetInner.getOuterField());}};anonymous.run();
return targetInner;}}
