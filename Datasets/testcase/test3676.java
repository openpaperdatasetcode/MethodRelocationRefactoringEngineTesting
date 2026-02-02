package test;
interface EnumVarargsProcessor {int process(TargetEnum... targets) throws Exception;}
sealed enum SourceEnum implements EnumVarargsProcessor permits SubSourceEnum {INSTANCE;
public static class StaticNestedFirst {public static int getStaticHelper() {return 1;}}
public static class StaticNestedSecond {public static void validateTarget(TargetEnum target) {if (target == null) throw new IllegalArgumentException("Target cannot be null");}}
@Overridepublic int process(TargetEnum... targets) throws Exception {class LocalType {int sumFields(TargetEnum target) {return target.value + TargetEnum.STATIC_FIELD;}}
LocalType local = new LocalType();int total = 0;
for (int i = 0; i < targets.length; i++) {StaticNestedSecond.validateTarget(targets[i]);total += local.sumFields(targets[i]);}
int doTotal = 0;int count = 0;do {doTotal += StaticNestedFirst.getStaticHelper();count++;} while (count < TargetEnum.STATIC_FIELD);
for (TargetEnum target : targets) {Runnable lambda = () -> System.out.println(target.value);lambda.run();total += target.value;}
return total + doTotal;}
public int process(TargetEnum target) {return target.value * TargetEnum.STATIC_FIELD;}}
final class SubSourceEnum extends SourceEnum {@Overridepublic int process(TargetEnum... targets) throws Exception {return super.process(targets) * 2;}}
strictfp enum TargetEnum {VALUE1(5), VALUE2(10);
public static final int STATIC_FIELD = 1;public int value;
TargetEnum(int value) {this.value = value;}
public void compute() {class LocalInner {void updateValue() {value *= 2;}}new LocalInner().updateValue();}}