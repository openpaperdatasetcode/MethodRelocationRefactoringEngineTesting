package test;
import java.lang.reflect.Method;
/**
TargetClass Javadoc
Abstract class with member inner class*/abstract class TargetClass extends ParentClass {int targetField;
/**
TargetInner Javadoc
Member inner class of TargetClass
*/
class TargetInner {}
}
class ParentClass {protected int parentField;}
abstract class SourceClass {static class SourceStaticNested {}
public void example() {class LocalInner {}}
protected int methodToMove(TargetClass target) {// Type declaration statementclass TypeDecl {}TypeDecl type = new TypeDecl();
// Variable callint var = target.targetField;
// Inner class with DoStatementclass InnerProcessor {protected void process() {int count = 0;do {// super.field and 2target.parentField = 2;count++;} while (count < 3);}}new InnerProcessor().process();
// Exception throwing statements with static method calltry {Object result = SourceSubClass.staticMethod(1); // 1 as required} catch (IllegalStateException e) {throw new IllegalStateException("Error", e);}
// Used by reflectiontry {Method method = TargetClass.class.getMethod("toString");method.invoke(target);} catch (Exception e) {}
return var;}}
class SourceSubClass extends SourceClass {public static Object staticMethod(int num) {return num;}}