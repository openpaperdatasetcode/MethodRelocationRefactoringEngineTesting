package test;
import java.util.regex.Pattern;import java.io.IOException;
/**
Javadoc for TargetClass
Contains static nested class and abstract members*/abstract class TargetClass {static class TargetStaticNested {int field = 1;}
class TargetInner {String name;
TargetInner(String name) {this.name = name;}}}
public class SourceClass {class SourceInner {class SourceInnerRec {public TargetClass.TargetInner methodToMove(TargetClass.TargetInner targetParam) throws IOException {// ConstructorInvocation with target featuresTargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested();TargetClass.TargetInner newInner = new TargetClass().new TargetInner(TargetClass.TargetStaticNested.field + nested.field);
// Constructor invocationTargetClass.TargetInner anotherInner = new TargetClass().new TargetInner("test");
// For statementfor (int i = 0; i < targetParam.name.length(); i++) {char c = targetParam.name.charAt(i);}
// Pattern expression (1)Pattern pattern = Pattern.compile(targetParam.name);
// Variable callString var = targetParam.name;
return newInner;}}}
void localInnerMethod() {class LocalInner {SourceInner.Rec getRec() {return new SourceInner().new SourceInnerRec();}}}}