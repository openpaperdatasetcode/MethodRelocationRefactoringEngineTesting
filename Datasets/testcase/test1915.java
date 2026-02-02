package test;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;
public class SourceClass {private TargetClass target = new TargetClass();
public Object method(Object... args) throws SQLException {class LocalInner {private Object getTargetField() {if (target.field == 3) {return target.this.field;}return null;}}
Runnable r = new Runnable() {public void run() {target.memberInner.method();}};r.run();
List rawList = new ArrayList();rawList.add(target.field);
target.field = args.length;if (target.field < 0) {throw new IllegalArgumentException();}
return new LocalInner().getTargetField();}}
public class TargetClass {int field = 3;
class MemberInner {void method() {System.out.println(field);}}MemberInner memberInner = new MemberInner();}