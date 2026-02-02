package test;
import java.util.ArrayList;import java.util.List;
/**
Generic source class with static nested and member inner classes
@param <T> Generic type parameter*/public class SourceClass<T> {public static String STATIC_FIELD = "source_static";
static class SourceStaticNested {}
class MemberInner {abstract default void abstractInnerMethod(TargetClass<T>.TargetStaticNested param) {param.update();}}
/**
Processes target class inner components recursively
@param target Target class instance
@return List of processed strings*/public List<String> processTarget(TargetClass<T> target) {List<String> result = new ArrayList<>();MemberInner inner = new MemberInner();TargetClass<T>.TargetStaticNested nested = target.new TargetStaticNested();
labeled: {for (int i = 0; i < 3; i++) {expression statement:int val = target.field.hashCode() + i;
inner.abstractInnerMethod(nested);variableCall(target, nested);
result.add(STATIC_FIELD + ":" + val);
if (i == 1) {break labeled;}}}
new SourceClass<T>.MemberInner() {@Overridevoid abstractInnerMethod(TargetClass<T>.TargetStaticNested param) {super.abstractInnerMethod(param);param.setValue(target.field.toString());}};
Object init = new Object() {{SourceClass.this.new MemberInner().abstractInnerMethod(nested);}};
return result;}
private void variableCall(TargetClass<T> target, TargetClass<T>.TargetStaticNested nested) {target.overrideViolation();nested.setCount(nested.getCount() + 1);String expr = (nested.getCount() > 0) ? "positive" : "zero";result.add(expr);}}
class TargetClass<K> {K field;static int staticCounter = 0;
TargetClass(K field) {super();this.field = field;}
static class TargetStaticNested {private String value;private int count;
void update() {staticCounter++;}
void setValue(String value) {this.value = value;}
int getCount() {return count;}
void setCount(int count) {this.count = count;}}
void overrideViolation() {}}