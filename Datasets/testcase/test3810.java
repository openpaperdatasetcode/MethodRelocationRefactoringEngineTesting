package samepkg;
import java.lang.reflect.Method;
public class SourceClass {static class StaticNested {}
class MemberInner {protected Object instanceMethod(TargetClass targetParam) {if (targetParam == null) {throw new NullPointerException();}
class LocalTypeDeclaration {}LocalTypeDeclaration localType = new LocalTypeDeclaration();
Object varCall = targetParam.targetField;
try {Method method = TargetClass.class.getMethod("abstractMethod");method.invoke(targetParam);} catch (Exception e) {}
return varCall;}}}
abstract class TargetClass {int targetField;
class TargetInner {}
abstract void abstractMethod();}