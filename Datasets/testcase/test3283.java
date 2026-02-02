package sourcepkg;
import targetpkg.TargetEnum;import java.io.IOException;import java.util.function.Consumer;
public enum SourceEnum {INSTANCE;
static class SourceStaticNested {static void staticHelperMethod(TargetEnum target) {}}
class SourceMemberInner {protected Object instanceMethod(TargetEnum targetParam) throws IOException {if (targetParam == null) {throw new NullPointerException();}
class SourceLocalInner {}new SourceLocalInner();
synchronized (this) {targetParam.doAction(); // Variable callSystem.out.println(targetParam.toString()); // Expression statement}
Consumer<TargetEnum> consumer = this::overloadedMethod;consumer.accept(targetParam);
TargetEnum rawTarget = TargetEnum.VALUE; // Raw typeString assignVar = rawTarget.name(); // Assignment expression
this.overloadedMethod(targetParam, 10);SourceEnum.SourceStaticNested.staticHelperMethod(targetParam);
return assignVar;}
default void overloadedMethod(TargetEnum target) {}
default void overloadedMethod(TargetEnum target, int num) {}}}
package targetpkg;
protected enum TargetEnum {VALUE;
void doAction() {class TargetLocalInner {}new TargetLocalInner();}}