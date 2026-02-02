package test;
import java.util.ArrayList;import java.util.List;import java.util.regex.Pattern;
abstract class SourceClass {private TargetClass targetField = new TargetClass();static String staticField = "static";
class SourceInner {class SourceInnerRec {private abstract Object abstractMethod(TargetClass param);
protected List<String> methodToMove() {// Abstract method in for loopfor (int i = 0; i < 1; i++) {Object obj = new SourceInnerRec() {@Overrideprivate Object abstractMethod(TargetClass param) {return param.field;}}.abstractMethod(targetField);}
// Expression statementTargetClass.AnonymousImpl impl = targetField.new AnonymousImpl();
// Pattern expressions (2)volatile Pattern p1 = Pattern.compile("pattern1");volatile Pattern p2 = Pattern.compile("pattern2");
// Variable callString var = targetField.field;
// Depends on static fieldString str = staticField + var;
List<String> list = new ArrayList<>();list.add(str);return list;}}}
{new Object() {}; // Anonymous inner class}}
public class TargetClass extends ParentClass {String field = "targetField";
{new ParentClass() {}; // Anonymous inner class}
class AnonymousImpl {}}
class ParentClass {}