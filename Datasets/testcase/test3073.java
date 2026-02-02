package source;
import target.TargetClass;import java.sql.SQLException;
protected class SourceClass {static class StaticNested {}
public SourceClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
/**
Method Javadoc: Handles varargs with keyword parameters
@param target The target class instance
@param keywords Variable arguments of keywords*/void methodToMove(TargetClass target, String... keywords) {String var = target.targetField;this.processKeywords(keywords);
try {target.targetMethod();} catch (SQLException e) {e.printStackTrace();}}
private void processKeywords(String... keywords) {for (String kw : keywords) {System.out.println(kw);}}}
// In different package (target)package target;
import java.sql.SQLException;
public class TargetClass {public String targetField;class TargetInner {}
public TargetClass() {}
public void targetMethod() throws SQLException {}
public void setProperty() {TargetClass target = new TargetClass();Runnable r = target::targetMethod;Object result = new TargetClass();}}