package test;
import java.util.List;import java.util.ArrayList;
enum SourceEnum {ENUM_CONSTANT;
class MemberInner {/**
Javadoc for the instance method to refactor
@param targetParam target enum parameter
@return List<String> result*/private List<String> methodToMove(TargetEnum targetParam) {List<String> result = new ArrayList<>();result.add(SourceEnum.staticField);result.add(targetParam.name());
new Object() {{result.add(SourceEnum.this.name());}};
class LocalInnerInSource {}new LocalInnerInSource();
result.addAll(MemberInner.privateInnerMethod(result));return result;}
private List<String> methodToMove(String str) {return new ArrayList<>();}
private static List<String> privateInnerMethod(List<String> input) {return input;}}
static String staticField = "sourceStaticField";}
protected enum TargetEnum extends SourceEnum {TARGET_CONSTANT;
public List<String> methodToMove() {class LocalInnerInTarget {}new LocalInnerInTarget();return new ArrayList<>();}
class TargetMemberInner {private List<String> callInnerMethod(List<String> param) {return SourceEnum.MemberInner.privateInnerMethod(param);}}}