package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
sealed class SuperSource permits SourceClass {}
public class SourceClass extends SuperSource {protected int outerProtected = 100;class MemberInner {}
@MyAnnotationprivate List<String> methodToMove(TargetClass target) {List<String> result = new ArrayList<>();class LocalInner {}LocalInner local = new LocalInner();
// Constructor invocationTargetClass.MemberInner inner = target.new MemberInner();
// InfixExpression with protected modifierprotected boolean condition = inner.count + outerProtected > 50;
// Switch caseswitch (inner.state) {case "ACTIVE":inner.variableCall();result.add("Active");break;case "INACTIVE":result.add("Inactive");break;default:result.add("Unknown");}
// Access outer protected fieldinner.value = this.outerProtected;
// Requires try-catchtry {if (inner.value < 0) throw new IOException();} catch (IOException e) {result.add(e.getMessage());}
return result;}}
interface TargetInterface {}
public class TargetClass implements TargetInterface {class MemberInner {int count;String state;int value;
void variableCall() {}}}