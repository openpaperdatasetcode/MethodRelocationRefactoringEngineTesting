package test;
import java.util.List;import java.util.ArrayList;import java.util.Collections;
public enum SourceEnum {FIRST, SECOND;
class FirstInner {public int getTargetValue(TargetEnum target) {return target.getValue();}}
class SecondInner {public String getTargetName(TargetEnum target) {return target.name();}}
private List<String> processTarget(TargetEnum target) {List<String> result = new ArrayList<>();FirstInner firstInner = new FirstInner();SecondInner secondInner = new SecondInner();
labeledBlock: {result.add(secondInner.getTargetName(target));result.add(String.valueOf(firstInner.getTargetValue(target)));
if (target == TargetEnum.A) {break labeledBlock;};}
result.add(String.valueOf(super.ordinal()));Collections.sort(result);return result;}}
private enum TargetEnum {A(10), B(20);
private final int value;
TargetEnum(int value) {this.value = value;}
class TargetInner {public int extractValue() {return TargetEnum.this.value;}}
public int getValue() {return new TargetInner().extractValue();}}