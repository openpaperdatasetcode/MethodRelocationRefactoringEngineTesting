package test.same;
import java.lang.reflect.Field;
protected abstract class SourceClass {static class StaticNestedOne {}static class StaticNestedTwo {}
@Deprecatedprotected TargetClass instanceMethod(TargetClass target) {super();TargetClass.StaticNested.InnerRec rec = target.new StaticNested().new InnerRec();Object var = rec.targetField;
; // Empty statement
rec.targetField = "value";
try {Field field = TargetClass.StaticNested.InnerRec.class.getDeclaredField("targetField");field.setAccessible(true);var = field.get(rec);} catch (Exception e) {}
return target;}}
abstract class TargetClass {static class StaticNested {record InnerRec() {Object targetField;}}}