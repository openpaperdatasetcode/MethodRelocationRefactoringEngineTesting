package test;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface Accessor {}
public enum SourceEnum {VALUE;
private static int privateStaticField = 5;
static class StaticNested {static int nestedStatic = 10;}
static {TargetEnum.DEFAULT.initialize(SourceEnum::createTarget);}
private static <T extends CharSequence> TargetEnum createTarget(T value) {return TargetEnum.DEFAULT;}
@Accessorpublic final List<String> getInnerData(TargetEnum target) {class LocalProcessor {int processItem(String item) {return item.length() + privateStaticField;}}
List<String> result = new ArrayList<>();TargetEnum.InnerFunctional func = target.new InnerFunctional() {};
for (String data : func.getData()) {int len = new LocalProcessor().processItem(data);result.add(String.valueOf(len));}
return result;}
int countElements(TargetEnum target) {this.getInnerData(target);return target.getCount();}}
enum TargetEnum {DEFAULT;
private int count;
abstract <T extends CharSequence> TargetEnum initialize(SourceEnum supplier);
class InnerFunctional {List<String> getData() {return List.of("a", "b", "c");}}
int getCount() {return count;}}