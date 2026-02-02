package test;
import java.lang.reflect.Method;
class ParentClass {}
strictfp class SourceClass {static class StaticNested {}
{new Runnable() {@Overridepublic void run() {new SourceClass().varargsMethod(new TargetClass.NestedRec(5), new TargetClass.NestedRec(10));}}.run();}
public TargetClass varargsMethod(TargetClass.NestedRec... recs) {// Type declaration statementTargetClass target = new TargetClass();TargetClass.NestedRec firstRec = recs.length > 0 ? recs[0] : null;
// ThrowStatement with protected modifier using 2 target fieldsif (firstRec == null) {protected class ProtectedThrowHelper {void checkFields() {if (target.field1 < 0 || target.nested.field2 < 0) {throw new IllegalArgumentException("Invalid fields: " + target.field1 + ", " + target.nested.field2);}}}new ProtectedThrowHelper().checkFields();}
// Expression statementint sum = 0;for (TargetClass.NestedRec rec : recs) {sum += rec.value();}
// CastExpression with abstract modifier (2 instances)abstract class AbstractCaster {TargetClass cast1(Object obj) {return (TargetClass) obj;}
TargetClass.NestedRec cast2(Object obj) {return (TargetClass.NestedRec) obj;}}AbstractCaster caster = new AbstractCaster() {};TargetClass castedTarget = caster.cast1(target);TargetClass.NestedRec castedRec = caster.cast2(firstRec);
// Variable call and override violation (calling final method as if overridden)String invalidCall = target.finalMethod();
// Used by reflectiontry {Method method = TargetClass.NestedRec.class.getMethod("value");sum += (int) method.invoke(castedRec);} catch (Exception e) {}
return target;}}
public class TargetClass extends ParentClass {int field1 = 10;NestedStatic nested = new NestedStatic();
public static class NestedStatic {int field2 = 20;}
public record NestedRec(int value) {}
public final String finalMethod() {return "Final method: " + field1;}}