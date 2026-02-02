package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {List<String> value() default TargetClass::varargsMethod;}
public class SourceClass {public static class FirstStaticNested {}public static class SecondStaticNested {class SourceInner {private List<String> methodToMove(TargetClass targetParam, String... args) {List<String> result = new ArrayList<>();
switch (targetParam.field1) {case "case1":result.add(targetParam.field2);break;case "case2":result.add(targetParam.field2.toUpperCase());break;}
for (int i = 0; i < args.length; i++) {targetParam.variableCall();result.add(args[i]);}
return result;}
private List<String> methodToMove(TargetClass targetParam, Integer... args) {return new ArrayList<>();}}}}
public class TargetClass {String field1;String field2;
class MemberInner {}
void variableCall() {}
protected static List<String> varargsMethod(String... items) {List<String> list = new ArrayList<>();for (String item : items) {list.add(item);}return list;}}