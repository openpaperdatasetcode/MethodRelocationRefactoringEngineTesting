package same.pkg;
import java.util.ArrayList;import java.util.List;
sealed enum SourceEnum permits SubSourceEnum {INSTANCE;
static class StaticNestedOne {static TargetEnum fetchTarget() {return TargetEnum.VALUE;}}
static class StaticNestedTwo {static boolean isValid(TargetEnum target) {return target != null;}}
static List<String> processTargets(TargetEnum... targetParams) {List<String> result = new ArrayList<>();int index = 0;
private class WhileHelper {boolean hasNext() {return index < targetParams.length;}}
WhileHelper helper = new WhileHelper();while (helper.hasNext()) {TargetEnum target = targetParams[index];if (StaticNestedTwo.isValid(target)) {target.processLocal();result.add(String.valueOf(target.value));}index++;}
return result;}}
non-sealed enum SubSourceEnum extends SourceEnum {}
public enum TargetEnum {VALUE;
int value;
void processLocal() {class LocalInner {void updateValue() {value = 10;}}new LocalInner().updateValue();}}