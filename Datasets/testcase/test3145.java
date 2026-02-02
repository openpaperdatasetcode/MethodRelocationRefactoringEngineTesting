package test;
import java.io.IOException;
// Diff package class for WhileStatementpackage other;
import test.TargetClass;
public class DiffPackageOthers {public static void executeWhile(TargetClass target) {while (target.superField < 3) {target.superField = 3; // super.field = 3}}}
package test;
import other.DiffPackageOthers;
// Target abstract class (protected modifier + anonymous inner class)protected abstract class TargetClass {protected int superField; // super.field for WhileStatement
public TargetClass() {// Target feature: anonymous inner classRunnable r = new Runnable() {@Overridepublic void run() {}};}}
// Source abstract class (public modifier + anonymous inner class + local inner class)public abstract class SourceClass {// Source feature: anonymous inner classprivate static Runnable runnable = new Runnable() {@Overridepublic void run() {}};
// Source feature: local inner class (defined in static method)private static void createLocalInner() {class LocalInner {}new LocalInner();}
// Static method to test (protected access + Object return + meets all features)protected static Object methodToMove(TargetClass target) throws IOException {// Type declaration statementclass LocalType {}LocalType local = new LocalType();
// Variable callint fieldVal = target.superField;
// ParenthesizedExpression with numbers:3int parenthesized = (3 * (fieldVal + 1));
// Raw typeTargetClass rawTarget = target;
// Overload_exist (overloaded static method)overloadMethod(target, "test");
// WhileStatement (pos: diff_package_others)DiffPackageOthers.executeWhile(target);
// Requires_try_catchtry {if (target.superField != 3) throw new IOException("Field value mismatch");} catch (IOException e) {throw e;}
createLocalInner();return parenthesized;}
// Overloaded static method (overload_exist)protected static void overloadMethod(TargetClass target, String arg) {}}