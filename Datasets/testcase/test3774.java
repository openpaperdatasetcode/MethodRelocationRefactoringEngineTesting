package source;
import java.util.List;import java.util.ArrayList;import target.TargetInterface;
interface SourceInterface {class SourceMemberInner {public String innerField;}
static class SourceStaticNested {public static void staticMethod() {}}
default List<String> processTarget(TargetInterface target) {List<String> result = new ArrayList<>();if (target == null) {return result;}
List rawList = new ArrayList();int count = 0;do {Object targetResult = TargetInterface.SuperTarget::targetInstanceMethod;rawList.add(targetResult.toString());count++;} while (count < 2);
String targetStaticField = TargetInterface.TargetStaticNested.staticField;result.add(targetStaticField);result.addAll(rawList);
return result;}
default List<String> processTarget(TargetInterface target, int limit) {List<String> result = processTarget(target);while (result.size() > limit) {result.remove(result.size() - 1);}return result;}}
package target;
import java.util.List;
abstract interface TargetInterface extends TargetInterface.SuperTarget {static class TargetStaticNested {public static String staticField = "targetStaticData";}
interface SuperTarget {default Object targetInstanceMethod() {return "superInstanceResult";}}}