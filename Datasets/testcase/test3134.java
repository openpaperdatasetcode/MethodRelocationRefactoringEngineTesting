package test;
import java.sql.SQLException;import java.lang.reflect.Field;import java.lang.reflect.Constructor;
// Diff package for IfStatementpackage diff;
import test.TargetClass;
public class DiffPackageOthers {// IfStatement (public modifier, ClassName.field = 2, pos: diff_package_others)public static void checkField() {if (TargetClass.TargetStaticNested.staticField == 2) {System.out.println("Field value matches");}}}
package test;
import diff.DiffPackageOthers;
// Target class (public modifier + static nested class)public class TargetClass {String instanceField; // For access_instance_fieldstatic class TargetStaticNested { // target_feature: static nested classpublic static int staticField = 2; // ClassName.field}}
// Source interface (for implements feature)interface SourceInterface {}
// Source class (default modifier + implements + static nested + member inner)class SourceClass implements SourceInterface {// Source feature: static nested classstatic class SourceStaticNested {}// Source feature: member inner classclass SourceInner {}
// Instance method (public access + void return + generic type parameter)public <T extends TargetClass> void methodToMove(T target, T... args) throws SQLException {// Variable callString var = target.instanceField;TargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested();
// Constructor invocationSourceStaticNested sourceStatic = new SourceStaticNested();SourceInner sourceInner = new SourceInner();TargetClass newTarget = new TargetClass();
// Expression statementtarget.instanceField = "updated";TargetClass.TargetStaticNested.staticField = 2;
// Access_instance_fieldvar = target.instanceField;
// With_boundsclass BoundedType {}
BoundedType<TargetClass> bounded = new BoundedType<>();
// IfStatement (pos: diff_package_others)DiffPackageOthers.checkField();
// SQLException (checked exception declaration)if (var == null) {throw new SQLException("Instance field is null");}
// Used_by_reflectiontry {Field field = TargetClass.class.getDeclaredField("instanceField");field.setAccessible(true);field.set(target, "reflected");
Constructor<TargetClass> constructor = TargetClass.class.getDeclaredConstructor();TargetClass reflectedTarget = constructor.newInstance();} catch (Exception e) {// No new exception thrown}}}