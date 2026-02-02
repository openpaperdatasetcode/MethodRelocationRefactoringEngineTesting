package test;
import java.util.List;import java.util.ArrayList;import java.util.Collections;import java.lang.reflect.Method;
@interface SourceAnnotation {
static class StaticNestedUtil {public static String formatData (String input) {return "Formatted_" + input;}
public static String reflectCallTarget(TargetAnnotation target, String methodName) {try {Method method = TargetAnnotation.class.getMethod(methodName);return (String) method.invoke(target);} catch (Exception e) {return "ReflectionCallFailed: " + e.getMessage();}}}
default List<String> recursiveProcess(TargetAnnotation target, int depth) {List<String> resultList = new ArrayList<>();
class LocalRecursionHandler {public void handleCurrentLevel (TargetAnnotation ta, int currentDepth) {labeledBlock: {try {
String targetData = (StaticNestedUtil.reflectCallTarget (ta, "getTargetData")
"_" + currentDepth);
resultList.add(StaticNestedUtil.formatData(targetData));
TargetAnnotation.TargetInner inner = ta.getInnerComponent ();resultList.add ("InnerComponentData:" + inner.getInnerValue ());
if (currentDepth <= 0) {break labeledBlock; }
TargetAnnotation nextTarget = ta.createNext ();resultList.addAll (recursiveProcess (nextTarget, currentDepth - 1));} catch (Exception e) {resultList.add ("ProcessingError:" + e.getMessage ());break labeledBlock;}}}}
LocalRecursionHandler handler = new LocalRecursionHandler();handler.handleCurrentLevel(target, depth);
Collections.sort(resultList);return resultList;}}
@interface TargetAnnotation extends BaseTargetAnnotation {
/static class TargetInner {private String innerValue;
public TargetInner(String innerValue) {this.innerValue = innerValue;}
public String getInnerValue() {return innerValue;}}
String getTargetData ();
TargetInner getInnerComponent ();
TargetAnnotation createNext ();
}
@interface BaseTargetAnnotation {
String getBaseData ();
}
@interface TargetAnnotationSub extends TargetAnnotation {@Overridedefault String getTargetData () {
return getBaseData ().startsWith ("Init")? "SubTargetData_" + getBaseData (): "DefaultSubData";}
@Overridedefault TargetInner getInnerComponent() {return new TargetInner("SubInnerValue_" + getBaseData().length());}
@Overridedefault TargetAnnotation createNext () {
return new TargetAnnotation () {@Overridepublic String getTargetData () {return "NextLevelData_" + TargetAnnotationSub.this.getBaseData ();}
@Overridepublic TargetInner getInnerComponent() {return new TargetInner("NextInnerValue_" + TargetAnnotationSub.this.getBaseData().length());}
@Overridepublic TargetAnnotation createNext () {return this;}
@Overridepublic String getBaseData () {return super.getBaseData () + "_Next"; }};}
@Overridedefault String getBaseData() {return "InitBaseData";}}