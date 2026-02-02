package test;
import java.sql.SQLException;
abstract class SourceClass {class SourceInner {protected Object method(TargetClass targetParam, Object... args) throws SQLException {targetParam.field = args[0];
switch (targetParam.getLevel()) {case 1:int result = targetParam.createInner().call1().call2().call3();break;default:break;}
Runnable r = new Runnable() {public void run() {System.out.println(targetParam.field);}};r.run();
return targetParam.field;}}
void createAnonymous() {Runnable r = new Runnable() {public void run() {new SourceInner().method(new TargetClass(), "test");}};}}
public abstract class TargetClass {Object field;int level;
int getLevel() {return level;}
public InnerClass createInner() {return new InnerClass();}
class InnerClass {public Step1 call1() {return new Step1();}}
class Step1 {public Step2 call2() {return new Step2();}}
class Step2 {public int call3() {return 1;}}
abstract void abstractMethod();
{Runnable r = new Runnable() {public void run() {abstractMethod();}};}}