package same;
import java.lang.reflect.Method;
class Source {static class SourceStaticNested {}class SourceMemberInner {class SourceInnerRec {final Target instanceMethod(Target targetParam) {protected Target parentInstanceInException() {try {return ParentClass.methodName(targetParam);} catch (Exception e) {return targetParam;}}
Target parentResult = parentInstanceInException();super();
class LocalType {Target storedTarget = targetParam;}new LocalType();
Object var = targetParam;try {Method getMethod = Target.class.getMethod("getInnerField");getMethod.invoke(targetParam);} catch (Exception e) {}
InnerAccessor.call(targetParam);return targetParam;}}}
private static class InnerAccessor {private static void call(Target target) {target.getInnerField();}}}
class ParentClass {public static Target methodName(Target target) {return target;}}
public class Target {private String innerField;
public String getInnerField() {class TargetLocalInner {void printField() {System.out.println(innerField);}}new TargetLocalInner().printField();return innerField;}}