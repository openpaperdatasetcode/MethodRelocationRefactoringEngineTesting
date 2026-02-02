package test;
import java.util.List;import java.util.ArrayList;import java.util.Arrays;
enum SourceEnum {VALUE1, VALUE2;
public class MemberInner {}public static class StaticNested {}
protected TargetEnum moveMethod(TargetEnum target) {List<String> list = Arrays.stream(new String[]{"a", "b"}).map(ParentClass::create).toList();
target.field = 5;
TargetEnum instance = new TargetEnum() {};
MemberInner inner = new MemberInner();inner.toString();
return target;}
protected TargetEnum moveMethod(String str) {return TargetEnum.TARGET1;}}
sealed enum TargetEnum permits TargetEnum.TARGET1, TargetEnum.TARGET2 {TARGET1, TARGET2;
int field;
TargetEnum() {new Runnable() {public void run() {}};}}
class ParentClass {public static String create(String s) {return s;}}