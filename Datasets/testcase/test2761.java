package test;
import java.io.IOException;import java.util.List;import java.util.ArrayList;
sealed enum SourceEnum permits SourceSubEnum {INSTANCE;
// Member inner class (source_inner)protected class SourceInner {// Overloading method 1protected int methodToMove(TargetEnum target) {return process(target, 1);}
// Overloading method 2 (overload_exist)protected long methodToMove(TargetEnum target, long multiplier) {return process(target, multiplier);}
private <T extends Number> T process(TargetEnum target, T multiplier) {// Variable call + contains target field (per_condition)target.toString();String targetField = target.field;Number result = 0;
// Switch caseswitch (targetField.length()) {case 0:throw new IllegalArgumentException("Empty field"); // Throw statementcase 5:result = targetField.length() * multiplier.intValue();break;default:result = targetField.length() * multiplier.doubleValue();}
// Expression statementresult = (Number) (result.intValue() + 10);
// Ternary operators with inner_class abstract method callList<String> abstractResult = targetField.startsWith("t")? AbstractProcessor.process(target.new Inner()): new ArrayList<>();
// Try statement with IOExceptiontry {if (abstractResult.isEmpty()) {throw new IOException("Empty abstract result");}result = result.intValue() + abstractResult.size();} catch (IOException e) {// No new exception}
// Local inner class 1 (source_feature)class LocalProcessor1 {public void validate() {if (result.intValue() < 0) result = 0;}}new LocalProcessor1().validate();
// Local inner class 2 (source_feature)class LocalProcessor2 {public void append() {abstractResult.add(targetField);}}new LocalProcessor2().append();
return (T) result;}}
// Abstract inner class (method_feature)public abstract static class AbstractProcessor {public abstract List<String> process(TargetEnum.Inner inner);
// ClassName.methodName(arguments)public static List<String> process(TargetEnum.Inner inner) {List<String> list = new ArrayList<>();list.add(inner.getInnerField());return list;}}}
non-sealed enum SourceSubEnum extends SourceEnum {}
enum TargetEnum {TARGET_INSTANCE;
public String field = "targetF"; // Source contains target's field (per_condition)
// Static nested class (target_feature)public static class TargetStaticNested {}
public class Inner {public String getInnerField() {return "inner_" + field;}}}