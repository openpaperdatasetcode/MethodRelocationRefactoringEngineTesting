package test;
// Source parent class for extends featureclass SourceParentClass {}
// Final source class (extends + member inner class + static nested class)final class SourceClass extends SourceParentClass {private TargetClass targetField = new TargetClass() {}; // Per condition: source contains target field
// Static nested class (source feature)public static class SourceStaticNested {}
// Member inner class (source feature)public class SourceMemberInner {public void innerMethod() {}}
// Instance method (public access modifier, returns base type)public int instanceMethod() {// Type declaration statementTargetClass.TargetStaticNested targetStatic = new TargetClass.TargetStaticNested();SourceMemberInner inner = new SourceMemberInner();
int result = 0;// Switch statementswitch (targetField.getTypeCode()) {case 1:result = 10;break;case 2:result = 20;break;default:result = 30;}
// For loop + break statementfor (int i = 0; i < 5; i++) {if (i == 2) break;result += i;}
// Variable calltargetField.abstractMethod();targetStatic.staticMethod();inner.innerMethod();
return result;}}
// Abstract target class (static nested class)abstract class TargetClass {public abstract int getTypeCode();public abstract void abstractMethod();
// Static nested class (target_feature)public static class TargetStaticNested {public void staticMethod() {}}}