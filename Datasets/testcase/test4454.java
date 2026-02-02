package source;
import target.TargetClass;
strictfp class SourceClass {private TargetClass targetField;
public class MemberInner {}
public void createAnonymousInner() {Runnable r = new Runnable() {public void run() {}};}
protected SourceClass() {super();TargetClass<String> genericTarget = new TargetClass<>();TargetClass rawTarget = new TargetClass();targetField.memberInner.field = 1;genericTarget.memberInner.field = 1;}}
package target;
class TargetClass<T> {public class MemberInner {public int field;}public MemberInner memberInner = new MemberInner();}