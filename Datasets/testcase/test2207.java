package test;
import java.util.ArrayList;import java.util.List;
strictfp class SourceClass permits SubSourceClass {class Inner {private List<String> process(TargetClass... targets) {List<String> result = new ArrayList<>();class LocalInner {}
int num = 1;for (TargetClass target : targets) {result.add(String.valueOf(target.inner.field + num));result.add(String.valueOf(TargetClass.staticField));}
new Runnable() {@Overridepublic void run() {}};
return result;}}}
class SubSourceClass extends SourceClass {}
protected class TargetClass {static int staticField = 10;Inner inner = new Inner();
class Inner {int field;}}