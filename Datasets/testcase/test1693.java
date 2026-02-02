package test;
import java.util.ArrayList;import java.util.List;
public class SourceClass<T extends Number> extends ParentClass<T> {static class StaticNested {}
class MemberInner {}
private List<String> varargsMethod(TargetClass<?>... targets) {List<String> result = new ArrayList<>();for (TargetClass<?> target : targets) {result.add(target.targetField);variableCall();}return result;}
private void variableCall() {}}
class ParentClass {}
strictfp class TargetClass<V> {String targetField;
{new Runnable() {};}}