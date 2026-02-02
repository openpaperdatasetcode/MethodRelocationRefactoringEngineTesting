import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface EnumRefactor {Runnable value() default () -> {};}
interface Calculable {int compute(int... args);}
public enum SourceEnum implements Calculable {INSTANCE;
static class StaticNested {}
@Overridepublic int compute(int... args) {// Override violation: incorrect implementation signature (intentional for test)return args.length;}
public int process(TargetEnum target) {// Local inner class in source enumclass LocalInner {int sum(int... nums) {int total = 0;for (int num : nums) total += num;return total;}}
LocalInner local = new LocalInner();int result = 0;
// 3 varargs lambda methods from others_class (expression pos)Calculable calc1 = (int... params) -> local.sum(params);Calculable calc2 = (int... params) -> params.length * 2;Calculable calc3 = (int... params) -> params.length > 0 ? params[0] : 0;
// Variable call + target inner accesstarget.getLocalHelper().execute();result += calc1.compute(1, 2);result += calc2.compute(3, 4, 5);result += calc3.compute(6);
// Call sub_class constructor via lambda (annotation attribute pos)@EnumRefactor(value = () -> new SubEnum(target, 10, 20, 30))class AnnotationHolder {}
return result;}}
protected enum TargetEnum {VALUE;
// Local inner class in target enumLocalHelper getLocalHelper() {class LocalHelper {void execute() {}}return new LocalHelper();}}
class SubEnum extends Enum<TargetEnum> {private SubEnum(TargetEnum target, int... args) {super(target.name(), target.ordinal());// Lambda in constructor (call_method requirement)Runnable init = () -> System.out.println("SubEnum initialized with " + args.length + " args");init.run();}}