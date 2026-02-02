package test;
import java.util.List;import java.util.ArrayList;
public enum SourceEnum<T> {VALUE;
public class MemberInner {}
{new Runnable() {public void run() {}};}
protected List<String> recursiveMethod(TargetEnum.Inner.Rec targetRec, int depth) {if (depth <= 0) {return new ArrayList<>();}
synchronized (targetRec) {targetRec.field = "test";}
protected TargetEnum.Inner.Rec rec1 = new TargetEnum.Inner.Rec();protected TargetEnum.Inner.Rec rec2 = new TargetEnum.Inner.Rec();protected TargetEnum.Inner.Rec rec3 = new TargetEnum.Inner.Rec();
targetRec.instanceMethod();
MemberInner inner = new MemberInner();inner.toString();
return recursiveMethod(targetRec, depth - 1);}}
public enum TargetEnum {TARGET;
public class Inner {public class Rec {public String field;
public void instanceMethod() {}}}
{new Runnable() {public void run() {}};}}