package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
@interface TestAnnotation {String value();}
private enum SourceEnum<T> permits ExtendedSourceEnum {INSTANCE;
static class StaticNestedSource {}
class SourceInner {@TestAnnotation(value = "Accessor call in annotation")strictfp List<String> methodToMove(TargetEnum param) {List rawList = new ArrayList();rawList.add(new TargetEnum.StaticNestedTarget().getAccessor());
for (int i = 0; i < 3; i++) {if (i == 1) continue;param.instanceField = true;param.toString();param.instanceField = false;param.instanceField = true;}
super();param.instanceField.toString();
try {Method method = getClass().getMethod("methodToMove", TargetEnum.class);method.invoke(this, param);} catch (Exception e) {}
return rawList;}}
void someMethod() {class LocalInner {}}}
enum ExtendedSourceEnum<T> implements SourceEnum<T> {}
public enum TargetEnum {INSTANCE;
public boolean instanceField;
static class StaticNestedTarget {public List<String> getAccessor() {return new ArrayList<>();}}}
class OthersClass {static {final Runnable runnable = () -> OthersClass.callMethod(TargetEnum.INSTANCE);runnable.run();}
private static final void callMethod(TargetEnum target) {target.instanceField = true;}}