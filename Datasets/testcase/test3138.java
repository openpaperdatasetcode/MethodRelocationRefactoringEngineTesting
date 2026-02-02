package test;
import java.lang.reflect.Method;
abstract class TargetClass {Object targetField;static class TargetStaticNested { // target_feature: static nested classclass TargetInner {} // target_inner}}
public class SourceClass {// Source feature: member inner classclass MemberInner {// Accessor method (parent)public Object getTargetField(TargetClass.TargetStaticNested.TargetInner inner) {TargetClass target = new TargetClass() {};return target.targetField;}}
// Source feature: anonymous inner classprivate final Runnable anonymous = new Runnable() {@Overridepublic void run() {new MemberInner().getTargetField(new TargetClass.TargetStaticNested().new TargetInner());}};
// Accessor method (default access, override_violation: weaker access than parent)Object getTargetField(TargetClass.TargetStaticNested.TargetInner inner) {// Type declaration statementclass LocalAccessor {Object getField(TargetClass target) {return target.targetField;}}
// Variable callTargetClass target = new TargetClass() {};LocalAccessor accessor = new LocalAccessor();Object fieldVal = accessor.getField(target);
// Used_by_reflectiontry {Method getter = TargetClass.class.getDeclaredMethod("getTargetField");getter.setAccessible(true);fieldVal = getter.invoke(target);} catch (Exception e) {// No new exception thrown}
return fieldVal;}}