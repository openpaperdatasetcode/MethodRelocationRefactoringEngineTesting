package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
interface BaseInterface {List<String> getList();}
public class SourceClass implements BaseInterface {private TargetClass targetField;
public class MemberInner {}
private Runnable anonymous = new Runnable() {@Overridepublic void run() {}};
@MethodAnnotation@Overridepublic final List<String> getList() {final int count = 1;if (targetField instanceof TargetClass) {}
private int threshold = 3;if (targetField.innerRec.field > threshold) {throw new IllegalArgumentException();}
List<? extends CharSequence> boundedList = new ArrayList<>();;
this.targetField = targetField;return new ArrayList<>(boundedList.stream().map(String::valueOf).toList());}}
public class TargetClass {public class MemberInner {public class InnerRec {int field;}}}