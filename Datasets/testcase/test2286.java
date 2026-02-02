package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnn {}
public class SourceClass {class Inner {class InnerRec {@MyAnnprotected List<String> varargsMethod(TargetClass<? extends Number>... targets) {if (targets.length == 0) {throw new IllegalArgumentException();}
variableCall = targets[0].field;TargetClass.StaticNested nested = new TargetClass.StaticNested();
return new ArrayList<>();}
String variableCall;}}}
protected class TargetClass<T> {String field;static class StaticNested {}}