package same.pkg;
import java.util.List;import java.util.ArrayList;import java.util.Arrays;
// Source enum with permits clauseenum SourceEnum permits ExtendedSourceEnum {INSTANCE;
// Contains target's field (per condition)private TargetEnum targetField = TargetEnum.MAIN;
private List<String> method() {variableCall();super.toString(); // Super keyword usage
// Constructor invocation (Target's inner class)TargetEnum.TargetInner targetInner = targetField.new TargetInner("innerValue");
// 1 public InfixExpression (e.g., string concatenation)public String infixResult = targetInner.getInnerField() + "_appended";
// Array initialization with source's normal method call (call_method)int[] intArray = {this.calculate(5, 3), this.calculate(2, 1)};
// Assemble result listList<String> result = new ArrayList<>();result.add(targetField.name());result.add(infixResult);result.add(Arrays.toString(intArray));
return result;}
// Source's normal method for call_methodpublic int calculate(int a, int b) {return a + b;}
private void variableCall() {String localVar = targetField.getTargetField();}}
// Extended enum for source's permits clauseenum ExtendedSourceEnum implements SomeInterface {EXTENDED_INSTANCE;}
interface SomeInterface {}
// Target enum with member inner classpublic enum TargetEnum {MAIN, SECONDARY;
private String targetField = "targetValue";
String getTargetField() {return targetField;}
// Target's member inner classclass TargetInner {private String innerField;
public TargetInner(String innerField) {this.innerField = innerField;}
String getInnerField() {return innerField;}}}