package test;
import java.sql.SQLException;
/**
TargetClass Javadoc
Generic target class with local inner class
@param <T> Generic type parameter*/public class TargetClass<T> {T targetField;
public void createLocalInner() {class LocalInner {} // target_feature: local inner classnew LocalInner();}}
/**
SourceClass Javadoc
Generic source class with static nested and member inner classes
@param Generic type parameter
/
public class SourceClass {
// Source feature: static nested class
static class SourceStaticNested {}
// Source feature: member inner class
class SourceInner {
/*
Method Javadoc
Varargs method with SQLException and required features
@param target TargetClass instance
@param args Variable length Object arguments
@return Combined result of target field and arguments
@throws SQLException If database-related operation fails*/protected Object methodToMove(TargetClass target, Object... args) throws SQLException {
// Variable call
U var = target.targetField;
target.createLocalInner();
// Try statementtry {if (var == null) {throw new SQLException("Target field is null"); // SQLException}} catch (SQLException e) {throw e; // Propagate checked exception}
// Continue statementStringBuilder result = new StringBuilder(String.valueOf(var));for (int i = 0; i < args.length; i++) {if (args[i] == null) continue;result.append(args[i]);}
return result.toString();}}}