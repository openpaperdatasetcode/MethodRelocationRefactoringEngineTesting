import java.util.ArrayList;import java.util.List;
public enum SourceEnum<T extends String> {INSTANCE_ONE("data1"),INSTANCE_TWO("data2");
private final T enumField;
SourceEnum(T enumField) {this.enumField = enumField;}
public static class StaticNested<T> {public T processValue(T value) {return value;}}
private List<String> instanceMethod(TargetEnum target) {List<String> result = new ArrayList<>();
class LocalInner {void addTargetData(TargetEnum target) {result.add(target.getInnerData());result.add(SourceEnum.this.enumField);}}LocalInner local = new LocalInner();local.addTargetData(target);
variableCall(target);result.add(overloadMethod(target));result.add(overloadMethod(target, "prefix_"));
return result;}
private void variableCall(TargetEnum target) {target.memberInner.updateData(enumField);}
private String overloadMethod(TargetEnum target) {return target.name() + "_" + enumField;}
private String overloadMethod(TargetEnum target, String prefix) {return prefix + target.name() + "_" + enumField;}
@CustomAnnotation(value = TargetEnum.TARGET_INSTANCE.new MemberInner().getProcessedData())public void callInAnnotation() {instanceMethod(TargetEnum.TARGET_INSTANCE);}}
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface CustomAnnotation {String value();}
private enum TargetEnum implements DataHandler {TARGET_INSTANCE;
public MemberInner memberInner = new MemberInner();
public class MemberInner {private String innerData;
public void updateData(String data) {this.innerData = data;}
public String getInnerData() {return innerData;}
public String getProcessedData() {return "processed_" + innerData;}}
@Overridepublic void handleData() {memberInner.updateData(name());}}
interface DataHandler {void handleData();}