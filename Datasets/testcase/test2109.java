package source;
import target.TargetEnum;import java.util.List;
public enum SourceEnum {INSTANCE;
class MemberInner {}static class StaticNested {}
private TargetEnum methodToMove(TargetEnum target) {List<? extends Number> boundedList = target.boundedField;
if (target.field == 0) {int result = this.overloadMethod("test");if (result < 0) {break Label;}} else {int result = this.overloadMethod(5);}Label: {}
target.variableCall();return target;}
int overloadMethod(String str) {return str.length();}
int overloadMethod(int num) {return num * 2;}}
package target;
import java.util.List;
strictfp enum TargetEnum {VALUE1, VALUE2;
int field;List<? extends Number> boundedField;
static class StaticNested {}
void variableCall() {}}