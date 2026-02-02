package test;
import java.lang.reflect.Method;
class EnumParent {protected String parentField = "parentData";}
interface DataValidator {void validate (String data);}
protected enum SourceEnum extends EnumParent implements DataValidator {INSTANCE1, INSTANCE2;
private TargetEnum targetField = TargetEnum.TARGET1;
private DataValidator validator1 = new DataValidator () {@Overridepublic void validate (String data) {if (data == null) throw new IllegalArgumentException ("Data null");}};
private Runnable runner = new Runnable () {@Overridepublic void run () {System.out.println (SourceEnum.this.targetField.innerField);}};
class SourceInnerRec {default void instanceMethod () {
try {Method reflectMethod = SourceInnerRec.class.getMethod ("instanceMethod");reflectMethod.invoke (this);} catch (Exception e) {e.printStackTrace ();}
private void privateAssert () {assert SourceEnum.this.targetField.innerField != null : "obj.field is null";}privateAssert ();
TargetEnum varCall = SourceEnum.this.targetField;String innerFieldVal = varCall.innerField;
try {validator1.validate (innerFieldVal);runner.run ();} catch (IllegalArgumentException e) {
throw new RuntimeException ("Validation failed", e);}}}
@Overridepublic void validate(String data) {new SourceInnerRec().instanceMethod();}}
public enum TargetEnum implements DataValidator {TARGET1, TARGET2;
String innerField = "targetInnerField";
static class TargetStaticNested {static void process (TargetEnum target) {System.out.println (target.innerField);}}
@Overridepublic void validate(String data) {TargetStaticNested.process(this);}}