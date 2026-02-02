import java.lang.reflect.Method;
class ParentSourceClass {}
public class SourceClass extends ParentSourceClass {static class StaticNested {}
Runnable anonymous = new Runnable() {@Overridepublic void run() {methodToMove(new TargetClass(), "final", "static");}};
// 带关键字参数的可变参数方法public TargetClass methodToMove (TargetClass target, String... keywordParams) {private String nameExp1 = "NameExpr1";private String nameExp2 = "NameExpr2";
System.out.println(nameExp1 + ", " + nameExp2);target.new MemberInner().doAction();StaticNested nested = new StaticNested();
try {Method method = TargetClass.MemberInner.class.getMethod("doAction");} catch (NoSuchMethodException e) {}
return target;}
// 重载方法public TargetClass methodToMove (TargetClass target, int param, String... keywordParams) {return methodToMove (target, keywordParams);}}
class ParentTargetClass {}
class TargetClass extends ParentTargetClass {class MemberInner {void doAction() {}}}