package same;
import java.util.ArrayList;import java.util.List;
private class SourceClass {private int outerPrivate = 10;static class StaticNested {}class MemberInner {}
public List<String> process(TargetClass target, String... args) {List<String> result = new ArrayList<>();
// ForStatement with target static fieldfor (int i = 0; i < TargetClass.STATIC_COUNT; i++) {if (i % 2 == 0) continue;result.add(args[i % args.length]);}
// Enhanced for statementfor (String arg : args) {result.add(arg + target.localField);}
// Overloaded method callsresult.addAll(target.convert(outerPrivate));result.addAll(target.convert(String.valueOf(outerPrivate)));
return result;}}
package same;
import java.util.List;import java.util.ArrayList;
public class TargetClass {public static final int STATIC_COUNT = 5;int localField = 3;
public List<String> convert(int num) {class LocalConverter {String process() {return "int:" + num;}}List<String> list = new ArrayList<>();list.add(new LocalConverter().process());return list;}
public List<String> convert(String str) {class LocalConverter {String process() {return "str:" + str;}}List<String> list = new ArrayList<>();list.add(new LocalConverter().process());return list;}}