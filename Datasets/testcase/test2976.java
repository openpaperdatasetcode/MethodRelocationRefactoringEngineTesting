package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
public enum SourceEnum {INSTANCE;
private String outerPrivateField = "source_private";
public class MemberInner {public String getInnerData() {return "inner_data";}}
public static class StaticNested {public static String processStatic(String input) {return input.toUpperCase();}}
@SuppressWarnings("unchecked")private List<String> process(TargetEnum... targets) {List<String> result = new ArrayList<>();MemberInner inner = new MemberInner();int index = 0;
while (index < targets.length) {TargetEnum target = targets[index];result.add(target.staticNested.targetField);result.add(1 + "_suffix");index++;}
result.add(outerPrivateField);result.add(inner.getInnerData());result.add(StaticNested.processStatic(targets[0].name()));
Function<TargetEnum, List<String>> lambda = t -> OthersClass.callProtected(t, t.name());result.addAll(lambda.apply(targets[0]));
return result;}}
enum TargetEnum implements SomeInterface {TARGET_INSTANCE;
public static class StaticNested {public String targetField = "target_static_field";}
public StaticNested staticNested = new StaticNested();
@Overridepublic void interfaceMethod() {}}
interface SomeInterface {void interfaceMethod();}
class OthersClass {protected static List<String> callProtected(TargetEnum target, String arg) {List<String> list = new ArrayList<>();list.add(target.name() + "_" + arg);list.add(target.staticNested.targetField);return list;}}