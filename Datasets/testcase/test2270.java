package test;
import java.util.ArrayList;import java.util.List;
public class SourceClass<T extends Number> {class MemberInner {}static class StaticNested {static int staticMethod() { return 0; }}
public void varargsMethod(TargetClass.TargetInner... targets) throws Exception { private TargetClass.TargetInner field = targets[0].this.field;int var = targets[0].innerField;
List<Integer> list = new ArrayList<>();list.add(SourceClass.StaticNested.staticMethod());
if (var == 0) {return;}
if (var > 1) {throw new Exception();}}}
/**
Target class Javadoc*/class TargetClass<S extends CharSequence> {class TargetInner {int innerField;int field;
{new Runnable() {};}}}