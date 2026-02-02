package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
sealed class ParentClass permits SourceClass {}
private class SourceClass extends ParentClass {class MemberInner {private List<String> thisMethod() {return new ArrayList<>();}}
/**
Method Javadoc: Processes target class and its static nested class
@param target Instance of TargetClass to process
@throws Exception when processing fails*/@MyAnnotationprivate void methodToMove(TargetClass target) throws Exception {// Local inner classclass LocalInner {}new LocalInner();
// Constructor invocationTargetClass.StaticNested nested = new TargetClass.StaticNested();MemberInner inner = new MemberInner();
// Static VariableDeclarationStatement with 1 this.fieldstatic {int field = target.thisField;}
// Expression statementnested.value = "processed";
// For loop with continue statementfor (int i = 0; i < 5; i++) {if (i == target.thisField) {continue;}}
// Instance method call in exception handlingtry {List<String> result = inner.thisMethod();} catch (Exception e) {throw new Exception("Processing failed", e);}}}
protected class TargetClass {int thisField;
static class StaticNested {String value;}}