package test;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;
class ParentTargetClass {protected int superField = 1;}
interface TargetInterface {}
public class SourceClass {private static TargetClass targetField = new TargetClass();
class MemberInner1 {}class MemberInner2 {}
public static final List<String> moveMethod(TargetClass param) {labeled: {try {public int targetSuperField = param.super.field;if (targetSuperField != 1) {throw new SQLException("Super field value mismatch");}
variableCall(param);System.out.println("Expression statement execution");System.out.println(super.toString());
List<String> result = new ArrayList<>();result.add("Success");return result;} catch (SQLException e) {e.printStackTrace();break labeled;}}return new ArrayList<>();}
private static void variableCall(TargetClass target) {target.memberInner.doTask();}}
private class TargetClass extends ParentTargetClass implements TargetInterface {class TargetMemberInner {void doTask() {}}
private TargetMemberInner memberInner = new TargetMemberInner();
public static final List<String> moveMethod(TargetClass param) {try {if (param.superField != 1) {throw new SQLException();}param.memberInner.doTask();return new ArrayList<>();} catch (SQLException e) {return new ArrayList<>();}}}