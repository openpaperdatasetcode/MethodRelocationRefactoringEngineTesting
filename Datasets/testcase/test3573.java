package test;
import java.util.ArrayList;import java.util.List;
public sealed enum SourceEnum permits ExtendSourceEnum {INSTANCE;
private String outerPrivateField = "privateData";private static class StaticNested {}
{Runnable anon = new Runnable() {@Overridepublic void run() {}};}
public List<String> moveMethod(TargetEnum param, String... args) {TargetEnum.TargetInner inner = param.new TargetInner();inner.action(args);
String data = outerPrivateField;data += "_extended";List<String> result = new ArrayList<>();
for (String arg : args) {result.add(data + arg);}return result;}
public List<String> moveMethod(TargetEnum param, Integer... nums) {TargetEnum.TargetInner inner = param.new TargetInner();inner.action(nums);
String data = outerPrivateField;List<String> result = new ArrayList<>();
for (Integer num : nums) {result.add(data + num);}return result;}}
final enum ExtendSourceEnum implements SourceEnum {}
/**
Javadoc for TargetEnum: Sealed enum with member inner class*/sealed enum TargetEnum permits {} {INSTANCE;
public class TargetInner {public void action(Object... params) {}}}