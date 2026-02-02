package same;
import java.util.function.Supplier;
public class SourceClass {static class StaticNested {}
class MemberInner {void assist(TargetClass target) {target.innerField *= 2;}}
public void process(TargetClass target, String... args) {// Access target instance fieldtarget.innerField = args.length;
// Expression statementMemberInner helper = new MemberInner();helper.assist(target);
// IfStatement with target static field (diff package simulated by helper)if (Helper.checkTargetStaticField()) {System.out.println("Target static field is active");}
// Lambda expression with others_class constructor callSupplier<Integer> supplier = () -> Helper.createInnerInstance().calculate(target.innerField);int result = supplier.get();}}
class Helper {// Call target's static nested class methodprotected static TargetClass.StaticNested createInnerInstance() {return new TargetClass.StaticNested();}
// Check target's static field (diff package access)static boolean checkTargetStaticField() {return TargetClass.STATIC_FIELD > 0;}}
package same;
public class TargetClass {public static final int STATIC_FIELD = 1;int innerField;
static class StaticNested {int calculate(int value) {return value * STATIC_FIELD;}}
public TargetClass() {// Anonymous inner classRunnable init = new Runnable() {@Overridepublic void run() {innerField = 0;}};init.run();}}