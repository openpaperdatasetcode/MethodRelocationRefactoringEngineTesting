package test;
import java.lang.reflect.Field;import java.util.function.Supplier;
public class SourceClass {static class StaticNestedOne {}static class StaticNestedTwo {}
public Object process(TargetClass target) {class LocalInner {private void updateField() {target.superField = 3;}}
new LocalInner().updateField();assert target.superField == 3 : "Field value mismatch";
try {Field field = TargetClass.class.getField("superField");Object value = field.get(target);} catch (Exception e) {return null;}
Supplier<String> supplier = TargetClass::newInstance;return supplier.get();}}
class TargetClass extends ParentClass implements MyInterface {public int superField;
void method() {class LocalInner {}}
protected static String newInstance() {return "New instance";}}
class ParentClass {}interface MyInterface {}