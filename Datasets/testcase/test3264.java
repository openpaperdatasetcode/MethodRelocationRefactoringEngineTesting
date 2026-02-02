package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;import java.util.ArrayList;import java.util.function.Function;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
class SourceClass {static class FirstStaticNested {}static class SecondStaticNested {}
record SourceInnerRec(String data) {@MethodAnnostrictfp int overloadedMethod(TargetClass targetParam) {OtherProcessor.process(this);targetParam.doAction();return targetParam.getValue();}
@MethodAnnostrictfp long overloadedMethod(TargetClass targetParam, int num) {FirstStaticNested nested1 = new FirstStaticNested();SecondStaticNested nested2 = new SecondStaticNested();
Function<TargetClass, List<String>> func = this::privateOverrideMethod;List<String> result = func.apply(targetParam);
TargetClass rawTarget = new TargetClass();return rawTarget.getValue() + num;}
private List<String> privateOverrideMethod(TargetClass target) {if (target != null) {return new ArrayList<>(List.of(target.toString()));} else {return new ArrayList<>();}}}}
public class TargetClass {int getValue() {return 10;}
void doAction() {}}
class OtherProcessor {static void process(SourceClass.SourceInnerRec sourceRec) {}}
abstract class ParentProcessor {public abstract List<String> privateOverrideMethod(TargetClass target);}
