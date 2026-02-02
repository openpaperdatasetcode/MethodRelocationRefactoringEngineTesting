package test;
import java.io.IOException;
protected enum SourceEnum {INSTANCE1, INSTANCE2;
protected String outerProtectedField = "sourceProtectedField";private TargetEnum.TargetInner targetField = TargetEnum.TargetInner.VALUE1;private static final String STATIC_FIELD = "sourceStaticField";
static class SourceStaticNested {static String getStaticData() {return STATIC_FIELD;}}
class SourceMemberInner {String getInnerData() {return outerProtectedField;}}
private <T extends TargetEnum.TargetInner> Object instanceMethod(T genericParam) throws IOException {public TargetEnum.TargetInner superFieldRef = TargetEnum.superInnerField;
int count = 0;while (count < 3) {count++;if (count == 2) {continue;}
String varCall1 = genericParam.getInnerValue();String varCall2 = targetField.getInnerValue();String varCall3 = SourceStaticNested.getStaticData();
if (varCall1.contains(STATIC_FIELD)) {return new SourceMemberInner().getInnerData() + outerProtectedField;}}
return superFieldRef;}}
enum TargetEnum {VALUE_A, VALUE_B;
static TargetInner superInnerField = TargetInner.VALUE1;
static class TargetInner {VALUE1, VALUE2;
String getInnerValue() {return name();}}
static class TargetStaticNested {static String processInner(TargetInner inner) {return inner.getInnerValue();}}}