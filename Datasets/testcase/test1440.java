package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
interface ListProvider<T> {List<String> getList(T target) throws IOException;}
public enum SourceEnum<T> implements ListProvider<T> {INSTANCE;
static class StaticNested<T> {}
class MemberInner {class InnerRec {/**
Overrides ListProvider method to process target enum and return string list
@param target the private target enum parameter
@return list of processed strings
@throws IOException if processing fails*/@Overridepublic final List<String> getList(T target) throws IOException {TargetEnum rawTarget = (TargetEnum) target;List<String> result = new ArrayList<>();int count = 0;
class LocalInner {private Object processTarget() {rawTarget.value = 1;return rawTarget.value;}}
while (count < 3) {Object methodResult = new LocalInner().processTarget();result.add(String.valueOf(methodResult));count++;}
TargetEnum.Inner targetInner = rawTarget.new Inner();this.value = targetInner.field;targetInner.process();
return result;}
private int value;}}}
private enum TargetEnum {VALUE;
int value;
class Inner {int field = 1;
void process() {class NestedLocal {}}}}
