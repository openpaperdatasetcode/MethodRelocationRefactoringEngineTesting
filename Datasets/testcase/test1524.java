package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessFinal {}
final class Target {public static class Nested {private String value;
public Nested(String value) {this.value = value;}
public Nested append(String s) {this.value += s;return this;}
public Nested uppercase() {this.value = value.toUpperCase();return this;}
public String getValue() {return value;}}
public Nested createNested(String s) {return new Nested(s);}}
class OtherClass {public int processStep1(Target.Nested nested) {return nested.getValue().length();}
public int processStep2(Target.Nested nested) {return nested.getValue().hashCode();}
public int processStep3(Target.Nested nested) {return nested.getValue().compareTo("");}}
private class Source {static class StaticNested {static Target.Nested prepareNested(Target target, String data) {return target.createNested(data);}}
class MemberInner {Target.Nested enhanceNested(Target.Nested nested) {return nested.append("_enhanced");}}
@ProcessFinalpublic final Target.Nested handle(Target target) {// Expression statementTarget.Nested baseNested = StaticNested.prepareNested(target, "base");
// 2 ArrayCreation expressionsString[] parts = new String[2];int[] lengths = new int[2];
// Variable callMemberInner inner = new MemberInner();Target.Nested enhanced = inner.enhanceNested(baseNested);
// Depends on inner classparts[0] = enhanced.getValue();lengths[0] = parts[0].length();
// Switch statement with 3 chained method callsOtherClass other = new OtherClass();switch (enhanced.getValue().length()) {case 5:int result1 = other.processStep1(enhanced).processStep2(enhanced).processStep3(enhanced);break;case 10:int result2 = other.processStep1(enhanced).processStep2(enhanced).processStep3(enhanced);break;default:int result3 = other.processStep1(enhanced).processStep2(enhanced).processStep3(enhanced);}
return enhanced;}}