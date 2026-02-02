package test;
import java.util.ArrayList;import java.util.List;
interface TargetInterface {}
public class TargetClass implements TargetInterface {public void targetMethod() {class LocalInner {private LocalInner() {}}LocalInner inner = new LocalInner();}}
abstract class SourceClass extends ParentClass {static int staticField = 10;TargetClass targetParam;
public class MemberInner {void useAbstractMethod() throws Exception {List<String> result = sourceMethod(new TargetClass());}}
public static class StaticNested {static void invoke() throws Exception {new SourceClass() {@Overridesynchronized List<String> sourceMethod(TargetClass param) {return new ArrayList<>();}}.sourceMethod(new TargetClass());}}
/**
Javadoc for abstract method sourceMethod
@param param TargetClass parameter
@return List of String
*/
abstract synchronized List<String> sourceMethod(TargetClass param);
private List<String> privateConstructor() {targetParam = new TargetClass();List<String> list = this.sourceMethod(targetParam);labeled: {if (staticField > 5) {list.add(String.valueOf(staticField));break labeled;}}return list;}}
class ParentClass {}
