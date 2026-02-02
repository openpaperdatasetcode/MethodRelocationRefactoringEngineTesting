package test;
import java.util.List;import java.util.ArrayList;
// Interface for source enum's implements featureinterface TestInterface {void testMethod();}
// Target: default enum with no additional target_featuresenum TargetEnum {VALUE1, VALUE2;
public String getValue() {return name();}}
// Source: private enum (implements + member inner + local inner class)private enum SourceEnum implements TestInterface {PERM1, PERM2;
// Contains target field (meets per_condition)private TargetEnum targetField = TargetEnum.VALUE1;
// Member inner class (source_class feature)protected class SourceMemberInner {public void process(TargetEnum target) {variableCall(target);}}
// Strictfp instance method (returns List<String>)strictfp List<String> instanceMethod() {List<String> result = new ArrayList<>();
// Expression statementSourceMemberInner inner = new SourceMemberInner();inner.process(targetField);
// Variable callvariableCall(targetField);
// Raw type usage (raw_type feature)List rawList = new ArrayList();rawList.add(targetField);
// Local inner class (source_class feature)class SourceLocalInner {void collectValues() {for (TargetEnum target : TargetEnum.values()) {result.add(target.getValue());}}}new SourceLocalInner().collectValues();
return result; // No new exception}
private void variableCall(TargetEnum target) {TargetEnum local = target;result.add(local.getValue());}
@Overridepublic void testMethod() {}}