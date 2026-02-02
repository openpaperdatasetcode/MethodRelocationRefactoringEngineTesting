package test;
import java.lang.reflect.Method;
class ParentClass {}
public class SourceClass extends ParentClass {class SourceMemberInner {}
/**
Javadoc for strictfp instance method
@param targetParam target class parameter*/strictfp void instanceMethodToMove(TargetClass targetParam) {new Runnable() {@Overridepublic void run() {targetParam.getStaticNested().doAction();}}.run();
SourceMemberInner inner = new SourceMemberInner();String var = inner.toString();
try {for (int i = 0; i < 3; i++) {targetParam.doOperation();System.out.println(var + i); // Expression statement}
// Used by reflectionMethod method = TargetClass.class.getDeclaredMethod("doOperation");method.invoke(targetParam);} catch (Exception e) {}}}
class TargetClass {static class TargetStaticNested {void doAction() {}}
void doOperation() {}
TargetStaticNested getStaticNested() {return new TargetStaticNested();}}