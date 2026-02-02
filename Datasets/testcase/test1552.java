package test;
import java.lang.annotation.*;import java.util.function.Function;
@Retention(RetentionPolicy.RUNTIME)@interface OverrideAnn {Class<?> methodRef();}
protected class SourceClass<T> {public class MemberInner {T data;}
protected Object process(TargetClass<String> target) {class LocalHandler {TargetClass<String> createTarget() {return new TargetClass<>();}}
TargetClass<String> localTarget = new LocalHandler().createTarget();volatile String pattern = "value=%s";
loop: for (int i = 0; i < 3; i++) {target.field1 = "field1-" + i;target.field2 = "field2-" + i;
if (i == 1) {break loop;}}
return target;}
@OverideAnn(methodRef = OthersClass.class)final TargetClass<String> apply(TargetClass<String> target) {Function<TargetClass<String>, TargetClass<String>> func = OthersClass::process;return func.apply(target);}}
protected class TargetClass {
public U field1;
public U field2;
void handle(U input) {class LocalProcessor {U process() {return input;}}new LocalProcessor().process();}}
class OthersClass {public static TargetClass process(TargetClass target) {
return target;
}
}