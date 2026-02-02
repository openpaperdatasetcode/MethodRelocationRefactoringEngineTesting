package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnotation {}
protected record SourceClass(int id) {class MemberInner {}
{new Runnable() {};}
@CustomAnnotationfinal Object instanceMethod(TargetRecord target) {// Access target field (record component)String targetField = target.value();
variableCall();
// Uses outer thisSourceClass outerThis = this;
return new Object();}
private void variableCall() {}}
final record TargetRecord(String value) {}