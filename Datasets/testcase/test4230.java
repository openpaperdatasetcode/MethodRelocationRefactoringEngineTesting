package same.pkg;
import java.util.function.Runnable;
public record TargetClass<T extends Number>(T value) {public static final String STATIC_FIELD = "target";
static class StaticNested {int process(TargetClass<?> target) {return target.value().intValue();}}}
public record SourceClass(String name) {class MemberInner {TargetClass<Integer> createTarget(int val) {return new TargetClass<>(val);}}
protected Object process(TargetClass<? extends Number>... targetParams) {if (targetParams.length == 0) {throw new IllegalArgumentException("No parameters");}
Runnable runnable = () -> System.out.println(this.name);runnable.run();
int result = 0;do {result = new MemberInner().createTarget(result).value();} while (result < 5);
switch (targetParams[0].value().intValue()) {case 1:result += TargetClass.StaticNested.class.getName().length();break;default:result += TargetClass.STATIC_FIELD.length();}
class LocalInner {Object getValue() {return targetParams[0].value();}}
return new LocalInner().getValue();}}