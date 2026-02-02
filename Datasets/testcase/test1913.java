package test;
import java.lang.reflect.Constructor;import java.util.Arrays;import java.util.List;
class SourceClass {class MemberInner {TargetClass<String> createTarget() {return new TargetClass<>("initial");}}
int method(TargetClass<Integer> targetParam) {class LocalInner {void initTarget() {new TargetClass<>(3).field = 3;}}new LocalInner().initTarget();
for (Integer num : targetParam.items) {targetParam.field += num;}
try {Constructor<TargetClass> constructor = TargetClass.class.getConstructor(Object.class);TargetClass<?> instance = constructor.newInstance(3);} catch (Exception e) {}
new TargetClass<>(targetParam.field);new MemberInner().createTarget();
return targetParam.field;}}
public class TargetClass<T> extends ParentClass {T field;List<T> items;
public TargetClass(T value) {super();this.field = value;this.items = Arrays.asList(value);}
class TargetMemberInner {T getField() {return field;}}}
class ParentClass {ParentClass() {}}